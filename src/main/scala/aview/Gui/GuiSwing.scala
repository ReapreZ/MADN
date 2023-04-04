package aview.Gui

import util._
import scala.swing._
import javax.swing.{JPanel, JScrollPane, SwingUtilities, ImageIcon}
import java.awt.{BorderLayout, Image, Toolkit, Font}
import java.awt.Font._
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import scala.util.{Try, Success, Failure}
import java.io.File
import scala.swing.event._
import scala.io.StdIn.readLine
import controller.Controller
import model.diceComponent.diceBase.Dice
import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase.Game
import model.gameComponent.GameInterface
import scala.language.postfixOps
import controller.ControllerInterface

class GuiSwing(controller: ControllerInterface) extends MainFrame with Observer{

	controller.add(this)
	var oldDice: Int = 0
	var mesh: Mesh = new Mesh(0)
	val dice = new Dice

	//DECREMENT & INCREMENT COUNT
	private def decrement1(num1: Int, num2: Int): Int = num1 - num2
	private val decrement: Int => Int = decrement1(_: Int, 1)

	private def increment1(num1: Int, num2: Int): Int = num1 + num2
	private val increment: Int => Int = increment1(_: Int, 1)

	//GUI OUTLINE LOOK
	title = "Mensch ärgere dich nicht!"
	preferredSize = new Dimension(1440, 720)
	val infoLabel = new TextField("Put in the amount of Players to start the game") { background = java.awt.Color.GRAY; foreground = java.awt.Color.WHITE; font = new Font("Arial Black", 0, 12) }
	val fieldLabel = new Label
	val playeramountTF = new TextField()

	//BUTTONS & LISTEN TO
	val rollDiceB = new Button("Roll the Dice"); listenTo(rollDiceB)
	val rollMagicDiceB = new Button("Magic Dice"); listenTo(rollMagicDiceB)
	val piece1B = new Button("1"); listenTo(piece1B)
	val piece2B = new Button("2"); listenTo(piece2B)
	val piece3B = new Button("3"); listenTo(piece3B)
	val piece4B = new Button("4"); listenTo(piece4B)
	val undoB = new Button("Undo"); listenTo(undoB)
	val redoB = new Button("Redo"); listenTo(redoB)

	//PLAYER PIECE ICONS
	val PlayerA = new ImageIcon("src/main/resources/Icons/PlayerA.png")
	val PlayerB = new ImageIcon("src/main/resources/Icons/PlayerB.png")
	val PlayerC = new ImageIcon("src/main/resources/Icons/PlayerC.png")
	val PlayerD = new ImageIcon("src/main/resources/Icons/PlayerD.png")

	//PLAYER HOUSE ICONS
	val houseAIcon = new ImageIcon("src/main/resources/Icons/PlayerAHome.png")
	val houseBIcon = new ImageIcon("src/main/resources/Icons/PlayerBHome.png")
	val houseCIcon = new ImageIcon("src/main/resources/Icons/PlayerCHome.png")
	val houseDIcon = new ImageIcon("src/main/resources/Icons/PlayerDHome.png")

	//START,FIELD,END
	val circleIcon = new ImageIcon("src/main/resources/Icons/EmptyKreis.png")
	val finishIcon = new ImageIcon("src/main/resources/Icons/EmptyFinish.png")
	val normalFieldIcon = new ImageIcon("src/main/resources/Icons/NormalField.png")
	val startFieldIcon = new ImageIcon("src/main/resources/Icons/PlayerAStartField.png")

	//ICON LABELS
	val circle = Array(new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label)
	val fin = Array(new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label)
	val house = Array(new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label)
	val field = Array(new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label)

	//PLACEHOLDER
	val platzhalter = new Label("       ")
	val platzhalter1 = new Label("       ")

	//SET FINISH FIELDS
	var i = 0
	while (i < 16)
		fin(i).icon = finishIcon
		i = increment(i)
	i = 0

	//SET HOUSE FIELDS
	while (i < 16)
		if(i <= 3)
			house(i).icon = houseAIcon
			circle(i).icon = circleIcon
		else if(i <= 7 && i >= 4)
			house(i).icon = houseBIcon
			circle(i).icon = circleIcon
		else if(i <= 11 && i >= 8)
			house(i).icon = houseCIcon
			circle(i).icon = circleIcon
		else
			house(i).icon = houseDIcon
			circle(i).icon = circleIcon
		i = increment(i)

