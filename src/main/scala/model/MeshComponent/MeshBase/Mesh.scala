package model.MeshComponent.MeshBase
import scala.collection.mutable.ListBuffer
import model.FieldComponent.FieldBase.Field
import model.HouseComponent.HouseBase.House
import model.FinishComponent.FinishBase.Finish
import model.FieldFactory

final case class Mesh(Cell: Int, Player: Int, Housenumber: Int){
    val field1 = FieldFactory.apply("field",Cell, Player)
    val house1 = FieldFactory.apply("house",Housenumber, Player)
    val finish1 = FieldFactory.apply("finish",Housenumber, Player)
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

}
