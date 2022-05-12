package model.GameComponent.GameBase
import model.MeshComponent.MeshBase.Mesh
import scala.io.StdIn.readLine

case class Game(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int) extends Strategy {
	
	def getOut(rolledDice: Int,mesh1:Mesh): Game = {
		val game = move(rolledDice,mesh1)
		if(rolledDice == 6) {
			val nextPlayer = game.mesh10.field1.Player * game.mesh10.field1.Cell + 1
			val nextHouse = game.mesh10.houseamount + 2
			//println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
			//println("Player A can move out one Piece\n")
			println("You can roll again\n")
			game.playerturn match {
				case 1 =>
					if(game.piecesOutA < game.mesh10.houseamount) {
						if(game.piecesOutA == 0)
							game.mesh10.stepsdone(0)(0) = 0
						game.mesh10.field1.cArr(0) = 'A'
						game.mesh10.house1.hArr(game.piecesOutA) = 'H'
						val piecesOutnew = game.piecesOutA + 1
						return game.copy(piecesOutA = piecesOutnew)
					} else move(6,game.mesh10)
				case 2 =>
					if(game.piecesOutB < game.mesh10.houseamount) {
						if(game.piecesOutB == 0)
							game.mesh10.stepsdone(1)(0) = 0			
						game.mesh10.field1.cArr(nextPlayer) = 'B'
						game.mesh10.house1.hArr(nextHouse + piecesOutB) = 'H'
						val piecesOutnew = game.piecesOutB + 1
						return game.copy(piecesOutB = piecesOutnew)
					} else 
						move(6,game.mesh10)
				case 3 => 
					if(game.piecesOutC < game.mesh10.houseamount) {
						if(game.piecesOutC == 0)
							game.mesh10.stepsdone(2)(0) = 0
						game.mesh10.field1.cArr(nextPlayer * 2) = 'C'
						game.mesh10.house1.hArr(nextHouse*2 + piecesOutC) = 'H'
						val piecesOutnew = game.piecesOutC + 1
						return game.copy(piecesOutC = piecesOutnew)
					} else 
						move(6,game.mesh10)
				case 4 =>
					if(game.piecesOutD < game.mesh10.houseamount) {
						if(game.piecesOutD == 0)
							game.mesh10.stepsdone(3)(0) = 0						
						game.mesh10.field1.cArr(nextPlayer * 3) = 'D'
						game.mesh10.house1.hArr(nextHouse*3 + piecesOutD) = 'H'
						val piecesOutnew = game.piecesOutD + 1
						return game.copy(piecesOutD = piecesOutnew)
					} else 
						move(6,game.mesh10)
			}
		} else{
			if(game.playerturn == game.mesh10.playeramount)
				return copy(playerturn = 1)
			else 
				val playerturn1 = game.playerturn + 1
				return copy(playerturn1, game.mesh10)
		}
	}

	def checkinput(rolledDice: Int, mesh1:Mesh): Game = {
		val game = getOut(rolledDice,mesh1)
		println("It is Player " + getTurnC(game.playerturn) + "'s turn\n")
		println(game.mesh10.mesh())
		return game.copy()
	}

	def move(rolledDice: Int,mesh1:Mesh): Game = {
		val playerTurnC = getTurnC(playerturn)
		val out = mesh1.field1.cArr.indexOf(playerTurnC)
		if(out != -1)
			println("Which Piece should be moved?")
			val input = readLine()
					if (mesh1.stepsdone(playerturn)(input.toInt) != -1 && input.toInt <= mesh1.houseamount)
						mesh1.field1.cArr(mesh1.piecepos(playerturn)(input.toInt)) = ('_')
						mesh1.field1.cArr(mesh1.piecepos(playerturn)(input.toInt) + rolledDice) = playerTurnC
						mesh1.stepsdone(playerturn)(input.toInt) = mesh1.stepsdone(playerturn)(input.toInt) + rolledDice
						mesh1.piecepos(playerturn)(input.toInt) = mesh1.piecepos(playerturn)(input.toInt) + rolledDice
					else 
						println("This Piece isnt out yet")
						val game = move(rolledDice, mesh1)
						return game.copy(mesh10 = mesh1)
			/*playerTurnC match {
				case 'A' =>
					mesh1.stepsdone(0)(0) = mesh1.stepsdone(0)(0) + rolledDice
				case 'B' => mesh1.stepsdone(1)(0) = mesh1.stepsdone(1)(0) + rolledDice
				case 'C' => mesh1.stepsdone(2)(0) = mesh1.stepsdone(2)(0) + rolledDice
				case 'D' => mesh1.stepsdone(3)(0) = mesh1.stepsdone(3)(0) + rolledDice
			}
			mesh1.field1.cArr(out) = ('_')
			mesh1.field1.cArr(out + rolledDice) = playerTurnC */
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
}

