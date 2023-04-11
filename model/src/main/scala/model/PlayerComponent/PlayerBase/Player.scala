package model.PlayerComponent.PlayerBase

case class Player() {
  var activePieces : Int = 0
  var playerCharacter : Char = ' '
  
  def setActivePieces(newActivePieces: Int): Unit = {
    activePieces = newActivePieces
  }
}
