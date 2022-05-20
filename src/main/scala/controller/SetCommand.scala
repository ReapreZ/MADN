package controller

import controller._
import util.Command
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game

class SetCommand(rolledDice:Int) extends Command[Game] {
  
  override def noStep(game: Game): Game = game
  override def doStep(game: Game): Game = game.put(game.playerturn, game.mesh10, game.piecesOutA, game.piecesOutB, game.piecesOutC, game.piecesOutD)
  override def undoStep(game: Game): Game = game.put(game.playerturn, game.mesh10, game.piecesOutA, game.piecesOutB, game.piecesOutC, game.piecesOutD)
  override def redoStep(game: Game): Game = game.put(game.playerturn, game.mesh10, game.piecesOutA, game.piecesOutB, game.piecesOutC, game.piecesOutD)
}