	//SET NORMAL FIELDS
	i = 1
	while (i < 40)
		field(i).icon = normalFieldIcon
		i = increment(i)
	field(0) = new Label { icon = startFieldIcon }

	//MENUBAR
	menuBar = new MenuBar {
		contents += new Menu("Game") {
			contents += MenuItem(Action("Save"){
				controller.doAndPublish(controller.save)
			})
			contents += MenuItem(Action("Load"){
				controller.doAndPublish(controller.load)
				var j = 0
				while (j < 40) {
					controller.game.mesh.field1.Arr(j) match {
						case '_' => field(j).icon = normalFieldIcon
						case 'A' => field(j).icon = PlayerA
						case 'B' => field(j).icon = PlayerB
						case 'C' => field(j).icon = PlayerC
						case 'D' => field(j).icon = PlayerD
						case 'x' => field(j).icon = normalFieldIcon
					}
					j = increment(j)
				}
				j = 0
				while (j < 16)
					controller.game.mesh.finish1.Arr(j) match {
						case '-' => fin(j).icon = finishIcon
					}
					j = increment(j)
			})
		}
		contents += undoB
		contents += redoB
		contents += piece1B
		contents += piece2B
		contents += piece3B
		contents += piece4B
		piece1B.preferredSize = new Dimension(50,30)
		piece2B.preferredSize = new Dimension(50,30)
		piece3B.preferredSize = new Dimension(50,30)
		piece4B.preferredSize = new Dimension(50,30)
	}

	//PANELS & REACTIONS
	def bottomPanel = new FlowPanel {
		infoLabel.preferredSize = new Dimension(560,50)
		contents += house(8)
		contents += house(9)
		contents += house(10)
		contents += house(11)
		contents += infoLabel
		contents += house(12)
		contents += house(13)
		contents += house(14)
		contents += house(15)
	}
	def topPanel = new FlowPanel {
		contents += circle(0)
		circle(0).visible = false
		contents += house(0)
		contents+= circle(1)
		circle(1).visible = false
		contents += house(1)
		contents += circle(2)
		circle(2).visible = false
		contents += house(2)
		contents += circle(3)
		circle(3).visible = false
		contents += house(3)
		contents += rollDiceB
		rollDiceB.preferredSize = new Dimension(140,30)
		contents += rollMagicDiceB
		rollMagicDiceB.preferredSize = new Dimension(140,30)
		contents += playeramountTF
		playeramountTF.preferredSize = new Dimension(140,30)

		contents += new Button("Start Game") {
			preferredSize = new Dimension(120,30)
			reactions += {
				case event.ButtonClicked(_) =>
					mesh = startGame()
					controller.game.pieceChooser = 0
					updateField()
					infoLabel.text = "Press the roll Button to roll the dice"
			}
		}
		contents += circle(4)
		circle(4).visible = false
		contents += house(4)
		contents += circle(5)
		circle(5).visible = false
		contents += house(5)
		contents += circle(6)
		circle(6).visible = false
		contents += house(6)
		contents += circle(7)
		circle(7).visible = false
		contents += house(7)
	}
	def rightPanel = new GridPanel(9,1) {
		contents += fin(8)
		contents += fin(9)
		contents += fin(10)
		contents += fin(11)
		contents += platzhalter
		contents += fin(12)
		contents += fin(13)
		contents += fin(14)
		contents += fin(15)
	}
	def centerPanel = new GridPanel(4,10) {
		contents += field(0)
		i = 1
		while(i < 40)
			contents += field(i)
			i = increment(i)
	}
	def leftPanel = new GridPanel(9,1) {
		contents += fin(0)
		contents += fin(1)
		contents += fin(2)
		contents += fin(3)
		contents += platzhalter1
		contents += fin(4)
		contents += fin(5)
		contents += fin(6)
		contents += fin(7)

	}
	reactions += {
		case event.ButtonClicked(`rollDiceB`) =>
			val rolledDice = dice.diceRandom()
			print("You rolled a " + rolledDice.toString + "\n")
			if(controller.game.piecesOutList(decrement(controller.game.playerturn)) == 1 && (rolledDice == 6 || oldDice == 6))
				if(controller.game.pieceChooser != 0)
					infoLabel.text = "Player: " + getPlayerturnAsChar() + " rolled a " + oldDice.toString + " Which piece should move/get out? Roll again to confirm";
					checkForPieceChoosing(6)
					oldDice = 0
				else
					oldDice = 6
					infoLabel.text = "Player: " + getPlayerturnAsChar() + " rolled a " + oldDice.toString + " Which piece should move/get out? Roll again to confirm";
			else if(controller.game.piecesOutList(decrement(controller.game.playerturn)) > 1)
				if(controller.game.pieceChooser != 0)
					infoLabel.text = "Player: " + getPlayerturnAsChar() + " rolled a " + oldDice.toString + " Which piece should move/get out? Roll again to confirm";
					checkForPieceChoosing(oldDice)
					oldDice = 0
				else 
					oldDice = rolledDice
					infoLabel.text = "Player: " + getPlayerturnAsChar() + " rolled a " + oldDice.toString + " Which piece should move/get out? Roll again to confirm";
			else 
				infoLabel.text = "Player " + getPlayerturnAsChar() + " rolled a " + rolledDice.toString;
				checkForPieceChoosing(rolledDice)

		case event.ButtonClicked(`rollMagicDiceB`) =>
			val rolledDice = dice.magicDice(6)
			print("You rolled a " + rolledDice.toString + "\n")
			infoLabel.text = "Player " + getPlayerturnAsChar() + " rolled a " + rolledDice.toString;
			checkForPieceChoosing(rolledDice)
			controller.game.pieceChooser = 0
		case event.ButtonClicked(`piece1B`) => controller.game.pieceChooser = 1
		case event.ButtonClicked(`piece2B`) => controller.game.pieceChooser = 2
		case event.ButtonClicked(`piece3B`) => controller.game.pieceChooser = 3
		case event.ButtonClicked(`piece4B`) => controller.game.pieceChooser = 4
		case event.ButtonClicked(`undoB`) => controller.doAndPublish(controller.undo); updateField();
		case event.ButtonClicked(`redoB`) => controller.doAndPublish(controller.redo); updateField();
	}
	contents = new BorderPanel {
	add(bottomPanel, BorderPanel.Position.South)
	add(topPanel, BorderPanel.Position.North)
	add(centerPanel, BorderPanel.Position.Center)
	add(rightPanel, BorderPanel.Position.East)
	add(leftPanel, BorderPanel.Position.West)
	}

