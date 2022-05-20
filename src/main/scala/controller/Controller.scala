package controller

import controller.SetCommand
import model.GameComponent.GameBase.Game
import model.MeshComponent.MeshBase.Mesh
import model.DiceComponent.DiceBase.Dice
import scala.io.StdIn.readLine
import util.Observable
import util.UndoManager
import util.Command
import GameStatus._

class Controller(var game: Game) extends Observable {
    val undoManager = new UndoManager[Game]
    var game1 = new Game(0,new Mesh(0,0,0),0,0,0,0)
    var mesh1 = new Mesh(0,0,0)
    var gamestatus: GameStatus = IDLE

    def doAndPublish(doThis: (Int) => Game,rolledDice:Int) = {
        undoManager.doStep(game,SetCommand(rolledDice))
        //game = game.copy()
        game = doThis(rolledDice)
        notifyObservers
    }
    def doAndPublish(doThis: => Game) = {
        game = doThis
        notifyObservers
    }

    def getOut1(rolledDice:Int): Game = {
        //game1 = game.copy()
        //mesh1 = mesh
        game.getOut(rolledDice)
    }
    def move1(rolledDice:Int):Game = { 
        //game1 = game.copy()
        //mesh1 = mesh
        game.move(rolledDice)
    }
    def checkinput1(rolledDice:Int):Game = {
            //mesh1 = game.mesh10.copy()
            game1 = game.copy()
            game.checkinput(rolledDice)
    }
    def put(rolledDice:Int): Game = undoManager.doStep(game, SetCommand(rolledDice))
    def undo: Game = {
        gamestatus = UNDO
        //print(gamestatus.message(gamestatus))
        undoManager.undoStep(game)
    }
    def redo: Game = {
        gamestatus = REDO
        print(gamestatus.message(gamestatus))
        undoManager.redoStep(game)
    }
}