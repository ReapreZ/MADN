package model.gamecomponent.gamebase

import model.meshcomponent.meshbase.Mesh
import model.gamecomponent.GameInterface
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}
import com.google.inject.{Guice, Inject}
import com.google.inject.name.{Named, Names}

//PLAYER MODEL EINBAUEN
// PIECECHOOSER TYPISIERUNG (OPTION)
//ALLES FUNKTIONALER GESTALTEN

case class Game(playerturn:Int,mesh:Mesh,piecesOutList: List[Int] = List(0, 0, 0, 0),
								timesPlayerRolledList: List[Int] = List(0, 0, 0, 0)) extends GameInterface {
	var pieceChooser: Int = 0
	private def decrement1(num1: Int, num2: Int): Int = num1 - num2
	private val decrement: Int => Int = decrement1(_: Int, 1)
	private def increment1(num1: Int, num2: Int): Int = num1 + num2
	private val increment: Int => Int = increment1(_: Int, 1)
	def move(rolledDice: Int): Game = if(rolledDice != 6) rolledDiceIsNotSix(rolledDice) else rolledDiceIsSix()
	private def rolledDiceIsSix(): Game = {
		piecesOutList(decrement(playerturn)) match {
			case 0 => movePieceOut()
			case _ =>
				val chosenPiece =
					if (pieceChooser == 0) readLine().toInt
					else pieceChooser
				moveChosenPiece(chosenPiece)
		}
	}
	private def rolledDiceIsNotSix(rolledDice: Int): Game = {
		val currentPlayer = decrement(playerturn)
		val piecesOut = piecesOutList(currentPlayer)
		val timesRolled = timesPlayerRolledList(currentPlayer)
		piecesOut match {
			case 1 => handleOnePieceOut(rolledDice)
			case 0 if timesRolled != 2 => rollAgain()
			case 0 if timesRolled == 2 => handleNoPiecesOut()
			case _ => handleMultiplePiecesOut(rolledDice)
		}
	}
	private def handleOnePieceOut(rolledDice: Int): Game = updatePlayerTurn(movePiece(rolledDice, 1))
	private def updatePlayerTurn(game: Game): Game = if (playerturn == mesh.Player) game.copy(playerturn = 1) else game.copy(playerturn = increment(playerturn))
	private def handleNoPiecesOut(): Game = if (playerturn == mesh.Player) resetPlayerTurn() else incrementPlayerTurn()
	private def resetPlayerTurn(): Game = copy(playerturn = 1, timesPlayerRolledList = resetTimesPlayerRolled())
	private def incrementPlayerTurn(): Game = copy(playerturn = increment(playerturn), timesPlayerRolledList = resetTimesPlayerRolled())
	private def resetTimesPlayerRolled(): List[Int] = timesPlayerRolledList.updated(decrement(playerturn), 0)
	private def handleMultiplePiecesOut(rolledDice: Int): Game = {
		println("Which Piece should move?")
		val chosenPiece = if (pieceChooser == 0) readLine().toInt
		else pieceChooser
		pieceChooser = 0
		chosePieceToMove(rolledDice, chosenPiece)
	}
	def changeList(list: List[Int], stelle: Int, amount: Int): List[Int] = list.updated(stelle, list(stelle) + amount)
	def undoMove(rolledDice: Int, playerturn: Int, piece: Int): Game = {
		val currentPlayer = decrement(playerturn)
		val currentPiece = decrement(piece)
		mesh.field1.Arr(mesh.piecepos(currentPlayer)(currentPiece)) = '_'
		updateField(rolledDice, currentPlayer, currentPiece)
		mesh.stepsdone(currentPlayer)(currentPiece) += rolledDice
		mesh.piecepos(currentPlayer)(currentPiece) += rolledDice
		copy()
	}
	private def updateField(rolledDice: Int, currentPlayer: Int, currentPiece: Int): Unit = {
		getTurnC(playerturn) match {
			case Success(v) => mesh.field1.Arr((mesh.piecepos(currentPlayer)(currentPiece) + rolledDice)) = v
			case Failure(e) => println(e.getMessage)
		}
	}
	def getTurnC(playerturn: Int): Try[Char] = {
		for {
			c <- playerturn match {
				case 1 => Success('A')
				case 2 => Success('B')
				case 3 => Success('C')
				case 4 => Success('D')
				case _ => Failure(new NoSuchMethodException("Something went wrong"))
			}
		} yield c
	}
	def movePiece(rolledDice: Int, piece: Int): Game = {
		val currentPlayer = decrement(playerturn)
		val currentPiece = decrement(piece)
		mesh.field1.Arr(mesh.piecepos(currentPlayer)(currentPiece)) = ('_')
		val game = isFieldOccupied(rolledDice, piece)
		updateField(game, rolledDice, currentPlayer, currentPiece)
		game.mesh.stepsdone(currentPlayer)(currentPiece) += rolledDice
		game.mesh.piecepos(currentPlayer)(currentPiece) += rolledDice
		game.copy()
	}
	private def updateField(game: Game, rolledDice: Int, currentPlayer: Int, currentPiece: Int): Unit = {
		getTurnC(playerturn) match {
			case Success(v) => game.mesh.field1.Arr((mesh.piecepos(currentPlayer)(currentPiece)) + rolledDice) = v
			case Failure(e) => println(e.getMessage)
		}
	}
	def piecesOutLessThanFour(player:Int, nextPlayerField:Int, playerCharacter:Char, nextPlayerHouse:Int) : Game = {
		if (piecesOutList(player) <= 4) {
			mesh.stepsdone(player)(piecesOutList(player)) = 0
			mesh.piecepos(player)(piecesOutList(player)) = nextPlayerField
			mesh.field1.Arr(nextPlayerField) = playerCharacter
			mesh.house1.Arr(nextPlayerHouse + piecesOutList(player)) = 'H'
			copy(piecesOutList = changeList(piecesOutList,player, 1))
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
	def isFieldOccupied(rolledDice: Int, piece:Int): Game = checkForPlayer(0,0, determineNewPos(rolledDice)(piece))
	def checkForPlayer(counter: Int, counter2: Int, newPos: Int): Game = {
		if(counter != mesh.Player)
			checkForField(counter, counter2, newPos)
			checkForPlayer(increment(counter), counter2, newPos)
		copy()
	}
	def checkForField(counter: Int, counter2: Int, newPos: Int): Game = {
		if(counter2 != 4)
			if (mesh.piecepos(counter)(counter2) == newPos && decrement(playerturn) != counter)
				mesh.piecepos(counter)(counter2) = -1
				mesh.stepsdone(counter)(counter2) = -1
				counter match {
					case 0 => setHouseInMesh('A', 0, counter)
					case 1 => setHouseInMesh('B', 6, counter)
					case 2 => setHouseInMesh('C', 12, counter)
					case 3 => setHouseInMesh('D', 18, counter)
				}
				return copy(piecesOutList = changeList(piecesOutList,counter, -1))
			else if (mesh.piecepos(counter)(counter2) == newPos && decrement(playerturn) == counter)
				print("You cant kick out your own Piece\n")
			else checkForField(counter:Int, increment(counter2), newPos)
		copy()
	}
	def determineNewPos(rolledDice: Int): Int => Int = (piece: Int) => { mesh.piecepos(decrement(playerturn))(decrement(piece)) + rolledDice }
	def setHouseInMesh(houseChar: Char, housePosition: Int,piecePosition: Int) : Unit = mesh.house1.Arr(decrement(piecesOutList(piecePosition)) + housePosition) = houseChar
	def put(game: Game): Game = game.copy()
	def startgame: Try[Game] = {
		println("Amount of Players:")
		for {
			input <- Try(readLine().toInt)
			_ <- Try(require(input >= 1 && input <= 4, "Invalid number of players: must be between 1 and 4."))
			_ <- Try(println("Press 'r' to roll the dice"))
			game <- Try(copy(playerturn = 1, mesh = Mesh(input)))
		} yield game
	}
	def rollAgain(): Game = copy(timesPlayerRolledList = changeList(timesPlayerRolledList,decrement(playerturn), 1))
	def moveChosenPiece(chosenPiece: Int): Game = if(piecesOutList(decrement(playerturn)) == chosenPiece) movePiece(6, chosenPiece) else movePieceOut()
	def chosePieceToMove(rolledDice: Int, chosenPiece: Int): Game = {
		val newGame = movePiece(rolledDice, chosenPiece)
		if (newGame.playerturn == mesh.Player) newGame.copy(playerturn = 1)
		else newGame.copy(playerturn = newGame.increment(newGame.playerturn))
	}
}