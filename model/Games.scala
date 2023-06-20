package model

import model.gameComponent.GameInterface

final case class Games(id: Int, playerturn: Int,mesh: String, piecesOutList: String, timesPlayerRolledList: String) {


    def createGame(game: GameInterface) : Games = {
        val newId: Int = 1
        val newPlayerturn: Int = game.getPlayerturn
        val newMesh: String = game.getMesh.mesh()
        val newPiecesOutList: String = game.getPiecesOutList.apply(0).toString + game.getPiecesOutList.apply(1).toString + game.getPiecesOutList.apply(2).toString + game.getPiecesOutList.apply(3).toString
        val newTimesPlayerRolledList: String = game.getTimesPlayerRolledList().apply(0).toString + game.getTimesPlayerRolledList().apply(1).toString + game.getTimesPlayerRolledList().apply(2).toString + game.getTimesPlayerRolledList().apply(3).toString
        copy(newId, newPlayerturn, newMesh, newPiecesOutList, newTimesPlayerRolledList)
    }

    def getId = id
    def getPlayerturn = playerturn
    def getMesh = mesh
    def getPiecesOutList = piecesOutList
    def getTimesPlayerRolledList = timesPlayerRolledList
}
