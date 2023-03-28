package model.meshComponent.meshBase
import scala.collection.mutable.ListBuffer
import model.meshComponent.MeshInterface
import model.FieldFactory
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}
import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject}

final case class Mesh @Inject() (@Named("DefaultMesh") Player: Int) extends MeshInterface {
    val field1 = FieldFactory("field", Player)
    val house1 = FieldFactory("house", Player)
    val finish1 = FieldFactory("finish", Player)
    var piecepos = fillArr(Player, 4)
    var stepsdone = fillArr(Player, 4)
    
    def mesh():String = field1.toString() + house1.toString() + finish1.toString()

    def fillArr(playeramount:Int, houseamount:Int): Array[Array[Int]] = {
        var arr = Array.ofDim[Int](playeramount, houseamount)
        var temp = 0
        var temp2 = 0
        while(temp < playeramount)
            temp2 = 0
            while(temp2 < houseamount)
                arr(temp)(temp2) = -1
                temp2 = temp2 + 1
            temp = temp + 1
        arr
    }


}
