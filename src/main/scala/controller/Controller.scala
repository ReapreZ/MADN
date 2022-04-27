package Controller

import model.{Mesh, Field, House, Dice, Game}
import scala.io.StdIn.readLine
import util.Observable

class Controller(var mesh:Mesh) extends Observable {

    def getOut1(rolledDice:Int):Unit = {
        mesh = Game.getOut(rolledDice)
        notifyObservers
    }

    def move1(rolledDice:Int):Unit = {
        mesh = Game.move(rolledDice)
        notifyObservers
    }

    def checkinput1(input: String,rolledDice:Int):Unit = {
        mesh = Game.checkinput(input,rolledDice)
        notifyObservers
    }


}