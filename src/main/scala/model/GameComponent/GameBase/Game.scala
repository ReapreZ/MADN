package model.GameComponent.GameBase
import model.MeshComponent.MeshBase.Mesh
import scala.io.StdIn.readLine
import controller.Controller
import scala.util.{Try,Success,Failure}
//import model.GameComponent.GameBase.PlayerOutState
//import model.GameComponent.GameBase.Event


case class Game(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int) extends GameStrategy {
	var out: Int = -1
	def getOut(rolledDice: Int): Game = {
		val game = move(rolledDice)
		val playerTurnC = getTurnC(playerturn)
		//val out = game.mesh10.field1.Arr.indexOf(playerTurnC)
		playerTurnC match {
				case Success(v) => out = game.mesh10.field1.Arr.indexOf(v.toChar)
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
		} else{
			//PlayerOutState.state = 0
			//PlayerOutState.handle(PlayerOutState.state)
			if(game.playerturn == game.mesh10.Player)
				return copy(playerturn = 1)
			else 
				val playerturn1 = game.playerturn + 1
				return copy(playerturn1, game.mesh10)
		}
		return game.copy()
	}

	def checkinput(rolledDice: Int): Game = {
		val game = getOut(rolledDice)
		getTurnC(game.playerturn) match {
			case Success(v) => println("It is Player " + v + "'s turn\n")
			case Failure(e) => println(e.getMessage)
		}
		//println("It is Player " + getTurnC(game.playerturn) + "'s turn\n")
		//println(game.mesh10.mesh())
		return game.copy()
	}

	def move(rolledDice: Int): Game = {
		println("A: " + piecesOutA + " B: " + piecesOutB + " C: " + piecesOutC + " playerturn: " + playerturn)
		val playerTurnC = getTurnC(playerturn)
		//val out = mesh10.field1.Arr.indexOf(playerTurnC)
			playerTurnC match {
				case Success(v) => out = mesh10.field1.Arr.indexOf(v)
				case Failure(e) => println(e.getMessage)
			}
		if(out != -1)
			if(rolledDice != 6 && getPiecesOut() == 1)
				val game = movePiece(rolledDice, getPiecesOut())
				return game.copy()
			if (rolledDice == 6)
				println("Which Piece should be moved or which Piece should come out?")
				val input = readLine()
				return moveOrGetOut(input.toInt, getPiecesOut())
			else
				println("Which Piece should be moved?")
				val input = readLine()
						if (input.toInt <= mesh10.Housenumber)
							if (mesh10.stepsdone(playerturn - 1)(input.toInt - 1) == -1)
								if(input.toInt >= getPiecesOut())
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
	/*def getTurnC(playerturn: Int): Char = {
		playerturn match {
			case 1 => 'A'
			case 2 => 'B'
			case 3 => 'C'
			case 4 => 'D'
			case _ => ' '
		}
	}*/
	def movePiece(rolledDice:Int, piece:Int): Game = {
		val playerTurnC = getTurnC(playerturn)
		mesh10.field1.Arr(mesh10.piecepos(playerturn - 1)(piece - 1)) = ('_')
		playerTurnC match {
			case Success(v) => mesh10.field1.Arr((mesh10.piecepos(playerturn - 1)(piece - 1)) + rolledDice) = v.toChar
			case Failure(e) => println(e.getMessage)
		}
		//mesh10.field1.Arr((mesh10.piecepos(playerturn - 1)(piece - 1)) + rolledDice) = playerTurnC
		mesh10.stepsdone(playerturn - 1)(piece - 1) = (mesh10.stepsdone(playerturn - 1)(piece - 1)) + rolledDice
		mesh10.piecepos(playerturn - 1)(piece - 1) = (mesh10.piecepos(playerturn - 1)(piece - 1)) + rolledDice
		return copy()
	}
	
	def movePieceOut(): Game = {
		val nextPlayer = mesh10.Player * mesh10.Cell + 1
		val nextHouse = mesh10.Housenumber + 2
		playerturn match {
			case 1 =>
				if(piecesOutA <= mesh10.Housenumber) {
					mesh10.stepsdone(0)(piecesOutA) = 0
					mesh10.piecepos(0)(piecesOutA) = 0
					mesh10.field1.Arr(0) = 'A'
					mesh10.house1.Arr(piecesOutA) = 'H'
					println("test " + piecesOutA)
					return copy(piecesOutA = piecesOutA + 1)
				} else 
					return move(6)
			case 2 =>
				if(piecesOutB <= mesh10.Housenumber) {
					mesh10.stepsdone(1)(piecesOutB) = 0
					mesh10.piecepos(1)(piecesOutB) = nextPlayer
					mesh10.field1.Arr(nextPlayer) = 'B'
					mesh10.house1.Arr(nextHouse + piecesOutB) = 'H'
					return copy(piecesOutB = piecesOutB + 1)
				} else
					return move(6)
			case 3 => 
				if(piecesOutC <= mesh10.Housenumber) {
					mesh10.stepsdone(2)(piecesOutC) = 0
					mesh10.piecepos(2)(piecesOutC) = nextPlayer * 2
					mesh10.field1.Arr(nextPlayer * 2) = 'C'
					mesh10.house1.Arr(nextHouse*2 + piecesOutC) = 'H'
					println("test " + piecesOutC)
					return copy(piecesOutC = piecesOutC + 1)
				} else 
					return move(6)
			case 4 =>
				if(piecesOutD <= mesh10.Housenumber) {
					mesh10.stepsdone(3)(piecesOutD) = 0
					mesh10.piecepos(3)(piecesOutD) = nextPlayer * 3
					mesh10.field1.Arr(nextPlayer * 3) = 'D'
					mesh10.house1.Arr(nextHouse*3 + piecesOutD) = 'H'
					val piecesOutnew = piecesOutD + 1
					return copy(piecesOutD = piecesOutnew)
				} else 
					return move(6)
		}
	}

	def moveOrGetOut(piece:Int, piecesOut:Int): Game = {
		if(piece > piecesOut && piece != mesh10.Housenumber + 1)
			return movePieceOut()
		else return movePiece(6,piece)
	}

	def getPiecesOut():Int = {
		playerturn match {
			case 1 => return piecesOutA
			case 2 => return piecesOutB
			case 3 => return piecesOutC
			case 4 => return piecesOutD
		}
	}

	def put(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int): Game = {
		copy(playerturn,mesh10,piecesOutA,piecesOutB,piecesOutC,piecesOutD)
	}
}
// Wenn einer draußen ist sollte nicht ein anderer rauskommen können
//Try/Option bei falschem Einput


// Bis nächstes mal Pattern 1 Abgabe() + Pattern 2 (Redo,Undo,Trait,Option)
