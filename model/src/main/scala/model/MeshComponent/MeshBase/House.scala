package model.meshComponent.meshBase
import model.FieldFactory

case class House(Player: Int) extends FieldFactory {
    val houses = List("A","B","C","D")
    val temp = buildHouses(0, Player, houses, "")
    var Arr = temp.toArray
    def buildHouses(i: Int, player: Int, houses: List[String], temp: String): String = {
        if (i < player) {
            val newTemp = temp + (houses(i) * 4) + "  "
            buildHouses(i + 1, player, houses, newTemp)
        } else {
            temp
        }
    }
}
