package model.meshComponent.meshBase
import model.FieldFactory

final case class Finish(Player: Int) extends FieldFactory{
    override val Arr = ((("-") * 4 + "  ") * Player).toArray
}