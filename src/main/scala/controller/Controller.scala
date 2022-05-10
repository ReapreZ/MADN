package Controller

import model.{Mesh, Field, House, Dice, Game, Move}
import scala.io.StdIn.readLine
import util.Observable

class Controller(var mesh:Mesh) extends Observable {

    var game = new Game(0,new Mesh(0,0,0))

    //def doAndPublish(doThis: Move => Mesh, move: Move) =
    //mesh = doThis(move)
    //notifyObservers

    def getOut1(rolledDice:Int,game1:Game):Unit = {
        game = game.getOut(rolledDice,game1.mesh10)
        notifyObservers
    }

    def move1(rolledDice:Int,game1:Game):Unit = {
        game = game.move(rolledDice,game1.mesh10)
        notifyObservers
    }

    def checkinput1(rolledDice:Int,game1:Game):Unit = {
        game = game.checkinput(rolledDice,game1.mesh10)
        notifyObservers
    }

}