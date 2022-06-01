package model.meshComponent.meshBase
import model.FieldFactory

final case class Finish(Amount: Int, Player: Int) extends FieldFactory{
    override val Arr = ((("-") * Amount + "  ") * Player).toArray
}