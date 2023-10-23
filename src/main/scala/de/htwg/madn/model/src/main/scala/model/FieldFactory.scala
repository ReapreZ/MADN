package de.htwg.madn.model

import de.htwg.madn.model.meshComponent.meshBase._

trait FieldFactory {
    
    var Arr: Array[Char]
    val eol = sys.props("line.separator")

    override def toString(): String =  {
        Arr.mkString("") + eol
    }
}

object FieldFactory {
    def apply(kind: String, Player:Int) = kind match {
        case "field" => new Field(Player)
        case "house" => new House(Player)
        case "finish" => new Finish(Player)
    }
}
