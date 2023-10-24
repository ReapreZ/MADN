package de.htwg.madn.controller

import de.htwg.madn.model.gameComponent.gameBase.Game
import de.htwg.madn.model.gameComponent.GameInterface
import de.htwg.madn.model.Move
import de.htwg.madn.util.Observable
import scala.swing.Publisher
import scala.util.Try

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
