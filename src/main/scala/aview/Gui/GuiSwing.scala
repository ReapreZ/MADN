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
import controller.Controller
import model.diceComponent.diceBase.Dice
import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase.Game
import scala.language.postfixOps

class GuiSwing(controller: Controller) extends MainFrame with Observer{
    //listenTo(controller)
    controller.add(this)
    var oldDice: Int = 0
    var playerturnC = ' '
    title = "Mensch ärgere dich nicht!"
    preferredSize = new Dimension(1024, 720)
    var piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
    val game: Game = new Game(1, mesh,piecesOutMap)

    var infoLabel = new TextField("Put in the amount of Players/Houses/Cells to start the game") {
        //icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/infoBoxTest.png")
        background = java.awt.Color.GRAY
        foreground = java.awt.Color.WHITE
        font = new Font("Arial Black", 0, 12)
    }
    val rollDiceB = new Button("Roll the Dice")
    listenTo(rollDiceB)
    val rollMagicDiceB = new Button("Magic Dice")
    listenTo(rollMagicDiceB)
    val piece1B = new Button("1")
    listenTo(piece1B)
    val piece2B = new Button("2")
    listenTo(piece2B)
    val piece3B = new Button("3")
    listenTo(piece3B)
    val piece4B = new Button("4")
    listenTo(piece4B)
    val undoB = new Button("Undo")
    listenTo(undoB)
    val redoB = new Button("Redo")
    listenTo(redoB)
    var PC1H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerCHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PC2H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerCHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PC3H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerCHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PC4H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerCHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PD1H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerDHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PD2H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerDHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PD3H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerDHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PD4H = new Label {
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerDHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PA1H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerAHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PA1 = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerA.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PA2H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerAHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PA3H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerAHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PA4H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerAHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PB1H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerBHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PB2H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerBHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PB3H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerBHome.png") 
        //preferredSize = new Dimension(50,50)
    }
    var PB4H = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/PlayerBHome.png") 
        //preferredSize = new Dimension(50,50)
    }
        var circle = new Label { 
        icon = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/EmptyKreis.png") 
        //preferredSize = new Dimension(50,50)
    }
    var circle2 = new Label
    Icon icon1 = new ImageIcon("C:/Software-Engineering/MADN-1/src/main/resources/Icons/EmptyKreis.png") 
    circle2.icon = icon1
    var mesh: Mesh = new Mesh(0)
    val dice1 = new Dice
    var fieldLabel = new Label
    var houseLabel = new Label
    var finishLabel = new Label

    val playeramountTF = new TextField()
    val houseamoutTF = new TextField()
    val cellamountTF = new TextField()


    menuBar = new MenuBar {
        contents ++= Seq{
            new Menu("Game") {
                contents ++= Seq {
                    MenuItem(Action("Undo")({controller.doAndPublish(controller.undo)}))
                }
            }
        }
        contents += undoB
        contents += redoB
    }
    def bottomPanel = new FlowPanel {
        infoLabel.preferredSize = new Dimension(560,50)
        contents += PC1H
        contents += PC2H
        contents += PC3H
        contents += PC4H
        contents += infoLabel
        contents += PD1H
        contents += PD2H
        contents += PD3H
        contents += PD4H
    }
    def topPanel = new FlowPanel {
        contents += circle
        circle.visible = false
        contents += PA1H
        contents+= circle2
        circle.visible = false
        contents += PA2H
        contents += PA3H
        contents += PA4H
        contents += rollDiceB
        rollDiceB.preferredSize = new Dimension(140,30)
        contents += rollMagicDiceB
        rollMagicDiceB.preferredSize = new Dimension(140,30)
        //houseamoutTF.preferredSize = new Dimension(100,30)
        //cellamountTF.preferredSize = new Dimension(100,30)
        contents += playeramountTF
        playeramountTF.preferredSize = new Dimension(140,30)
        //contents += houseamoutTF
        //contents += cellamountTF
        contents += new Button("Start Game") {
            preferredSize = new Dimension(120,30)
            reactions += {
                case event.ButtonClicked(_) =>
                    mesh = startGame()
                    controller.game = game.copy(1, mesh, piecesOutMap)
                    controller.game.pieceChooser = 0
                    updateField()
                    infoLabel.text = "Press the roll Button to roll the dice"
            }
        }
        contents += PB1H
        contents += PB2H
        contents += PB3H
        contents += PB4H
    }
    def rightPanel = new GridPanel(4,1) {
        contents += piece1B
        contents += piece2B
        contents += piece3B
        contents += piece4B
        piece1B.preferredSize = new Dimension(50,30)
        piece2B.preferredSize = new Dimension(50,30)
        piece3B.preferredSize = new Dimension(50,30)
        piece4B.preferredSize = new Dimension(50,30)
    }
    def centerPanel = new GridPanel(4,3) {
        contents += fieldLabel
        contents += houseLabel
        contents += finishLabel
        fieldLabel.font = new Font("Arial", 0, 20)
        houseLabel.font = new Font("Arial", 0, 20)
        finishLabel.font = new Font("Arial", 0, 20)
    }
    def leftPanel = new GridPanel(4,1) {
        //contents += undoB
        //contents += redoB
    }
    reactions += {
        case event.ButtonClicked(`rollDiceB`) =>
            println(controller.game.pieceChooser.toString)
            val rolledDice = dice1.diceRandom()
            controller.game.getTurnC(controller.game.playerturn) match {
                case Success(v) => playerturnC = v
                case Failure(e) => println(e.getMessage)
            }
            if(oldDice == 0)
                infoLabel.text = "It is Player's " + playerturnC + " and you rolled a " + rolledDice.toString
                oldDice = rolledDice
                checkForPieceChoosing(rolledDice)
            else 
                infoLabel.text = "It is Player's " + playerturnC + " and you rolled a " + oldDice.toString
                checkForPieceChoosing(oldDice)
                oldDice = 0
        case event.ButtonClicked(`rollMagicDiceB`) =>
            println(controller.game.pieceChooser.toString)
            val rolledDice = dice1.magicDice(6)
            controller.game.getTurnC(controller.game.playerturn) match {
                case Success(v) => playerturnC = v
                case Failure(e) => println(e.getMessage)
            }
            if(oldDice == 0)
                infoLabel.text = "It is Player's " + playerturnC + " and you rolled a " + rolledDice.toString
            //infoLabel.text = "You rolled a " + rolledDice.toString
            if(controller.game.piecesOutMap(controller.game.playerturn - 1) != 0)
                infoLabel.text = infoLabel.text + "    Which Piece should move or get out? After choosing, roll again to confirm"
                if(controller.game.pieceChooser != 0 && controller.game.pieceChooser != -1)
                    movePiece(rolledDice)
                    //infoLabel.text = "Press the roll Button to roll"
            else 
                movePiece(rolledDice)
                //infoLabel.text = "Press the roll Button to roll"
        case event.ButtonClicked(`piece1B`) => 
            controller.game.pieceChooser = 1
            println("GUI PieceChooser: " + controller.game.pieceChooser)
        case event.ButtonClicked(`piece2B`) => 
            controller.game.pieceChooser = 2
            println("GUI PieceChooser: " + controller.game.pieceChooser)
        case event.ButtonClicked(`undoB`) =>
            controller.doAndPublish(controller.undo)
            updateField()
        case event.ButtonClicked(`redoB`) =>
            controller.doAndPublish(controller.redo)
            updateField()
    }
    contents = new BorderPanel {
    add(bottomPanel, BorderPanel.Position.South)
    add(topPanel, BorderPanel.Position.North)
    add(centerPanel, BorderPanel.Position.Center)
    add(rightPanel, BorderPanel.Position.East)
    add(leftPanel, BorderPanel.Position.West)
    }
    def startGame(): Mesh = {
        return Mesh(playeramountTF.text.toInt)
    }
    def movePiece(rolledDice: Int): Unit = {
            controller.doAndPublish(controller.move1 , rolledDice)
            controller.game.pieceChooser = 0
            updateField()
    }
    def updateField() : Unit = {
        fieldLabel.text = controller.game.mesh10.field1.toString
        houseLabel.text = controller.game.mesh10.house1.toString
        finishLabel.text = controller.game.mesh10.finish1.toString
        if(piecesOutMap != controller.game.piecesOutMap)
            var i = 0
            //while (i < 4)
                if(piecesOutMap(0) != controller.game.piecesOutMap(0))
                    controller.game.piecesOutMap(0) match {
                        case 0 => circle.visible = false
                        case 1 => 
                            PA1H.visible = false
                            circle.visible = true
                        case 2 =>
                            PA2H.visible = false
                            circle2.visible = true
                        case 3 =>
                            PA3H.visible = false
                        case 4 =>
                            PA4H.visible = false

                    }
        piecesOutMap = controller.game.piecesOutMap
    }
    def checkForPieceChoosing(rolledDice: Int): Unit = {
        if(rolledDice.toInt != 6 && (controller.game.piecesOutMap(controller.game.playerturn - 1) != 1 && controller.game.piecesOutMap(controller.game.playerturn - 1) != 0))
            infoLabel.text = infoLabel.text + "     Which Piece should move? After choosing, roll again to confirm"
            if(controller.game.pieceChooser != 0 && controller.game.pieceChooser != -1)
                movePiece(rolledDice)
                //infoLabel.text = "Press the roll Button to roll"
        else if(rolledDice.toInt == 6 && controller.game.piecesOutMap(controller.game.playerturn - 1) != 0)
                infoLabel.text = infoLabel.text + "    Which Piece should move or get out? After choosing, roll again to confirm"
                if(controller.game.pieceChooser != 0 && controller.game.pieceChooser != -1)
                        movePiece(rolledDice)
                        //infoLabel.text = "Press the roll Button to roll"
        else 
            movePiece(rolledDice)
            //infoLabel.text = "Press the roll Button to roll"
    }
    override def closeOperation() = 
        this.close()
    override def update = println()
    pack()
    centerOnScreen()
    open()
}
