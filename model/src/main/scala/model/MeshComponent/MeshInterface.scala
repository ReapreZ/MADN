package de.htwg.madn.model.meshComponent

import de.htwg.madn.model.meshComponent.meshBase._
import scala.util.Try

trait MeshInterface {
  def mesh(): String
  def fromString(mesh: String): Unit
  def fillArr(playeramount:Int, houseamount:Int): Array[Array[Int]]

}
