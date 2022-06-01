package controller

import controller.SetCommand
import model.gameComponent.gameBase.Game
import model.meshComponent.meshBase._
import model.diceComponent.diceBase.Dice
import scala.io.StdIn.readLine
import util.Observable
import util.UndoManager
import util.Command
import GameStatus._
import model.Move
import scala.util.{Try,Success,Failure}


class Controller(var game: Game) extends ControllerInterface{
    val undoManager = new UndoManager[Game]
    var mesh1 = new Mesh(0,0,0)
    var gamestatus: GameStatus = IDLE

    /*def doAndPublish(doThis: (Move) => Game,move:Move) = {
        game = doThis(move)
        notifyObservers
    }*/

    def doAndPublish(doThis: (Int) => Game,rolledDice:Int) = {
        game = doThis(rolledDice)
        notifyObservers
    }
    def doAndPublish(doThis: => Game) = {
        game = doThis
        notifyObservers
    }
    def move1(rolledDice:Int): Game = {
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
    def put(move:Move): Game = undoManager.doStep(game, SetCommand(move))
    
    def undo: Game = {
        gamestatus = UNDO
        print(gamestatus.map(gamestatus))
        game = undoManager.undoStep(game)
        game
    }
   
    def redo: Game = {
        gamestatus = REDO
        print(gamestatus.map(gamestatus))
        undoManager.redoStep(game)
    }
}