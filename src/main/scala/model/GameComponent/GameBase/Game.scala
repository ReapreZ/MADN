package model.gameComponent.gameBase
import model.meshComponent.meshBase.Mesh
import model.gameComponent.GameInterface
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}
import com.google.inject.{Guice, Inject}
import com.google.inject.name.{Named, Names}
import model.diceComponent.diceBase.DiceStrategy
import model.diceComponent.diceBase.Dice


case class Game(playerturn:Int,mesh10:Mesh,piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0),
								timesPlayerRolled:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)) extends GameInterface {
	var pieceChooser: Int = -1

	private def decrement1(num1: Int, num2: Int): Int = num1 - num2
	private val decrement: Int => Int = decrement1(_: Int, 1)
	private def increment1(num1: Int, num2: Int): Int = num1 + num2
	private val increment: Int => Int = increment1(_: Int, 1)

	def move(rolledDice: Int): Game = {
		//println("A: " + piecesOutMap(0) + " B: " + piecesOutMap(1) + " C: " + piecesOutMap(2) + " playerturn: " + playerturn)
		if(rolledDice != 6) // Wenn keine 6 gewürfelt wird
			 rolledDiceIsNotSix(rolledDice)
		else // Wenn eine 6 gewürfelt wird
		   rolledDiceIsSix()

	}

	private def rolledDiceIsSix(): Game = {
		if (piecesOutMap(decrement(playerturn)) == 0)
			println(piecesOutMap(decrement(playerturn)))
			return movePieceOut()
		else
			println("Which Piece should move or get out?")
		if (pieceChooser == -1)
			moveChosenPiece(readLine().toInt)
		else
			moveChosenPiece(getPiece().toInt)
	}

	private def rolledDiceIsNotSix(rolledDice: Int): Game = {
		if (piecesOutMap(decrement(playerturn)) == 1) // Nur einer draußen und keine 6
			val game1 = movePiece(rolledDice, 1)
			if (playerturn == mesh10.Player)
				return game1.copy(playerturn = 1)
			else
				return game1.copy(increment(playerturn))
		else if(piecesOutMap(decrement(playerturn)) == 0 && timesPlayerRolled(decrement(playerturn)) != 2) // Keiner draußen und keine 6
			rollAgain()
		else if(piecesOutMap(decrement(playerturn)) == 0 && timesPlayerRolled(decrement(playerturn)) == 2)
			if (playerturn == mesh10.Player)
				return copy(playerturn = 1, timesPlayerRolled = changeMap(decrement(playerturn), 0))
			else
				return copy(increment(playerturn), timesPlayerRolled = changeMap(decrement(playerturn), 0))

		else // Mehrere draußen und keine 6
			println("Which Piece should move?")
			if (pieceChooser == -1)
				chosePieceToMove(rolledDice, readLine().toInt)
			else
				chosePieceToMove(rolledDice, getPiece().toInt)
	}

	def undoMove(rolledDice: Int,playerturnt: Int,piece: Int): Game = {
		val playerTurnC = getTurnC(playerturnt)
		mesh10.field1.Arr(mesh10.piecepos(decrement(playerturnt))(decrement(piece))) = ('_')
		playerTurnC match {
			case Success(v) => mesh10.field1.Arr((mesh10.piecepos(decrement(playerturnt))(decrement(piece)) + rolledDice)) = v
			case Failure(e) => println(e.getMessage)
		}
		mesh10.stepsdone(decrement(playerturnt))(decrement(piece)) = (mesh10.stepsdone(decrement(playerturnt))(decrement(piece))) + rolledDice
		mesh10.piecepos(decrement(playerturnt))(decrement(piece)) = (mesh10.piecepos(decrement(playerturnt))(decrement(piece))) + rolledDice
		return copy()
	}

	def getTurnC(playerturn: Int): Try[Char] = {
		if playerturn < 0 || playerturn > 4 then Failure(NoSuchMethodException("Something went wrong"))
		else
		playerturn match {
			case 1 => Success('A')
			case 2 => Success('B')
			case 3 => Success('C')
			case 4 => Success('D')
			case _ => Success('A')
		}
	}
	def movePiece(rolledDice:Int,piece:Int) : Game = {
		val playerTurnC = getTurnC(playerturn)
		mesh10.field1.Arr(mesh10.piecepos(decrement(playerturn))(decrement(piece))) = ('_')
		val game2 = isFieldOccupied(rolledDice, piece)
		playerTurnC match {
			case Success(v) => game2.mesh10.field1.Arr((mesh10.piecepos(decrement(playerturn))(decrement(piece))) + rolledDice) = v.toChar
			case Failure(e) => println(e.getMessage)
		}
		game2.mesh10.stepsdone(decrement(playerturn))(decrement(piece)) = (mesh10.stepsdone(decrement(playerturn))(decrement(piece))) + rolledDice
		game2.mesh10.piecepos(decrement(playerturn))(decrement(piece)) = (mesh10.piecepos(decrement(playerturn))(decrement(piece))) + rolledDice
		return game2.copy()
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
					copy(piecesOutMap = changeMap(0,1))
				} else 
					move(6)
			case 2 =>
				if(piecesOutMap(1) <= 4) {
					mesh10.stepsdone(1)(piecesOutMap(1)) = 0
					mesh10.piecepos(1)(piecesOutMap(1)) = nextPlayer
					mesh10.field1.Arr(nextPlayer) = 'B'
					mesh10.house1.Arr(nextHouse + piecesOutMap(1)) = 'H'
					copy(piecesOutMap = changeMap(1,1))
				} else
					move(6)
			case 3 => 
				if(piecesOutMap(2) <= 4) {
					mesh10.stepsdone(2)(piecesOutMap(2)) = 0
					mesh10.piecepos(2)(piecesOutMap(2)) = nextPlayer * 2
					mesh10.field1.Arr(nextPlayer * 2) = 'C'
					mesh10.house1.Arr(nextHouse*2 + piecesOutMap(2)) = 'H'
					copy(piecesOutMap = changeMap(2,1))
				} else 
					move(6)
			case 4 =>
				if(piecesOutMap(3) <= 4) {
					mesh10.stepsdone(3)(piecesOutMap(3)) = 0
					mesh10.piecepos(3)(piecesOutMap(3)) = nextPlayer * 3
					mesh10.field1.Arr(nextPlayer * 3) = 'D'
					mesh10.house1.Arr(nextHouse*3 + piecesOutMap(3)) = 'H'
					copy(piecesOutMap = changeMap(3, 1))
				} else 
					move(6)
		}
	}

	def moveOrGetOut(piece:Int, piecesOut:Int): Game = {
		if(piece > piecesOut && piece != 4 + 1)
			movePieceOut()
		else 
			movePiece(6,piece)
	}

	def isFieldOccupied(rolledDice: Int, piece:Int): Game = {
		val nextHouse = 6
		val newPos = mesh10.piecepos(decrement(playerturn))(decrement(piece)) + rolledDice
		var i = 0
		var j = 0
		while(i < mesh10.Player)
			while(j < 4)
				if(mesh10.piecepos(i)(j) == newPos && decrement(playerturn) != i) //nicht der selbe
					mesh10.piecepos(i)(j) = -1
					mesh10.stepsdone(i)(j) = -1
					i match {
						case 0 => mesh10.house1.Arr(decrement(piecesOutMap(i))) = 'A'
						case 1 => mesh10.house1.Arr(decrement(piecesOutMap(i)) + nextHouse) = 'B'
						case 2 => mesh10.house1.Arr(decrement(piecesOutMap(i)) + nextHouse*2) = 'C'
						case 3 => mesh10.house1.Arr(decrement(piecesOutMap(i)) + nextHouse*3) = 'D'
					}
					return copy(piecesOutMap = changeMap(i, -1))
				else if(mesh10.piecepos(i)(j) == newPos && decrement(playerturn) == i)  //der selbe
					print("You cant kick out your own Piece\n")
					return copy()
				j = increment(j)
			j = 0
			i = increment(i)
		copy()
	}

	/*def changePlayerTurn(playerturnT: Int) : Game = {
		if(playerturnT == mesh10.Player)
				return copy(playerturn = 1)
		else
			return copy(playerturn = playerturn + 1)
	}*/

	def changeMap(stelle:Int, amount:Int): Map[Int,Int] = {
		val changedMap: scala.collection.mutable.Map[Int,Int] = scala.collection.mutable.Map(piecesOutMap.toSeq: _*)
		changedMap(stelle) = piecesOutMap(stelle) + amount
		changedMap.toMap
	}
	def put(game: Game): Game = {
		game.copy()
	}

	def getPiece(): String = {
		if(pieceChooser == 0)
			getPiece()
		pieceChooser.toString
	}
	def startgame: Try[Game] = {
		println("Amount of Players:")
		val input = readLine()
		if(input.toInt < 1 && input.toInt > 4) then Failure(NotImplementedError("Too Many/Few Player"))
		else
			val playeramount = input.toInt
			println("Press 'r' to roll the dice")
			Success(copy(playerturn = 1,mesh10 = new Mesh(playeramount.toInt)))
	}

	def rollAgain(): Game = { copy(timesPlayerRolled = changeMap(decrement(playerturn), increment(timesPlayerRolled(decrement(playerturn)))))
	}

	def moveChosenPiece(chosenPiece: Int): Game = {
		if (chosenPiece <= 4)
			piecesOutMap.get(decrement(playerturn)) match {
				case Some(piece) => moveOrGetOut(chosenPiece, piece)
				case None => move(6)
			}
		else move(6)
	}

	def chosePieceToMove(rolledDice: Int, chosenPiece: Int): Game = {
		movePiece(rolledDice, chosenPiece)
		if (playerturn == mesh10.Player)
			copy(playerturn = 1)
		else
			copy(increment(playerturn))
	}

	}
// Wenn einer draußen ist sollte nicht ein anderer rauskommen können
