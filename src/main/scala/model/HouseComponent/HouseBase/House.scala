package model.HouseComponent.HouseBase
import model.Factory

case class House(Amount: Int, Player: Int) extends Factory {
    val houses = List("A","B","C","D")
    var temp = ""
    var i = 0
    while(i < Player)
        temp = temp + (houses(i)) * Amount + "  "
        i = i + 1
    override val Arr = temp.toArray

}
