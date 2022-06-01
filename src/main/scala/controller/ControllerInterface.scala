package controller

import scala.swing.Publisher
import model.gameComponent.gameBase.Game
import model.Move
import scala.util.Try
import util.Observable

trait ControllerInterface extends Observable{

    def doAndPublish(doThis: => Game) : Unit
    def doAndPublish(doThis: (Int) => Game,rolledDice:Int): Unit
    //def doAndPublish(doThis: (Int) => Game,move:Move): Unit
    def move1(rolledDice:Int): Game
    def printPlayerTurn(): Unit
    def getTurnC1(playerturn: Int): Try[Char]
    def put(move:Move): Game
    def undo: Game
    def redo: Game
}
