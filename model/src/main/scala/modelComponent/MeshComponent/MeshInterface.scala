package modelComponent.meshComponent

import scala.util.Try

trait MeshInterface {
  def mesh(): String
  def fillArr(playeramount:Int, houseamount:Int): Array[Array[Int]]

}
