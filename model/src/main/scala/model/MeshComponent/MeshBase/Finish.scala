package model.meshComponent.meshBase
import model.FieldFactory

final case class Finish(Player: Int) extends FieldFactory{
    var Arr = ((("-") * 4 + "  ") * Player).toArray
}