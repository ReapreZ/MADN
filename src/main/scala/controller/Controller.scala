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
import model.Move

class Controller(var game: Game) extends Observable {
    val undoManager = new UndoManager[Game]
    var game1 = new Game(0,new Mesh(0,0,0),0,0,0,0)
    var mesh1 = new Mesh(0,0,0)
    var gamestatus: GameStatus = IDLE


    def doAndPublish(doThis: (Int) => Game,rolledDice:Int) = {
        game = doThis(rolledDice)
        notifyObservers
    }
    def doAndPublish(doThis: => Game) = {
        game = doThis
        notifyObservers
    }
    def getOut1(rolledDice:Int): Game = {
        game.getOut(rolledDice)
    }
    def move1(rolledDice:Int):Game = { 
        game.move(rolledDice)
    }
    def checkinput1(rolledDice:Int):Game = {
            //print(game.mesh10.mesh())
            //doAndPublish(put/*Move(output, mesh1)*/)
            game1 = game.copy()
            game.checkinput(rolledDice)
    }
    def put(/*move:Move*/): Game = undoManager.doStep(game, SetCommand(game.playerturn, game.mesh10, game.piecesOutA, game.piecesOutB, game.piecesOutC, game.piecesOutD, this/*move*/))
    def undo: Game = {
        gamestatus = UNDO
        print(gamestatus.map(gamestatus))
        undoManager.undoStep(game)
    }
    def redo: Game = {
        gamestatus = REDO
        print(gamestatus.map(gamestatus))
        undoManager.redoStep(game)
    }
}