package de.htwg.madn.controller

import de.htwg.madn.controller._
import de.htwg.madn.util.Command
import de.htwg.madn.model.meshComponent.meshBase.Mesh
import de.htwg.madn.model.gameComponent.gameBase.Game
import de.htwg.madn.model.gameComponent.GameInterface
import de.htwg.madn.model.Move


class SetCommand(move:Move) extends Command[GameInterface] {
  
  override def noStep(game: GameInterface): GameInterface = game
  override def doStep(game: GameInterface): GameInterface = game.move(move.rolledDice)
  override def undoStep(game: GameInterface): GameInterface = {
    val result = game.undoMove(-move.rolledDice, move.playerturnT, move.piece)
    result.getOrElse(game) }
  override def redoStep(game: GameInterface): GameInterface = {
    val result = game.undoMove(move.rolledDice, move.playerturnT, move.piece)
    result.getOrElse(game) }

  }
