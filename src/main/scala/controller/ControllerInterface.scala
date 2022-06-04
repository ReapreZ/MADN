package controller

import scala.swing.Publisher
import model.gameComponent.gameBase.Game
import model.gameComponent.GameInterface
import model.Move
import scala.util.Try
import util.Observable

trait ControllerInterface extends Observable{

    def doAndPublish(doThis: => GameInterface) : Unit
    def doAndPublish(doThis: (Int) => GameInterface,rolledDice:Int): Unit
    //def doAndPublish(doThis: (Int) => Game,move:Move): Unit
    def move1(rolledDice:Int): GameInterface
    def printPlayerTurn(): Unit
    def getTurnC1(playerturn: Int): Try[Char]
    def put(move:Move): GameInterface
    def undo: GameInterface
    def redo: GameInterface
    def game: GameInterface
}