	def startGame(): Mesh = { Mesh(playeramountTF.text.toInt) }

	def move(rolledDice: Int): Unit = {
			controller.doAndPublish(controller.move1 , rolledDice)
			controller.game.pieceChooser = 0
			updateField()
	}

	def getPlayerturnAsChar() : Char = {
		controller.game.playerturn match {
			case 1 => 'A'
			case 2 => 'B'
			case 3 => 'C'
			case 4 => 'D'
		}
	}
	def movePiece(rolledDice: Int): Unit = {
		if(controller.game.piecesOutList(decrement(controller.game.playerturn)) > 1 && controller.game.pieceChooser > 0)
			changePiecePos(rolledDice, controller.game.pieceChooser)
		else
			changePiecePos(rolledDice, 1)
	}

	//CHANGE GUI ICONS WHILE PLAYING
	private def changePiecePos(rolledDice: Int, pieceToMove: Int): Unit =  {
		isFieldOccupied(rolledDice, pieceToMove)
		controller.game.playerturn match {
			case 1 =>
				field(controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(pieceToMove))).icon = normalFieldIcon
				field((controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(pieceToMove))) + rolledDice).icon = PlayerA
			case 2 =>
				field(controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(pieceToMove))).icon = normalFieldIcon
				field((controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(pieceToMove))) + rolledDice).icon = PlayerB
			case 3 =>
				field(controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(pieceToMove))).icon = normalFieldIcon
				field((controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(pieceToMove))) + rolledDice).icon = PlayerC
			case 4 =>
				field(controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(pieceToMove))).icon = normalFieldIcon
				field((controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(pieceToMove))) + rolledDice).icon = PlayerD
		}
	}
	def movePieceOut(): Unit = {
		controller.game.playerturn match {
			case 1 => field(0).icon = PlayerA
			case 2 => field(10).icon = PlayerB
			case 3 => field(20).icon = PlayerC
			case 4 => field(30).icon = PlayerD
		}
	}
	def updateField() : Unit = {
		fieldLabel.text = controller.game.mesh.field1.toString
		controller.game.piecesOutList(0) match {
			case 0 =>
				circle(0).visible = false
			case 1 =>
				house(0).visible = false
				circle(0).visible = true
			case 2 =>
				house(1).visible = false
				circle(1).visible = true
			case 3 =>
				house(2).visible = false
				circle(2).visible = true
			case 4 =>
				house(3).visible = false
				circle(3).visible = true
					}
		controller.game.piecesOutList(1) match {
			case 0 =>
				circle(4).visible = false
			case 1 =>
				house(4).visible = false
				circle(4).visible = true
			case 2 =>
				house(5).visible = false
				circle(5).visible = true
			case 3 =>
				house(6).visible = false
				circle(6).visible = true
			case 4 =>
				house(7).visible = false
				circle(7).visible = true
		}
	}

	def isFieldOccupied(rolledDice: Int, piece: Int): Unit = { checkForPlayer(0, 0, determineNewPos(rolledDice, piece))}
	def checkForPlayer(counter: Int, counter2: Int, newPos: Int): Unit = {
		if (counter != controller.game.mesh.Player)
			checkForField(counter, counter2, newPos)
			checkForPlayer(increment(counter), counter2, newPos)
	}
	def checkForField(counter: Int, counter2: Int, newPos: Int): Unit = {
		if (counter2 != 4)
			if (controller.game.mesh.piecepos(counter)(counter2) == newPos && decrement(controller.game.playerturn) != counter)
				counter match {
					case 0 => setHouseInvisibleCircleVisible(i, 0)
					case 1 => setHouseInvisibleCircleVisible(i, 4)
					case 2 => setHouseInvisibleCircleVisible(i, 8)
					case 3 => setHouseInvisibleCircleVisible(i, 12)
				}
			else if(controller.game.mesh.piecepos(counter)(counter2) == newPos && decrement(controller.game.playerturn) == counter)
				infoLabel.text = "You cant kick out your own Piece"
			else checkForField(counter:Int, increment(counter2), newPos)
	}
	def determineNewPos(rolledDice: Int, piece: Int) : Int = { controller.game.mesh.piecepos(decrement(controller.game.playerturn))(decrement(piece)) + rolledDice }
	def setHouseInvisibleCircleVisible(counter: Int, nextPlayerHouse: Int) : Unit = { setHouseInvisible(counter, nextPlayerHouse); setCircleVisible(counter, nextPlayerHouse) }
	def setHouseInvisible(counter: Int, nextPlayerHouse: Int) : Unit = { house(decrement(controller.game.piecesOutList(counter)) + nextPlayerHouse).visible = false }
	def setCircleVisible(counter: Int, nextPlayerHouse: Int) : Unit = { circle(decrement(controller.game.piecesOutList(counter)) + nextPlayerHouse).visible = true }

	def checkForPieceChoosing(rolledDice: Int): Unit = {
		if(rolledDice == 6 && controller.game.piecesOutList(decrement(controller.game.playerturn)) == 0)
			movePieceOut()
			move(rolledDice)
		else if(rolledDice != 6 && (controller.game.piecesOutList(decrement(controller.game.playerturn)) > 1))
			if(controller.game.pieceChooser > 0)
				movePiece(rolledDice)
				move(rolledDice)
		else if(rolledDice == 6 && controller.game.piecesOutList(decrement(controller.game.playerturn)) > 0)
			if(controller.game.pieceChooser > 0)
				if(controller.game.pieceChooser > controller.game.piecesOutList(decrement(controller.game.playerturn)))
					movePieceOut()
					move(rolledDice)
				else
					movePiece(rolledDice)
					move(rolledDice)
				else oldDice = rolledDice
				//movePiece(rolledDice)
		else if(rolledDice != 6 && controller.game.piecesOutList(decrement(controller.game.playerturn)) == 1)
			movePiece(rolledDice)
			move(rolledDice)
		else 
			move(rolledDice)
	}
	override def closeOperation() = this.close()
	override def update = println()
	pack()
	centerOnScreen()
	open()
}
