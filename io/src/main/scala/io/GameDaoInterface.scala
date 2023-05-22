package io

import model.gameComponent.GameInterface

trait GameDaoInterface {
  def create: Unit
  def read:Unit
  def update(id: Int, playerturn: Int, mesh: String, piecesOut: String, timesPlayerRolled: String):Unit
  def delete: Unit
}