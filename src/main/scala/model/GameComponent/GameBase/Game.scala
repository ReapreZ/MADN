package model.gameComponent.gameBase
import model.meshComponent.meshBase.Mesh
import model.gameComponent.GameInterface
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}


case class Game(playerturn:Int,mesh10:Mesh,piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)) extends GameInterface {
	var out: Int = -1
	var pieceChooser: Int = -1
	var input = " "

	def move(rolledDice: Int): Game = {
		println("A: " + piecesOutMap(0) + " B: " + piecesOutMap(1) + " C: " + piecesOutMap(2) + " playerturn: " + playerturn)
		val playerTurnC = getTurnC(playerturn)
		playerTurnC match {
				case Success(v) => out = mesh10.field1.Arr.indexOf(v.toChar)
				case Failure(e) => println(e.getMessage)
			}
		if(rolledDice != 6) // Wenn keine 6 gewürfelt wird
			if(piecesOutMap(playerturn - 1) == 1) // Nur einer draußen und keine 6
				val game1 = movePiece(rolledDice, 1)
				if(playerturn == mesh10.Player)
					return copy(playerturn = 1)
				else
					val playerturn1 = playerturn + 1 
					return copy(playerturn = playerturn1)
			else if(piecesOutMap(playerturn - 1) == 0) // Keiner draußen und keine 6
				if(playerturn == mesh10.Player)
					return copy(playerturn = 1)
				else
					val playerturn1 = playerturn + 1 
					return copy(playerturn = playerturn1)
				return copy(piecesOutMap = changeMap(playerturn - 1))
			else // Mehrere draußen und keine 6
				println("Which Piece should move?")
				if(pieceChooser == -1)
					input = readLine()
				else
					input = getPiece()
				val game1 = movePiece(rolledDice, input.toInt)
				if(playerturn == mesh10.Player)
					return copy(playerturn = 1)
				else
					val playerturn1 = playerturn + 1 
					return copy(playerturn = playerturn1)
				return game1
			return copy()
		else // Wenn eine 6 gewürfelt wird
			if(piecesOutMap(playerturn - 1) == 0)
				return movePieceOut()
			else
				println("Which Piece should move or get out?")
				println("pieceChooser: " + pieceChooser)
				if(pieceChooser == -1)
					input = readLine()
				else
					input = getPiece()
					//pieceChooser = 0
				if(input.toInt <= 4)
				piecesOutMap.get(playerturn - 1) match {
					case Some(piece) => return moveOrGetOut(input.toInt, piece)
					case None => return move(rolledDice)
				}
				else move(rolledDice)

	}
	def undoMove(rolledDice: Int, playerturnt: Int, piece: Int): Game = {
		val playerTurnC = getTurnC(playerturnt)
		mesh10.field1.Arr(mesh10.piecepos(playerturnt - 1)(piece - 1)) = ('_')
		playerTurnC match {
			case Success(v) => mesh10.field1.Arr((mesh10.piecepos(playerturnt - 1)(piece - 1)) + rolledDice) = v.toChar
			case Failure(e) => println(e.getMessage)
		}
		mesh10.stepsdone(playerturnt - 1)(piece - 1) = (mesh10.stepsdone(playerturnt - 1)(piece - 1)) + rolledDice
		mesh10.piecepos(playerturnt - 1)(piece - 1) = (mesh10.piecepos(playerturnt - 1)(piece - 1)) + rolledDice
		return copy()
	}

	def getTurnC(playerturn: Int): Try[Char] = {
		if playerturn < 0 && playerturn > 4 then return Failure(NoSuchMethodException("Something went wrong"))
		else
		playerturn match {
			case 1 => Success('A')
			case 2 => Success('B')
			case 3 => Success('C')
			case 4 => Success('D')
			case _ => Success('A')
		}
	}
	def movePiece(rolledDice:Int, piece:Int): Game = {
		val playerTurnC = getTurnC(playerturn)
		mesh10.field1.Arr(mesh10.piecepos(playerturn - 1)(piece - 1)) = ('_')
		playerTurnC match {
			case Success(v) => mesh10.field1.Arr((mesh10.piecepos(playerturn - 1)(piece - 1)) + rolledDice) = v.toChar
			case Failure(e) => println(e.getMessage)
		}
		mesh10.stepsdone(playerturn - 1)(piece - 1) = (mesh10.stepsdone(playerturn - 1)(piece - 1)) + rolledDice
		mesh10.piecepos(playerturn - 1)(piece - 1) = (mesh10.piecepos(playerturn - 1)(piece - 1)) + rolledDice
		return copy()
	}
	
	def movePieceOut(): Game = {
		val nextPlayer = 10
		val nextHouse = 4 + 2
		playerturn match {
			case 1 =>
				if(piecesOutMap(0) <=4) {
					mesh10.stepsdone(0)(piecesOutMap(0)) = 0
					mesh10.piecepos(0)(piecesOutMap(0)) = 0
					mesh10.field1.Arr(0) = 'A'
					mesh10.house1.Arr(piecesOutMap(0)) = 'H'
					return copy(piecesOutMap = changeMap(0))
				} else 
					return move(6)
			case 2 =>
				if(piecesOutMap(1) <= 4) {
					mesh10.stepsdone(1)(piecesOutMap(1)) = 0
					mesh10.piecepos(1)(piecesOutMap(1)) = nextPlayer
					mesh10.field1.Arr(nextPlayer) = 'B'
					mesh10.house1.Arr(nextHouse + piecesOutMap(1)) = 'H'
					return copy(piecesOutMap = changeMap(1))
				} else
					return move(6)
			case 3 => 
				if(piecesOutMap(2) <= 4) {
					mesh10.stepsdone(2)(piecesOutMap(2)) = 0
					mesh10.piecepos(2)(piecesOutMap(2)) = nextPlayer * 2
					mesh10.field1.Arr(nextPlayer * 2) = 'C'
					mesh10.house1.Arr(nextHouse*2 + piecesOutMap(2)) = 'H'
					return copy(piecesOutMap = changeMap(2))
				} else 
					return move(6)
			case 4 =>
				if(piecesOutMap(3) <= 4) {
					mesh10.stepsdone(3)(piecesOutMap(3)) = 0
					mesh10.piecepos(3)(piecesOutMap(3)) = nextPlayer * 3
					mesh10.field1.Arr(nextPlayer * 3) = 'D'
					mesh10.house1.Arr(nextHouse*3 + piecesOutMap(3)) = 'H'
					return copy(piecesOutMap = changeMap(3))
				} else 
					return move(6)
		}
	}

	def moveOrGetOut(piece:Int, piecesOut:Int): Game = {
		if(piece > piecesOut && piece != 4 + 1)
			return movePieceOut()
		else return 
			movePiece(6,piece)
	}

	def changePlayerTurn(playerturnT: Int) : Game = {
		if(playerturnT == mesh10.Player)
				return copy(playerturn = 1)
		else
			val playerturn1 = playerturnT + 1 
			return copy(playerturn = playerturn1)
	}

	def changeMap(stelle:Int): Map[Int,Int] = {
		var changedMap: scala.collection.mutable.Map[Int,Int] = scala.collection.mutable.Map(piecesOutMap.toSeq: _*)
		changedMap(stelle) = piecesOutMap(stelle) + 1
		return changedMap.toMap
	}
	def put(game: Game): Game = {
		return game.copy()
	}

	def getPiece(): String = {
		if(pieceChooser == 0)
			getPiece()
		return pieceChooser.toString
	}
	}
// Am Anfang 3x würfeln
// Wenn einer draußen ist sollte nicht ein anderer rauskommen können
