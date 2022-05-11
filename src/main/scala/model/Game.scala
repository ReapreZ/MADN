package model
case class Game(playerturn:Int,mesh10:Mesh,piecesOutA:Int,piecesOutB:Int,piecesOutC:Int,piecesOutD:Int) {
	
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
						game.mesh10.field1.cArr(0) = 'A'
						game.mesh10.house1.hArr(game.piecesOutA) = 'H'
						val piecesOutnew = game.piecesOutA + 1
						return game.copy(piecesOutA = piecesOutnew)
					} else move(6,game.mesh10)
				//mesh1.player = mesh1.player :+ ("A", 0)
				case 2 =>
					if(game.piecesOutB < game.mesh10.houseamount) {
						game.mesh10.field1.cArr(nextPlayer) = 'B'
						game.mesh10.house1.hArr(nextHouse + piecesOutB) = 'H'
						val piecesOutnew = game.piecesOutB + 1
						return game.copy(piecesOutB = piecesOutnew)
					} else 
						move(6,game.mesh10)
				//mesh1.player = mesh1.player :+ ("B", nextPlayer)
				case 3 => 
					if(game.piecesOutC < game.mesh10.houseamount) {
						game.mesh10.field1.cArr(nextPlayer * 2) = 'C'
						game.mesh10.house1.hArr(nextHouse*2 + piecesOutC) = 'H'
						val piecesOutnew = game.piecesOutC + 1
						return game.copy(piecesOutC = piecesOutnew)
					} else 
						move(6,game.mesh10)
				//mesh1.player = mesh1.player :+ ("C", nextPlayer*2)
				case 4 =>
					if(game.piecesOutD < game.mesh10.houseamount) {
						game.mesh10.field1.cArr(nextPlayer * 3) = 'D'
						game.mesh10.house1.hArr(nextHouse*3 + piecesOutD) = 'H'
						val piecesOutnew = game.piecesOutD + 1
						return game.copy(piecesOutD = piecesOutnew)
					} else 
						move(6,game.mesh10)
				//mesh1.player = mesh1.player :+ ("D", nextPlayer*3)
			}
		} else if(rolledDice != 6) {
			if(game.playerturn == game.mesh10.playeramount)
				return copy(playerturn = 1)
			else 
				val playerturn1 = game.playerturn + 1
				return copy(playerturn1, game.mesh10)
		}
		//checkIfAllOut(mesh1)
		return copy(mesh10 = game.mesh10)
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
			mesh1.field1.cArr(out) = ('_')
			mesh1.field1.cArr(out + rolledDice) = playerTurnC
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

	/*def checkIfAllOut(mesh: Mesh): Mesh = {
		//val test = mesh.player.groupBy(_.head).mapValues(_.size)
		playerturn match {
			case 1 => ""
				
		}


		mesh

	}*/
}

