package controllerComponent

import scala.swing.Publisher
import scala.util.Try
import util.Observable

trait ControllerInterface extends Observable{

    def doAndPublish(doThis: => GameInterface) : Unit
    def doAndPublish(doThis: (Int) => GameInterface,rolledDice:Int): Unit
    def move1(rolledDice:Int): GameInterface
    def printPlayerTurn(): Unit
    def getTurnC1(playerturn: Int): Try[Char]
    def put(move:Move): GameInterface
    def undo: GameInterface
    def redo: GameInterface
    def game: GameInterface
    def save: GameInterface
    def load: GameInterface
}
