package model.GameComponent.GameBase
import model.MeshComponent.MeshBase.Mesh
import scala.io.StdIn.readLine
import scala.compiletime.ops.boolean

case class Game(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int) extends Strategy {
	
	def getOut(rolledDice: Int,mesh1:Mesh): Game = {
		val game = move(rolledDice,mesh1)
		val playerTurnC = getTurnC(playerturn)
		val out = mesh1.field1.cArr.indexOf(playerTurnC)
		if(rolledDice == 6) {
			if(out == -1)
			//println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
			//println("Player A can move out one Piece\n")
				println("You can roll again\n")
				return movePieceOut(game.mesh10)
		} else{
			if(game.playerturn == game.mesh10.playeramount)
				return copy(playerturn = 1)
			else 
				val playerturn1 = game.playerturn + 1
				return copy(playerturn1, game.mesh10)
		}
		return game.copy()
	}

	def checkinput(rolledDice: Int, mesh1:Mesh): Game = {
		val game = getOut(rolledDice,mesh1)
		println("It is Player " + getTurnC(game.playerturn) + "'s turn\n")
		println(game.mesh10.mesh())
		return game.copy()
	}

	def move(rolledDice: Int,mesh1:Mesh): Game = {
		println("A: " + piecesOutA + " B: " + piecesOutB + " C: " + piecesOutC + " playerturn: " + playerturn)
		val playerTurnC = getTurnC(playerturn)
		val out = mesh1.field1.cArr.indexOf(playerTurnC)
		if(out != -1)
			if(rolledDice != 6 && getPiecesOut() == 1)
				val game = movePiece(rolledDice, getPiecesOut(), mesh1)
				return game.copy(mesh10 = mesh1)
				/*playerTurnC match {
					case 'A' =>
						if(piecesOutA == 1)
							val game = movePiece(rolledDice, piecesOutA, mesh1)
							return 	game.copy(mesh10 = mesh1)
						else()
					case 'B' =>
						if(piecesOutB == 1)
							val game = movePiece(rolledDice, piecesOutB, mesh1)
							return copy(mesh10 = mesh1)
					case 'C' =>
						if(piecesOutC == 1)
							val game = movePiece(rolledDice, piecesOutC, mesh1)
							return copy(mesh10 = mesh1)
					case 'D' =>
						if(piecesOutD == 1)
							val game = movePiece(rolledDice, piecesOutD, mesh1)
							return copy(mesh10 = mesh1)		
				} */
			if (rolledDice == 6)
				println("Which Piece should be moved or which Piece should come out?")
				val input = readLine()
				playerTurnC match {
					case 'A' => moveOrGetOut(6,input.toInt,piecesOutA,mesh1)
					case 'B' => moveOrGetOut(6,input.toInt,piecesOutB,mesh1)
					case 'C' => moveOrGetOut(6,input.toInt,piecesOutC,mesh1)
					case 'D' => moveOrGetOut(6,input.toInt,piecesOutA,mesh1)	
				}
			else
				println("Which Piece should be moved?")
				val input = readLine()
						if (input.toInt <= mesh1.houseamount)
							if (mesh1.stepsdone(playerturn - 1)(input.toInt - 1) == -1)
								playerTurnC match {
									case 'A' =>
										if(input.toInt >= piecesOutA)
											return movePieceOut(mesh1)
									case 'B' => 
										if(input.toInt >= piecesOutB)
											return movePieceOut(mesh1)
									case 'C' => 
										if(input.toInt >= piecesOutC)
											return movePieceOut(mesh1)
									case 'D' => 
										if(input.toInt >= piecesOutD)
											return movePieceOut(mesh1)
								}
							else return movePiece(rolledDice, input.toInt, mesh1)
						else
							println("This Piece isnt out yet")
							val game = move(rolledDice, mesh1)
							return game.copy(mesh10 = mesh1)
		return copy(mesh10 = mesh1)
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
	def movePiece(rolledDice:Int, piece:Int, mesh:Mesh): Game = {
		val playerTurnC = getTurnC(playerturn)
		mesh.field1.cArr(mesh.piecepos(playerturn - 1)(piece - 1)) = ('_')
		mesh.field1.cArr((mesh.piecepos(playerturn - 1)(piece - 1)) + rolledDice) = playerTurnC
		mesh.stepsdone(playerturn - 1)(piece - 1) = (mesh.stepsdone(playerturn - 1)(piece - 1)) + rolledDice
		mesh.piecepos(playerturn - 1)(piece - 1) = (mesh.piecepos(playerturn - 1)(piece - 1)) + rolledDice
		return copy(mesh10 = mesh)
	}
	
	def movePieceOut(mesh:Mesh): Game = {
		val nextPlayer = mesh.field1.Player * mesh.field1.Cell + 1
		val nextHouse = mesh.houseamount + 2
		playerturn match {
			case 1 =>
				if(piecesOutA <= mesh.houseamount) {
					mesh.stepsdone(0)(piecesOutA) = 0
					mesh.piecepos(0)(piecesOutA) = 0
					mesh.field1.cArr(0) = 'A'
					mesh.house1.hArr(piecesOutA) = 'H'						
					val piecesOutnew = piecesOutA + 1
					return copy(piecesOutA = piecesOutnew)
				} else 
					return move(6,mesh)
			case 2 =>
				if(piecesOutB <= mesh.houseamount) {
					mesh.stepsdone(1)(piecesOutB) = 0
					mesh.piecepos(1)(piecesOutB) = nextPlayer
					mesh.field1.cArr(nextPlayer) = 'B'
					mesh.house1.hArr(nextHouse + piecesOutB) = 'H'
					val piecesOutnew = piecesOutB + 1
					return copy(piecesOutB = piecesOutnew)
				} else
					return move(6,mesh10)
			case 3 => 
				if(piecesOutC <= mesh.houseamount) {
					mesh.stepsdone(2)(piecesOutC) = 0
					mesh.piecepos(2)(piecesOutC) = nextPlayer * 2
					mesh.field1.cArr(nextPlayer * 2) = 'C'
					mesh.house1.hArr(nextHouse*2 + piecesOutC) = 'H'
					val piecesOutnew = piecesOutC + 1
					return copy(piecesOutC = piecesOutnew)
				} else 
					return move(6,mesh)
			case 4 =>
				if(piecesOutD <= mesh.houseamount) {
					mesh.stepsdone(3)(piecesOutD) = 0
					mesh.piecepos(3)(piecesOutD) = nextPlayer * 3
					mesh.field1.cArr(nextPlayer * 3) = 'D'
					mesh.house1.hArr(nextHouse*3 + piecesOutD) = 'H'
					val piecesOutnew = piecesOutD + 1
					return copy(piecesOutD = piecesOutnew)
				} else 
					return move(6,mesh)
		}
	}

	def moveOrGetOut(rolledDice:Int, piece:Int, piecesOut:Int, mesh:Mesh): Game = {
		if(piece > piecesOut && piece != mesh.houseamount)
			return movePieceOut(mesh)
		else return movePiece(6,piece,mesh)
	}

	def getPiecesOut():Int = {
		playerturn match {
			case 1 => return piecesOutA
			case 2 => return piecesOutB
			case 3 => return piecesOutC
			case 4 => return piecesOutD
		}
	}
}
// Wenn einer draußen ist sollte nicht ein anderer rauskommen können
// Wenn ein zweitelsmal 6 gewürfelt wird, kommt der einfach raus und läuft
// Wenn eins draußen ist und eine 6 gewürfelt wird, wird nicht gefragt