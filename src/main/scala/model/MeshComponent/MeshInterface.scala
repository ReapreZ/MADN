package model.meshComponent

import model.meshComponent.meshBase._
import scala.util.Try

trait MeshInterface {
  def mesh(): String
  def fillArr(playeramount:Int, houseamount:Int): Array[Array[Int]]
  def startgame(): Try[Mesh]
}
