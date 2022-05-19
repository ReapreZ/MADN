/*package model.MeshComponent.MeshBase

import model.FieldComponent.FieldBase.Field
import model.HouseComponent.HouseBase.House
import model.FinishComponent.FinishBase.Finish

trait Mesh {
    def field1: Field
    def house1: House
    def finish1: Finish
    def piecepos : Array[Array[Int]]
    def stepsdone : Array[Array[Int]]
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
class SimpleMesh extends Mesh {
    override def field1 = Field(1,1)
    override def house1 = House(1,1)
    override def finish1 = Finish(1,1)
    override def piecepos = fillArr(1,1)
    override def stepsdone = fillArr(1,1)
}

abstract class MeshDecorator(decoratedMesh: Mesh) extends Mesh {
    override def field1 = decoratedMesh.field1
    override def house1 = decoratedMesh.house1
    override def finish1 = decoratedMesh.finish1
    override def piecespos = decoratedMesh.piecepos
    override def stepsdone = decoratedMesh.stepsdone 
}

trait twoPtwoHtenF extends MeshDecorator {
    override def field1 = Field(10,2)
    override def house1 = House(2,2)
    override def finish1 = Finish(2,2)
    override def piecepos = fillArr(2,2)
    override def stepsdone = fillArr(2,2)
}
trait threePthreeHeightF extends MeshDecorator {
    override def field1 = Field(8,3)
    override def house1 = House(3,3)
    override def finish1 = Finish(3,3)
    override def piecepos = fillArr(3,3)
    override def stepsdone = fillArr(3,3)
}
trait twoPtwoHfourF extends MeshDecorator {
    override def field1 = Field(4,2)
    override def house1 = House(2,2)
    override def finish1 = Finish(2,2)
    override def piecepos = fillArr(2,2)
    override def stepsdone = fillArr(2,2)
}*/