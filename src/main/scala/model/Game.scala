package model
case class Game( playerturn:Int, mesh10:Mesh) {

	//var playerturn = 0
	def getOut(rolledDice: Int,mesh:Mesh): Game = {
		val game = move(rolledDice,mesh)
		println(game.playerturn + "playeramount: " + game.mesh10.playeramount)
		if(rolledDice == 6) {
			val nextPlayer = game.mesh10.field1.Player * mesh10.field1.Cell + 1
			//println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
			//println("Player A can move out one Piece\n")
			println("You can roll again\n")
			game.playerturn match {
				case 1 => game.mesh10.field1.cArr(0) = 'A'
				//mesh1.player = mesh1.player :+ ("A", 0)
					//mesh1.house1.hArr(0) = 'H'
				case 2 => game.mesh10.field1.cArr(nextPlayer) = 'B'
				//mesh1.player = mesh1.player :+ ("B", nextPlayer)
					//mesh1.house1.hArr(4) = 'H'
				case 3 => game.mesh10.field1.cArr(nextPlayer * 2) = 'C'
				//mesh1.player = mesh1.player :+ ("C", nextPlayer*2)
					//mesh1.house1.hArr(8) = 'H'
				case 4 => game.mesh10.field1.cArr(nextPlayer * 3) = 'D'
				//mesh1.player = mesh1.player :+ ("D", nextPlayer*3)
					//mesh1.house1.hArr(12) = 'H'
			}
		} else if(rolledDice != 6) {
			if(game.playerturn == game.mesh10.playeramount)
				copy(playerturn = 1)
			else 
				val playerturn1 = game.playerturn + 1
				copy(playerturn1, game.mesh10)
		}
		//checkIfAllOut(mesh1)
		copy(mesh10 = game.mesh10)
	}

	def checkinput(rolledDice: Int, mesh: Mesh): Game = {
		val game = getOut(rolledDice,mesh)
		println("It is Player " + getTurnC(game.playerturn) + "'s turn\n")
		println(game.mesh10.mesh())
		copy(mesh10 = game.mesh10)
	}

	def move(rolledDice: Int,mesh1:Mesh): Game = {
		val playerTurnC = getTurnC(playerturn)
		val out = mesh1.field1.cArr.indexOf(playerTurnC)
		if(out != -1)
			mesh1.field1.cArr(out) = ('_')
			mesh1.field1.cArr(out + rolledDice) = playerTurnC
		copy(mesh10 = mesh1)
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

