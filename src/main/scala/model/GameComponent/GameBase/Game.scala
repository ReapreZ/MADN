package model.GameComponent.GameBase
import model.MeshComponent.MeshBase.Mesh
import scala.io.StdIn.readLine
import controller.Controller
import scala.util.{Try,Success,Failure}
//import model.GameComponent.GameBase.PlayerOutState
//import model.GameComponent.GameBase.Event


case class Game(playerturn:Int,mesh10:Mesh,piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)) extends GameStrategy {
	var out: Int = -1
	def getOut(rolledDice: Int): Game = {
		//val game = move(rolledDice)
		val playerTurnC = getTurnC(playerturn)
		//val out = game.mesh10.field1.Arr.indexOf(playerTurnC)
		playerTurnC match {
				case Success(v) => out = mesh10.field1.Arr.indexOf(v.toChar)
				case Failure(e) => println(e.getMessage)
			}
		if(rolledDice == 6) {
			if(out == -1)
				//PlayerOutState.changeState(OnEvent)
				//PlayerOutState.handle(PlayerOutState.state)
			//println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
			//println("Player A can move out one Piece\n")
				println("You can roll again\n")
				return movePieceOut()
			else 
				println("Which Piece should be moved or which Piece should come out?")
				val input = readLine()
				return moveOrGetOut(input.toInt,piecesOutMap(playerturn + 1))
		} else{
			//PlayerOutState.state = 0
			//PlayerOutState.handle(PlayerOutState.state)
			if(playerturn == mesh10.Player)
				return copy(playerturn = 1)
			else 
				val playerturn1 = playerturn + 1
				return copy(playerturn1, mesh10)
		}
		return copy()
	}

	def checkinput(rolledDice: Int): Game = {
		val game = getOut(rolledDice)
		getTurnC(game.playerturn) match {
			case Success(v) => println("It is Player " + v + "'s turn\n")
			case Failure(e) => println(e.getMessage)
		}
		//println("It is Player " + getTurnC(game.playerturn) + "'s turn\n")
		return game.copy()
	}

	def move(rolledDice: Int): Game = {
		println("A: " + piecesOutMap(0) + " B: " + piecesOutMap(1) + " C: " + piecesOutMap(2) + " playerturn: " + playerturn)
		val playerTurnC = getTurnC(playerturn)
		//val out = mesh10.field1.Arr.indexOf(playerTurnC)
			playerTurnC match {
				case Success(v) => out = mesh10.field1.Arr.indexOf(v)
				case Failure(e) => println(e.getMessage)
			}
		if(out != -1)
			if(rolledDice != 6 && piecesOutMap(playerturn + 1) == 1)
				val game = movePiece(rolledDice, piecesOutMap(playerturn + 1))
				return game.copy()
			/*if (rolledDice == 6)
				println("Which Piece should be moved or which Piece should come out?")
				val input = readLine()
				return moveOrGetOut(input.toInt, piecesOutMap(playerturn + 1))*/
			if(rolledDice != 6)
				println("Which Piece should be moved?")
				val input = readLine()
				if (input.toInt <= mesh10.Housenumber)
					if (mesh10.stepsdone(playerturn - 1)(input.toInt - 1) == -1)
						if(input.toInt >= piecesOutMap(playerturn + 1))
							val game1 = movePieceOut()
							return game1
					else return movePiece(rolledDice, input.toInt)
				else
					println("This Piece isnt out yet")
					val game = move(rolledDice)
					return game.copy()
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
			case _ => Success(' ')
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
		val nextPlayer = mesh10.Player * mesh10.Cell + 1
		val nextHouse = mesh10.Housenumber + 2
		playerturn match {
			case 1 =>
				if(piecesOutMap(0) <= mesh10.Housenumber) {
					mesh10.stepsdone(0)(piecesOutMap(0)) = 0
					mesh10.piecepos(0)(piecesOutMap(0)) = 0
					mesh10.field1.Arr(0) = 'A'
					mesh10.house1.Arr(piecesOutMap(0)) = 'H'
					return copy(piecesOutMap = changeMap(0))
				} else 
					return move(6)
			case 2 =>
				if(piecesOutMap(1) <= mesh10.Housenumber) {
					mesh10.stepsdone(1)(piecesOutMap(1)) = 0
					mesh10.piecepos(1)(piecesOutMap(1)) = nextPlayer
					mesh10.field1.Arr(nextPlayer) = 'B'
					mesh10.house1.Arr(nextHouse + piecesOutMap(1)) = 'H'
					return copy(piecesOutMap = changeMap(1))
				} else
					return move(6)
			case 3 => 
				if(piecesOutMap(2) <= mesh10.Housenumber) {
					mesh10.stepsdone(2)(piecesOutMap(2)) = 0
					mesh10.piecepos(2)(piecesOutMap(2)) = nextPlayer * 2
					mesh10.field1.Arr(nextPlayer * 2) = 'C'
					mesh10.house1.Arr(nextHouse*2 + piecesOutMap(2)) = 'H'
					return copy(piecesOutMap = changeMap(2))
				} else 
					return move(6)
			case 4 =>
				if(piecesOutMap(3) <= mesh10.Housenumber) {
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
		if(piece > piecesOut && piece != mesh10.Housenumber + 1)
			return movePieceOut()
		else return movePiece(6,piece)
	}

	def changeMap(stelle:Int): Map[Int,Int] = {
		var changedMap: scala.collection.mutable.Map[Int,Int] = scala.collection.mutable.Map(piecesOutMap.toSeq: _*)
		changedMap(stelle) = piecesOutMap(stelle) + 1
		return changedMap.toMap
	}

	def put(rolledDice:Int,piece:Int) = copy(rolledDice,mesh10)
	}

// Wenn einer draußen ist sollte nicht ein anderer rauskommen können
//Try/Option bei falschem Einput


// Bis nächstes mal Pattern 1 Abgabe() + Pattern 2 (Redo,Undo,Trait,Option)
