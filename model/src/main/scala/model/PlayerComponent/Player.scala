package de.htwg.madn.model.PlayerComponent

case class Player(playerTurn: Int,
                  piecesOutMap: List[Int] = List(0, 0, 0, 0),
                  timesPlayerRolledList: List[Int] = List(0, 0, 0, 0))


