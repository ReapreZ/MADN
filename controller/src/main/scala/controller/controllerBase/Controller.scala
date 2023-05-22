package controller

import model.diceComponent._
import model.gameComponent.gameBase.Game
import model.gameComponent.GameInterface
import model.meshComponent.meshBase._
import model.Games
import io.GameDaoComponent.GamesTable
import io.GameDaoComponent.GameDaoSlickImpl
import util.Observable
import util.UndoManager
import util.Command
import scala.concurrent.Future
import controller.SetCommand
import model.Move
import scala.util.{Try,Success,Failure}
import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject}
import model.fileIOComponent._

import com.typesafe.config.ConfigFactory
import slick.jdbc.PostgresProfile.api._



class Controller @Inject()(@Named("DefaultGameType")var game: GameInterface) extends ControllerInterface {
    val gamesClass = Games(0,0,"","","")
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
    def put(move:Move): GameInterface = undoManager.doStep(game, SetCommand(move))
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
        GameDaoSlickImpl.delete
        val gameDBItems = gamesClass.createGame(game)
        GameDaoSlickImpl.update(gameDBItems.getId, gameDBItems.getPlayerturn, gameDBItems.getMesh, gameDBItems.getPiecesOutList, gameDBItems.getTimesPlayerRolledList)
        Thread.sleep(1000)
    }
    def readFromDB(): Unit = { GameDaoSlickImpl.read }
}