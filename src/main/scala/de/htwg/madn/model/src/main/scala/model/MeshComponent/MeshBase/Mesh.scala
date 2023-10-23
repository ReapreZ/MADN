package de.htwg.madn.model.meshComponent.meshBase

import de.htwg.madn.model.meshComponent.MeshInterface
import de.htwg.madn.model.FieldFactory
import scala.collection.mutable.ListBuffer
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

    override def fromString(mesh: String) : Unit = { field1.toString() + house1.toString() + finish1.toString()
    }
    def fillArr(playeramount: Int, houseamount: Int): Array[Array[Int]] = {
        def fill(arr: Array[Array[Int]], i: Int, j: Int): Unit = {
            if (i < playeramount) {
                if (j < houseamount) {
                    arr(i)(j) = -1
                    fill(arr, i, j + 1)
                } else {
                    fill(arr, i + 1, 0)
                }
            }
        }
        val arr = Array.ofDim[Int](playeramount, houseamount)
        fill(arr, 0, 0)
        arr
    }


}
