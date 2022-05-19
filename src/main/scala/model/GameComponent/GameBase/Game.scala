package model.GameComponent.GameBase
import model.MeshComponent.MeshBase.Mesh
import scala.io.StdIn.readLine
import scala.compiletime.ops.boolean

case class Game(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int) extends GameStrategy {
	
	def getOut(rolledDice: Int): Game = {
		val game = move(rolledDice)
		val playerTurnC = getTurnC(playerturn)
		val out = game.mesh10.field1.Arr.indexOf(playerTurnC)
		if(rolledDice == 6) {
			if(out == -1)
			//println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
			//println("Player A can move out one Piece\n")
				println("You can roll again\n")
				return movePieceOut()
		} else{
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
		println("It is Player " + getTurnC(game.playerturn) + "'s turn\n")
		println(game.mesh10.mesh())
		return game.copy()
	}

	def move(rolledDice: Int): Game = {
		println("A: " + piecesOutA + " B: " + piecesOutB + " C: " + piecesOutC + " playerturn: " + playerturn)
		val playerTurnC = getTurnC(playerturn)
		val out = mesh10.field1.Arr.indexOf(playerTurnC)
		if(out != -1)
			if(rolledDice != 6 && getPiecesOut() == 1)
				val game = movePiece(rolledDice, getPiecesOut())
				return game.copy(mesh10 = this.mesh10)
			if (rolledDice == 6)
				println("Which Piece should be moved or which Piece should come out?")
				val input = readLine()
				playerTurnC match {
					case 'A' => return moveOrGetOut(input.toInt,piecesOutA)
					case 'B' => return moveOrGetOut(input.toInt,piecesOutB)
					case 'C' => return moveOrGetOut(input.toInt,piecesOutC)
					case 'D' => return moveOrGetOut(input.toInt,piecesOutD)	
				}
			else
				println("Which Piece should be moved?")
				val input = readLine()
						if (input.toInt <= mesh10.Housenumber)
							if (mesh10.stepsdone(playerturn - 1)(input.toInt - 1) == -1)
								playerTurnC match {
									case 'A' =>
										if(input.toInt >= piecesOutA)
											val game1 = movePieceOut()
											return game1
									case 'B' => 
										if(input.toInt >= piecesOutB)
											val game1 = movePieceOut()
											return game1
									case 'C' => 
										if(input.toInt >= piecesOutC)
											val game1 = movePieceOut()
											return game1
									case 'D' => 
										if(input.toInt >= piecesOutD)
											val game1 = movePieceOut()
											return game1
								}
							else return movePiece(rolledDice, input.toInt)
						else
							println("This Piece isnt out yet")
							val game = move(rolledDice)
							return game.copy(mesh10 = this.mesh10)
		return copy(mesh10 = this.mesh10)
	}

	def getTurnC(playerturn: Int): Char = {
		playerturn match {
			case 1 => 'A'
			case 2 => 'B'
			case 3 => 'C'
			case 4 => 'D'
			case _ => ' '
		}
	}
	def movePiece(rolledDice:Int, piece:Int): Game = {
		val playerTurnC = getTurnC(playerturn)
		mesh10.field1.Arr(mesh10.piecepos(playerturn - 1)(piece - 1)) = ('_')
		mesh10.field1.Arr((mesh10.piecepos(playerturn - 1)(piece - 1)) + rolledDice) = playerTurnC
		mesh10.stepsdone(playerturn - 1)(piece - 1) = (mesh10.stepsdone(playerturn - 1)(piece - 1)) + rolledDice
		mesh10.piecepos(playerturn - 1)(piece - 1) = (mesh10.piecepos(playerturn - 1)(piece - 1)) + rolledDice
		return copy(mesh10 = this.mesh10)
	}
	
	def movePieceOut(): Game = {
		val nextPlayer = mesh10.field1.Player * mesh10.field1.Cell + 1
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

	def set(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int): Game = {
		copy(playerturn,mesh10,piecesOutA,piecesOutB,piecesOutC,piecesOutD)
	}
}
// Wenn einer draußen ist sollte nicht ein anderer rauskommen können
//Try/Option bei falschem Einput


// Bis nächstes mal Pattern 1 Abgabe() + Pattern 2 (Redo,Undo,Trait,Option)
