package controller

import model.gameComponent.gameBase.Game
import model.gameComponent.GameInterface
import model.meshComponent.meshBase._
import model.diceComponent.diceBase.Dice
import util.Observable
import util.UndoManager
import util.Command
import controller.SetCommand
import GameStatus._
import model.Move
import scala.util.{Try,Success,Failure}
import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject}
import model.fileIOComponent.fileIOJsonImpl.FileIO
import model.fileIOComponent._
import model.fileIOComponent.FileIOInterface


class Controller @Inject()(@Named("DefaultGameType")var game: GameInterface) extends ControllerInterface {
    val undoManager = new UndoManager[GameInterface]
    var mesh1 = new Mesh(0)
    var gamestatus: GameStatus = IDLE
    val file:FileIOInterface = new fileIOJsonImpl.FileIO

    val meshtry = game.startgame
        meshtry match {
            case Success(v) => game = v
            case Failure(e) => print(e.getMessage)
        }

    def doAndPublish(doThis: (Int) => GameInterface,rolledDice:Int) = {
        game = doThis(rolledDice)
        notifyObservers
    }
    def doAndPublish(doThis: => GameInterface) = {
        game = doThis
        notifyObservers
    }
    def move1(rolledDice:Int): GameInterface = {
        game = put(Move(rolledDice, game.playerturn, 1))
        printPlayerTurn()
        game
        }
    def printPlayerTurn() : Unit = {
        getTurnC1(game.playerturn) match {
			case Success(v) => println("It is Player " + v + "'s turn\n")
			case Failure(e) => println(e.getMessage)
		}
    }
    def getTurnC1(playerturn: Int) : Try[Char] = {
        game.getTurnC(playerturn)
    }
    def put(move:Move): GameInterface = undoManager.doStep(game, SetCommand(move))
    
    def undo: GameInterface = {
        gamestatus = UNDO
        print(gamestatus.map(gamestatus))
        game = undoManager.undoStep(game)
        game
    }
   
    def redo: GameInterface = {
        gamestatus = REDO
        print(gamestatus.map(gamestatus))
        game = undoManager.redoStep(game)
        game
    }
    def save: GameInterface = {
        gamestatus = SAVED
        print(gamestatus.map(gamestatus))
        file.save(game)
        game
    }
    def load: GameInterface = {
        gamestatus = LOADED
        print(gamestatus.map(gamestatus))
        game = file.load
        game
    }
}