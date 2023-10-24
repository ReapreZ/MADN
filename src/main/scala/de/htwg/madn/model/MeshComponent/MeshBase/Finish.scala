package de.htwg.madn.model.meshComponent.meshBase

import de.htwg.madn.model.FieldFactory

final case class Finish(Player: Int) extends FieldFactory{
    var Arr = ((("-") * 4 + "  ") * Player).toArray
}