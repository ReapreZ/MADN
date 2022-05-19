package controller

import controller._
import util.Command
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game

class SetCommand(rolledDice:Int) extends Command[Game] {
  
  override def noStep(game: Game): Game = game
  override def doStep(game: Game): Game = game.put(movePiece.rolledDice,movePiece.piece)
  override def undoStep(game: Game): Game = game.put()
  override def redoStep(game: Game): Game = game.put()
}
