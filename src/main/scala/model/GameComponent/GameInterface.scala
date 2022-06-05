package model.gameComponent
import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase._
import scala.util.{Try,Success,Failure}

trait GameInterface {

    var out: Int
    var pieceChooser: Int
    var input: String

    def move(rolledDice:Int): Game
    def getTurnC(playerturn: Int): Try[Char]
    def undoMove(rolledDice: Int, playerturnt: Int, piece: Int): Game
    def movePiece(rolledDice: Int, piece: Int) : Game
    def movePieceOut(): Game
    def moveOrGetOut(piece: Int, piecesOut: Int): Game
    def changePlayerTurn(playerturnT: Int): Game
    def changeMap(stelle: Int): Map[Int,Int]
    def put(game: Game): Game
    def getPiece(): String
    val playerturn: Int
    val mesh10: Mesh
    val piecesOutMap: Map[Int,Int]
    def startgame: Try[Game]
}
