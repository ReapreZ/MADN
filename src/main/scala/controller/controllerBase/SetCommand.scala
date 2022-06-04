package controller

import controller._
import util.Command
import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase.Game
import model.gameComponent.GameInterface
import model.Move


class SetCommand(move:Move) extends Command[GameInterface]:
  
  override def noStep(game: GameInterface): GameInterface = game
  override def doStep(game: GameInterface): GameInterface = game.move(move.rolledDice)
  override def undoStep(game: GameInterface): GameInterface = game.undoMove(-move.rolledDice, move.playerturnT, move.piece)
  override def redoStep(game: GameInterface): GameInterface = game.undoMove(move.rolledDice, move.playerturnT, move.piece)

