package modelComponent.gameComponent.gameBase

import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}
import com.google.inject.{Guice, Inject}
import com.google.inject.name.{Named, Names}

//PLAYER MODEL EINBAUEN
//TYPISIERUNG VON MAPS ÄNDERN 2LISTEN UND COLLECTABLE
// PIECECHOOSER TYPISIERUNG (OPTION)
// IF ELSE AUSBAUEN -> MONADEN VERBINDEN
// GETPLAYERTURN ÄNDERN
// WHILES AUSBAUEN
//ALLES FUNKTIONALER GESTALTEN
//ABGABE 2 TWO CHECK EINFÜHREN

case class Game(playerturn:Int,mesh10:Mesh,piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0),
								timesPlayerRolled:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)) extends GameInterface {
	var pieceChooser: Int = -1
	val activePieces = List.fill(4)(Player)

	private def decrement1(num1: Int, num2: Int): Int = num1 - num2
	private val decrement: Int => Int = decrement1(_: Int, 1)
	private def increment1(num1: Int, num2: Int): Int = num1 + num2
	private val increment: Int => Int = increment1(_: Int, 1)
	def move(rolledDice: Int): Game = { if(rolledDice != 6) rolledDiceIsNotSix(rolledDice) else rolledDiceIsSix() }
	private def rolledDiceIsSix(): Game = {
		if (piecesOutMap(decrement(playerturn)) == 0) return movePieceOut()
		else
			println("Which Piece should move or get out?")
		if (pieceChooser == -1) moveChosenPiece(readLine().toInt)
		else moveChosenPiece(getPiece().toInt)
	}

	private def rolledDiceIsNotSix(rolledDice: Int): Game = {
		val currentPlayer = decrement(playerturn)
		val piecesOut = piecesOutMap(currentPlayer)
		val timesRolled = timesPlayerRolled(currentPlayer)
		if (piecesOut == 1) {
			val game = movePiece(rolledDice, 1)
			if (playerturn == mesh10.Player) game.copy(playerturn = 1)
			else game.copy(playerturn = increment(playerturn))
		} else if (piecesOut == 0 && timesRolled != 2) {
			rollAgain()
		} else if (piecesOut == 0 && timesRolled == 2) {
			if (playerturn == mesh10.Player)
				copy(playerturn = 1, timesPlayerRolled = changeMap(currentPlayer, 0))
			else
				copy(playerturn = increment(playerturn), timesPlayerRolled = changeMap(currentPlayer, 0))
		} else {
			println("Which Piece should move?")
			val chosenPiece = if (pieceChooser == -1) readLine().toInt else getPiece().toInt
			chosePieceToMove(rolledDice, chosenPiece)
		}
	}

	def undoMove(rolledDice: Int,playerturn: Int,piece: Int): Game = {
		val playerTurnC = getTurnC(playerturn)
		mesh10.field1.Arr(mesh10.piecepos(decrement(playerturn))(decrement(piece))) = ('_')
		playerTurnC match {
			case Success(v) => mesh10.field1.Arr((mesh10.piecepos(decrement(playerturn))(decrement(piece)) + rolledDice)) = v
			case Failure(e) => println(e.getMessage)
		}
		mesh10.stepsdone(decrement(playerturn))(decrement(piece)) = (mesh10.stepsdone(decrement(playerturn))(decrement(piece))) + rolledDice
		mesh10.piecepos(decrement(playerturn))(decrement(piece)) = (mesh10.piecepos(decrement(playerturn))(decrement(piece))) + rolledDice
		copy()
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
		val game = isFieldOccupied(rolledDice, piece)
		playerTurnC match {
			case Success(v) => game.mesh10.field1.Arr((mesh10.piecepos(decrement(playerturn))(decrement(piece))) + rolledDice) = v
			case Failure(e) => println(e.getMessage)
		}
		game.mesh10.stepsdone(decrement(playerturn))(decrement(piece)) = (mesh10.stepsdone(decrement(playerturn))(decrement(piece))) + rolledDice
		game.mesh10.piecepos(decrement(playerturn))(decrement(piece)) = (mesh10.piecepos(decrement(playerturn))(decrement(piece))) + rolledDice
		game.copy()
	}
	private def piecesOutLessThanFour(player:Int, nextPlayerField:Int, playerCharacter:Char, nextPlayerHouse:Int) : Game = {
		if (piecesOutMap(player) <= 4) {
			mesh10.stepsdone(player)(piecesOutMap(player)) = 0
			mesh10.piecepos(player)(piecesOutMap(player)) = nextPlayerField
			mesh10.field1.Arr(nextPlayerField) = playerCharacter
			mesh10.house1.Arr(nextPlayerHouse + piecesOutMap(player)) = 'H'
			copy(piecesOutMap = changeMap(player, 1))
		} else {
			move(6)
		}
	}
	def movePieceOut(): Game = { 
		playerturn match {
			case 1 => piecesOutLessThanFour(0, 0, 'A', 0)
			case 2 => piecesOutLessThanFour(1, 10, 'B', 6)
			case 3 => piecesOutLessThanFour(2, 20, 'C', 12)
			case 4 => piecesOutLessThanFour(3, 30, 'D', 18)
		}
	}
	def isFieldOccupied(rolledDice: Int, piece:Int): Game = { checkForPlayer(0,0, determineNewPos(rolledDice)(piece))
	}
	def checkForPlayer(counter: Int, counter2: Int, newPos: Int): Game = {
		if(counter != mesh10.Player)
			checkForField(counter, counter2, newPos)
			checkForPlayer(increment(counter), counter2, newPos)
		copy()
	}
	def checkForField(counter: Int, counter2: Int, newPos: Int): Game = {
		if(counter2 != 4)
			if (mesh10.piecepos(counter)(counter2) == newPos && decrement(playerturn) != counter)
				mesh10.piecepos(counter)(counter2) = -1
				mesh10.stepsdone(counter)(counter2) = -1
				counter match {
					case 0 => setHouseInMesh('A', 0, counter)
					case 1 => setHouseInMesh('B', 6, counter)
					case 2 => setHouseInMesh('C', 12, counter)
					case 3 => setHouseInMesh('D', 18, counter)
				}
				return copy(piecesOutMap = changeMap(counter, -1))
			else if (mesh10.piecepos(counter)(counter2) == newPos && decrement(playerturn) == counter)
				print("You cant kick out your own Piece\n")
			else checkForField(counter:Int, increment(counter2), newPos)
		copy()
	}
	def determineNewPos(rolledDice: Int) = (piece: Int) => { mesh10.piecepos(decrement(playerturn))(decrement(piece)) + rolledDice }
	def setHouseInMesh(houseChar: Char, housePosition: Int,piecePosition: Int) : Unit = { mesh10.house1.Arr(decrement(piecesOutMap(piecePosition)) + housePosition) = houseChar
	}
	def changeMap(stelle:Int, amount:Int): Map[Int,Int] = {
		val changedMap: scala.collection.mutable.Map[Int,Int] = scala.collection.mutable.Map(piecesOutMap.toSeq: _*)
		changedMap(stelle) = piecesOutMap(stelle) + amount
		changedMap.toMap
	}
	def put(game: Game): Game = { game.copy() }
	def getPiece(): String = { if(pieceChooser == 0) getPiece(); pieceChooser.toString }
	def startgame: Try[Game] = {
		println("Amount of Players:")
		val input = readLine()
		if(input.toInt < 1 && input.toInt > 4) then Failure(NotImplementedError("Too Many/Few Player"))
		else
			val playeramount = input.toInt
			println("Press 'r' to roll the dice")
			Success(copy(playerturn = 1,mesh10 = Mesh(playeramount)))
	}
	def rollAgain(): Game = { copy(timesPlayerRolled = changeMap(decrement(playerturn), increment(timesPlayerRolled(decrement(playerturn))))) }
	def moveChosenPiece(chosenPiece: Int): Game = {
		if (chosenPiece <= 4)
			piecesOutMap.get(decrement(playerturn)) match {
				case Some(chosenPiece) => movePieceOut()
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