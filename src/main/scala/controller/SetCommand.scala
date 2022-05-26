package controller

import controller._
import util.Command
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game
import model.Move


class SetCommand(move:Move) extends Command[Game]:
  
  override def noStep(game: Game): Game = game
  override def doStep(game: Game): Game = game.put(move.rolledDice,move.piece)
  override def undoStep(game: Game): Game = game.put(-move.rolledDice,move.piece)
  override def redoStep(game: Game): Game = game.put(move.rolledDice,move.piece)

