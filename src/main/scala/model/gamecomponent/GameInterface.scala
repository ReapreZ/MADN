package model.gamecomponent
import model.meshcomponent.meshbase.Mesh
import model.gamecomponent.gamebase._
import scala.util.{Try,Success,Failure}
import model.fileIOComponent.fileIOJsonImpl.FileIO
import model.fileIOComponent.FileIOInterface
import model.playercomponent.Player

trait GameInterface {
    
    var pieceChooser: Int

    def move(rolledDice:Int): Game
    def getTurnC(playerturn: Int): Try[Char]
    def undoMove(rolledDice: Int, playerturnt: Int, piece: Int): Game
    def movePiece(rolledDice: Int, piece: Int) : Game
    def movePieceOut(): Game
    //def changePlayerTurn(playerturnT: Int): Game
    //def changeMap(stelle: Int, amount: Int): Map[Int,Int]
    def changeList(list: List[Int], index: Int, value: Int): List[Int]
    def put(game: Game): Game
    def isFieldOccupied(rolledDice: Int, piece:Int): Game

    val playerturn: Int
    val mesh: Mesh
    val piecesOutList: List[Int]
    def startgame: Try[Game]
}
