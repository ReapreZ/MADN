package de.htwg.madn.model.meshComponent.meshBase

import de.htwg.madn.model.FieldFactory

final case class Field(Player: Int) extends FieldFactory {
    
    var Arr = ("").toArray
    var Arr2 = " ".toArray
    Player match {
        case 0 => Arr2 = " ".toArray
        case 1 => Arr2 = ("x" + ("_" * 9) + ("_" * 30)).toArray
        case 2 => Arr2 = (("x" + ("_" * 9)) * 2 + ("_" * 20)).toArray
        case 3 => Arr2 = (("x" + ("_" * 9)) * 3 + ("_" * 10)).toArray
        case 4 => Arr2 = (("x" + ("_" * 9)) * 4).toArray
    }
    Arr = Arr2
}