package controller

import controller._
import util.Command
import model.MeshComponent.MeshBase.Mesh

class SetCommand(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int, controller: Controller) extends Command {
  override def doStep: Unit =   controller.game = controller.game.set(playerturn, mesh10, piecesOutA, piecesOutB, piecesOutC, piecesOutD)

  override def undoStep: Unit = controller.game = controller.game.set(playerturn, mesh10, piecesOutA, piecesOutB, piecesOutC, piecesOutD)

  override def redoStep: Unit = controller.game = controller.game.set(playerturn, mesh10, piecesOutA, piecesOutB, piecesOutC, piecesOutD)
}
