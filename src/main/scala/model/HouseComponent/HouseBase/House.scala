package model.HouseComponent.HouseBase
import model.FieldFactory

case class House(Amount: Int, Player: Int) extends FieldFactory {
    val houses = List("A","B","C","D")
    var temp = ""
    var i = 0
    while(i < Player)
        temp = temp + (houses(i)) * Amount + "  "
        i = i + 1
    override val Arr = temp.toArray

}
