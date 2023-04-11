package model.meshcomponent

import model.meshcomponent.meshbase._
import scala.util.Try

trait MeshInterface {
  def mesh(): String
  def fillArr(playeramount:Int, houseamount:Int): Array[Array[Int]]

}
