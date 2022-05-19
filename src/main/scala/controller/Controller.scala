package controller

import controller.SetCommand
import model.GameComponent.GameBase.Game
import model.MeshComponent.MeshBase.Mesh
import model.DiceComponent.DiceBase.Dice
import scala.io.StdIn.readLine
import util.Observable
import util.UndoManager
import util.Command

class Controller(var game: Game) extends Observable {
    val undoManager = new UndoManager[Game]
    var game1 = new Game(0,new Mesh(0,0,0),0,0,0,0)
    var mesh1 = new Mesh(0,0,0)
    //var gamestatus: GameStatus = IDLE

    def doAndPublish(doThis: (Int) => Game,rolledDice:Int) = {
        //game = game.copy()
        game = doThis(rolledDice)
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


    def put(rolledDice:Int): Game = undoManager.doStep(game)
    def undo: Game = undoManager.undoStep(game)
    def redo: Game = undoManager.redoStep(game)

}