package modelComponent.gameComponent

import scala.util.{Try,Success,Failure}

trait GameInterface {
    
    var pieceChooser: Int

    

    def move(rolledDice:Int): Game
    def getTurnC(playerturn: Int): Try[Char]
    def undoMove(rolledDice: Int, playerturnt: Int, piece: Int): Game
    def movePiece(rolledDice: Int, piece: Int) : Game
    def movePieceOut(): Game
    //def changePlayerTurn(playerturnT: Int): Game
    def changeMap(stelle: Int, amount: Int): Map[Int,Int]
    def put(game: Game): Game
    def getPiece(): String
    def isFieldOccupied(rolledDice: Int, piece:Int): Game
    val playerturn: Int
    val mesh10: Mesh
    val piecesOutMap: Map[Int,Int]
    def startgame: Try[Game]
}
