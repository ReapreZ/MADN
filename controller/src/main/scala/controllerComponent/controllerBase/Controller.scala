package controllerComponent.controllerBase

import util.Observable
import util.UndoManager
import util.Command
import GameStatus._
import scala.util.{Try,Success,Failure}
import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject}


class Controller @Inject()(@Named("DefaultGameType")var game: GameInterface) extends ControllerInterface {
    val undoManager = new UndoManager[GameInterface]
    val file:FileIOInterface = new FileIO
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
}