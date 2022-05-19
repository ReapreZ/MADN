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
    private val undoManager = new UndoManager
    var game1 = new Game(0,new Mesh(0,0,0),0,0,0,0)
    var mesh1 = new Mesh(0,0,0)
    //var gamestatus: GameStatus = IDLE

    def doAndPublish(doThis: (Int) => Game,rolledDice:Int) = {
        //game = game.copy()
        game = doThis(rolledDice)
        notifyObservers
    }
    def set(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int): Unit = {
    undoManager.doStep(new SetCommand(playerturn, mesh10, piecesOutA, piecesOutB, piecesOutC, piecesOutD, this))
    //gameStatus = SET
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
    def undo: Unit = 
        undoManager.undoStep
}