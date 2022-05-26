package controller

import controller._
import util.Command
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game
import model.Move

class SetCommand(playerturn: Int, mesh10: Mesh, piecesOutMap:Map[Int,Int], controller: Controller/*move: Move*/) extends Command[Game] {
  
  override def noStep(game: Game): Game = game
  override def doStep(game: Game): Game = controller.game.put(playerturn, mesh10, piecesOutMap)
  override def undoStep(game: Game): Game = controller.game.put(playerturn, mesh10, piecesOutMap)
  override def redoStep(game: Game): Game = controller.game.put(playerturn, mesh10, piecesOutMap)
}
