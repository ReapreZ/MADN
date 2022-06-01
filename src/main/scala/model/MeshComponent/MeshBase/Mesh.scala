package model.meshComponent.meshBase
import scala.collection.mutable.ListBuffer
import model.meshComponent.meshBase._
import model.FieldFactory
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}

final case class Mesh(Cell: Int, Player: Int, Housenumber: Int){
    val field1 = FieldFactory("field",Cell, Player)
    val house1 = FieldFactory("house",Housenumber, Player)
    val finish1 = FieldFactory("finish",Housenumber, Player)
    val piecepos = fillArr(Player, Housenumber)
    val stepsdone = fillArr(Player, Housenumber)
    
    def mesh():String = field1.toString() + house1.toString() + finish1.toString()

    def fillArr(playeramount:Int, houseamount:Int): Array[Array[Int]] = {
        val arr = Array.ofDim[Int](playeramount, houseamount)
        var temp = 0
        var temp2 = 0
        while(temp < playeramount)
            temp2 = 0
            while(temp2 < houseamount)
                arr(temp)(temp2) = -1
                temp2 = temp2 + 1
            temp = temp + 1
        return arr
    }

    def startgame(): Try[Mesh] = {
        println("Amount of Players:")
        val input = readLine()
        if input.toInt < 1 && input.toInt > 4 then return Failure(NotImplementedError("Too Many/Few Player"))
        else 
            val playeramount = input.toInt
            println("Amount of Houses:")
            val houseamount = readLine()
            println("Amount of Cells per Player:")
            val cellamount = readLine()
            println("Press 'r' to roll the dice\n")
            return Success(Mesh(cellamount.toInt, playeramount.toInt, houseamount.toInt))
    }

}
