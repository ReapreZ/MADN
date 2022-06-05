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


class Controller @Inject()(@Named("DefaultGameType")var game: GameInterface) extends ControllerInterface {
    val undoManager = new UndoManager[GameInterface]
    var mesh1 = new Mesh(0)
    var gamestatus: GameStatus = IDLE

    /*def doAndPublish(doThis: (Move) => Game,move:Move) = {
        game = doThis(move)
        notifyObservers
    }*/

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
        //game.move(rolledDice)
        //game = game.move(rolledDice)
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
}