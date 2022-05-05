package Controller

import model.{Mesh, Field, House, Dice, Game, Move}
import scala.io.StdIn.readLine
import util.Observable

class Controller(var mesh:Mesh) extends Observable {

    val game = new Game

    def doAndPublish(doThis: Move => Mesh, move: Move) =
    mesh = doThis(move)
    notifyObservers

    def getOut1(rolledDice:Int,mesh1:Mesh):Unit = {
        mesh = game.getOut(rolledDice,mesh1)
        notifyObservers
    }

    def move1(rolledDice:Int,mesh1:Mesh):Unit = {
        mesh = game.move(rolledDice,mesh1)
        notifyObservers
    }

    def checkinput1(rolledDice:Int,mesh1:Mesh):Unit = {
        mesh = game.checkinput(rolledDice,mesh1)
        notifyObservers
    }



}