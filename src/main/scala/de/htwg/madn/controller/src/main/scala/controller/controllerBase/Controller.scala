package de.htwg.madn.controller.controllerBase

import de.htwg.madn.controller.ControllerInterface
import de.htwg.madn.model.diceComponent._
import de.htwg.madn.model.gameComponent.gameBase.Game
import de.htwg.madn.model.gameComponent.GameInterface
import de.htwg.madn.model.meshComponent.meshBase._
import de.htwg.madn.model.Games
import de.htwg.madn.io.GameDaoComponent.GamesTable
import de.htwg.madn.io.GameDaoComponent.GameDaoSlickImpl
import de.htwg.madn.io.GameDaoComponent.GameDAOMongoImpl
import de.htwg.madn.controller.controllerBase.SetCommand
import de.htwg.madn.model.Move
import de.htwg.madn.util.Observable
import de.htwg.madn.util.UndoManager
import de.htwg.madn.util.Command
import de.htwg.madn.model.fileIOComponent._
import de.htwg.madn.io.GameDaoInterface
import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Try,Success,Failure}
import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject}
import com.typesafe.config.ConfigFactory
import slick.jdbc.PostgresProfile.api._

class Controller @Inject()(@Named("DefaultGameType")var game: GameInterface) extends ControllerInterface {
    val gamesClass = Games(0,0,"","","")
    val gameDAO = new GameDaoSlickImpl
    val undoManager = new UndoManager[GameInterface]
    val file:FileIOInterface = new fileIOJsonImpl.FileIO
    val meshtry = game.startgame
        meshtry match {
            case Success(v) => game = v
            case Failure(e) => print(e.getMessage)
        }
    def doAndPublish(doThis: (Int) => GameInterface,rolledDice:Int) = { game = doThis(rolledDice); notifyObservers}
    def doAndPublish(doThis: => GameInterface) = { game = doThis; notifyObservers }
    def move1(rolledDice:Int): GameInterface = { game = put(Move(rolledDice, game.playerturn, 1)); printPlayerTurn(); game }
    def getTurnC1(playerturn: Int) : Try[Char] = { game.getTurnC(playerturn) }
    def put(move:Move): GameInterface = undoManager.doStep(game, new SetCommand(move))
    def undo: GameInterface = { game = undoManager.undoStep(game); game }
    def redo: GameInterface = { game = undoManager.redoStep(game) ; game }
    def save: GameInterface = { file.save(game); game }
    def load: GameInterface = { game = file.load; game }
    def printPlayerTurn(): Unit = {
        getTurnC1(game.playerturn) match {
            case Success(v) => println("It is Player " + v + "'s turn\n")
            case Failure(e) => println(e.getMessage)
        }
    }
    def insertInDB(): Unit = {
        val gameDBItems = gamesClass.createGame(game)
        val id = gameDBItems.getId
        val playerturn = gameDBItems.getPlayerturn
        val mesh = gameDBItems.getMesh
        val piecesOut = gameDBItems.getPiecesOutList
        val timesPlayerRolled = gameDBItems.getTimesPlayerRolledList
        gameDAO.delete
        Thread.sleep(2000)
        gameDAO.create
        Thread.sleep(2000)
        gameDAO.update(id, playerturn, mesh, piecesOut, timesPlayerRolled)
    }
    def readFromDB(): Unit = {
        val dbData = gameDAO.read
        dbData.onComplete {
            case Success(t) => println(t.toString)
            case Failure(e) => println("An error has occured" + e.getMessage)
        } 
    }
}