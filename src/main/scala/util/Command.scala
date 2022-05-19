/*package util

import model.MeshComponent.MeshBase.Mesh
import Controller._

trait Command {
  
    def rollDice:Unit
    def undoStep:Unit
    def redoStep:Unit
}

class SetCommand(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int, controller:Controller) extends Command{
override def rollDice: Unit = controller.game1.set()
override def undoStep: Unit = controller.game1.set()
override def redoStep: Unit = controller.game1.set()

}*/
package util

trait Command {

  def doStep: Unit
  def undoStep: Unit
  def redoStep: Unit

}





