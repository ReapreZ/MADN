package model.MeshComponent.MeshBase
import scala.collection.mutable.ListBuffer
import model.FieldComponent.FieldBase.Field
import model.HouseComponent.HouseBase.House
import model.FinishComponent.FinishBase.Finish


final case class Mesh(Cell: Int, Player: Int, Housenumber: Int) extends Strategy{
    val field1 = Field(Cell, Player)
    val house1 = House(Housenumber, Player)
    val finish1 = Finish(Housenumber, Player)
    val playeramount = Player
    val houseamount = Housenumber
    def mesh():String = field1.cell() + house1.housefield() + finish1.finishfield()

    
}
