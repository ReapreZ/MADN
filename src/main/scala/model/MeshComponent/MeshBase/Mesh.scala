package model.MeshComponent.MeshBase
import scala.collection.mutable.ListBuffer
import model.FieldComponent.FieldBase.Field
import model.HouseComponent.HouseBase.House
import model.FinishComponent.FinishBase.Finish


final case class Mesh(Cell: Int, Player: Int, Housenumber: Int){
    val field1 = Field(Cell, Player)
    val house1 = House(Housenumber, Player)
    val finish1 = Finish(Housenumber, Player)
    val playeramount = Player
    val houseamount = Housenumber
    var temp = 0
    var temp2 = 0
    val stepsdone = Array.ofDim[Int](playeramount, houseamount)
    while(temp < playeramount)
        temp2 = 0
        while(temp2 < houseamount)
            stepsdone(temp)(temp2) = -1
            temp2 = temp2 + 1
        temp = temp + 1
    val piecepos = Array.ofDim[Int](playeramount, houseamount)
    temp = 0
    while(temp < playeramount)
        temp2 = 0
        while(temp2 < houseamount)
            piecepos(temp)(temp2) = -1
            temp2 = temp2 + 1
        temp = temp + 1
    def mesh():String = field1.cell() + house1.housefield() + finish1.finishfield()

}
