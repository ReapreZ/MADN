package model.meshcomponent.meshbase

import model.FieldFactory

final case class Finish(Player: Int) extends FieldFactory{
    override val Arr = ((("-") * 4 + "  ") * Player).toArray
}