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
import model.DiceComponent.DiceBase.Dice
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game
import java.time._

class GuiSwing(controller: Controller) extends MainFrame with Observer{
    //listenTo(controller)
    controller.add(this)
    var oldDice: Int = 0
    var playerturnC = ' '
    title = "Mensch ärgere dich nicht!"
    preferredSize = new Dimension(800, 700)
    val piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
    val game: Game = new Game(1, mesh,piecesOutMap)
    val circle = Toolkit.getDefaultToolkit.getImage("C:/Software-Engineering/MADN-1/Bilder/Kreis.jpg")
    val infoLabel = new TextField("Put in the amount of Players/Houses/Cells to start the game")
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

    var mesh: Mesh = new Mesh(0,0,0)
    val dice1 = new Dice
    var fieldLabel = new Label
    var houseLabel = new Label
    var finishLabel = new Label

    val playeramountTF = new TextField()
    val houseamoutTF = new TextField()
    val cellamountTF = new TextField()

        def bottomPanel = new FlowPanel {
            infoLabel.preferredSize = new Dimension(690,50)
            infoLabel.editable = false
            contents += infoLabel
        }
        def topPanel = new FlowPanel {
            contents += rollDiceB
            contents += rollMagicDiceB
            playeramountTF.preferredSize = new Dimension(100,30)
            houseamoutTF.preferredSize = new Dimension(100,30)
            cellamountTF.preferredSize = new Dimension(100,30)
            contents += playeramountTF
            contents += houseamoutTF
            contents += cellamountTF
            contents += new Button("Start Game") {
                reactions += {
                    case event.ButtonClicked(_) =>
                        mesh = startGame()
                        val controller2 = new Controller(game.copy(1, mesh, piecesOutMap))
                        controller.game.setPieceChooser(0)
                        fieldLabel.text = controller.game.mesh10.field1.toString
                        houseLabel.text = controller.game.mesh10.house1.toString
                        finishLabel.text = controller.game.mesh10.finish1.toString
                        infoLabel.text = "Press the roll Button to roll"
                }
            }
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
        }
        contents = new BorderPanel {
        add(bottomPanel, BorderPanel.Position.South)
        add(topPanel, BorderPanel.Position.North)
        add(centerPanel, BorderPanel.Position.Center)
        add(rightPanel, BorderPanel.Position.East)
        add(leftPanel, BorderPanel.Position.West)
        }
    def startGame(): Mesh = {
        return Mesh(cellamountTF.text.toInt, playeramountTF.text.toInt, houseamoutTF.text.toInt)
    }
    def movePiece(rolledDice: Int): Unit = {
            controller.doAndPublish(controller.move1 , rolledDice)
            //controller.game.pieceChooser = 0
            fieldLabel.text = controller.game.mesh10.field1.toString
            houseLabel.text = controller.game.mesh10.house1.toString
            finishLabel.text = controller.game.mesh10.finish1.toString
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
