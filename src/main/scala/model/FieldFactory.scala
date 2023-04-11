package model

import model.meshcomponent.meshbase._

trait FieldFactory {

    var Arr = ("").toArray
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
