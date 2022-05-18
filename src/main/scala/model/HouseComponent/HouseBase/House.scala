package model.HouseComponent.HouseBase
import scala.language.postfixOps
case class House(Amount: Int, Player: Int){
    val houses = List("A","B","C","D")
    var temp = ""
    var i = 0
    while(i < Player)
        temp = temp + (houses(i)) * Amount + "  "
        i = i + 1
    val hArr = temp.toArray    
    //val hArr = ((("H") * Amount + "  ") * Player).toArray
    val eol = sys.props("line.separator")
    def housefield():String =
        hArr.mkString("") + eol

}
