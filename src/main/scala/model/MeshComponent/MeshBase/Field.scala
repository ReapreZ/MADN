package model.meshComponent.meshBase
import model.FieldFactory

final case class Field(Player: Int) extends FieldFactory {
    //override val Arr = (("x" + ("_" * Cell * Player)) * Player).toArray
    //Arr = ("x" + ("_" * 9)).toArray
    var Arr2 = " ".toArray
    Player match {
        case 0 => Arr2 = " ".toArray
        case 1 => Arr2 = ("x" + ("_" * 9) + ("_" * 30)).toArray
        case 2 => Arr2 = (("x" + ("_" * 9)) * 2 + ("_" * 20)).toArray
        case 3 => Arr2 = (("x" + ("_" * 9)) * 3 + ("_" * 10)).toArray
        case 4 => Arr2 = (("x" + ("_" * 9)) * 4).toArray
    }
    override val Arr = Arr2
}