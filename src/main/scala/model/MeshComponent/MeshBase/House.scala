package model.meshComponent.meshBase
import model.FieldFactory

case class House(Player: Int) extends FieldFactory {
    val houses = List("A","B","C","D")
    var temp = ""
    var i = 0
    while(i < Player)
        temp = temp + (houses(i)) * 4 + "  "
        i = i + 1
    override val Arr = temp.toArray

}
