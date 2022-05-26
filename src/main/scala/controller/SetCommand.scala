package controller

import controller._
import util.Command
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game
import model.Move


class SetCommand(move:Move) extends Command[Game]:
  
  override def noStep(game: Game): Game = game
  override def doStep(game: Game): Game = game.move(move.rolledDice)
  override def undoStep(game: Game): Game = game.undoMove(-move.rolledDice, move.playerturnT, move.piece)
  override def redoStep(game: Game): Game = game.undoMove(move.rolledDice, move.playerturnT, move.piece)

