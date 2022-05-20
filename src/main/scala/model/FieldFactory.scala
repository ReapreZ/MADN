package model

import model.FieldComponent.FieldBase.Field
import model.HouseComponent.HouseBase.House
import model.FinishComponent.FinishBase.Finish

trait FieldFactory() {
    
    val Arr = ("").toArray
    val eol = sys.props("line.separator")

    override def toString(): String =  {
        Arr.mkString("") + eol
    }
}

object FieldFactory {
    def apply(kind: String, Cell:Int, Player:Int) = kind match {
        case "field" => new Field(Cell, Player)
        case "house" => new House(Cell, Player)
        case "finish" => new Finish(Cell, Player)
    }
}
