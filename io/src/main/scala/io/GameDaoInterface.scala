package io

import model.gameComponent.GameInterface
import scala.concurrent.Future

trait GameDaoInterface {
  def create: Unit
  def read:Future[String]
  def update(id: Int, playerturn: Int, mesh: String, piecesOut: String, timesPlayerRolled: String):Unit
  def delete: Unit
}