package controller

import model.diceComponent._
import model.gameComponent.gameBase.Game
import model.gameComponent.GameInterface
import model.meshComponent.meshBase._
import model.Games
import io.GameDaoComponent.GamesTable
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
object Connection {
  val db = Database.forConfig("postgres", ConfigFactory.load())
}


class Controller @Inject()(@Named("DefaultGameType")var game: GameInterface) extends ControllerInterface {
    val gamesClass = Games(0,0,"","","")
    val games:TableQuery[GamesTable] = new TableQuery(new GamesTable(_))
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
    def insertGame(): Future[Int] = {
        val gameToGames = gamesClass.createGame(game)
        val insertQuery = games += (gameToGames.getId, gameToGames.getPlayerturn, gameToGames.getMesh, gameToGames.getPiecesOutList, gameToGames.getTimesPlayerRolledList)
        val query = sql"SELECT * FROM GamesTable".as[(Int, Int, String, String, String)]
        //val insertQuery = games += (games.getId, games.getPlayerturn, games.getPiecesOutList, games.getTimesPlayerRolledList)
        Connection.db.run(query)
    }
}