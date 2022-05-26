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

class GuiSwing(controller: Controller) extends MainFrame with Observer{
    //listenTo(controller)
    controller.add(this)
    title = "Mensch ärgere dich nicht!"
    preferredSize = new Dimension(800, 700)

    val circle = Toolkit.getDefaultToolkit.getImage("C:/Software-Engineering/MADN-1/Bilder/Kreis.jpg")
    val infoLabel = new TextField("Put in the amount of Players/Houses/Cells to start the game")
    val rollDiceB = new Button("Roll the Dice")
    val testB = new Button
    val testB2 = new Button
    val testB3 = new Button
    val testB4 = new Button
    val testB5 = new Button

    var mesh: Mesh = new Mesh(0,0,0)
    val dice1 = new Dice
    var fieldLabel = new Label
    var houseLabel = new Label
    var finishLabel = new Label

        def bottomPanel = new FlowPanel {
            infoLabel.preferredSize = new Dimension(690,50)
            infoLabel.editable = false
            contents += infoLabel
        }
        def topPanel = new FlowPanel {
            contents += rollDiceB
            listenTo(rollDiceB)
            val playeramountTF = new TextField()
            playeramountTF.preferredSize = new Dimension(100,30)
            val houseamoutTF = new TextField()
            houseamoutTF.preferredSize = new Dimension(100,30)
            val cellamountTF = new TextField()
            cellamountTF.preferredSize = new Dimension(100,30)
            contents += playeramountTF
            contents += houseamoutTF
            contents += cellamountTF
            contents += new Button("Start Game") {
                reactions += {
                    case event.ButtonClicked(_) => 
                        mesh = new Mesh(cellamountTF.text.toInt, playeramountTF.text.toInt, houseamoutTF.text.toInt)
                        val controller = new Controller(new Game(1, mesh,0,0,0,0))
                        println("this is the mesh" + mesh.mesh())
                        fieldLabel.text = controller.game.mesh10.field1.toString
                        houseLabel.text = controller.game.mesh10.house1.toString
                        finishLabel.text = controller.game.mesh10.finish1.toString
                        
                }
            }
        }

        def centerPanel = new GridPanel(3,3) {
            /*contents += testB
            contents += testB2
            contents += testB3
            contents += testB4
            contents += testB5*/
            contents += fieldLabel
            contents += houseLabel
            contents += finishLabel
            fieldLabel.font = new Font("Arial", 0, 20)
            houseLabel.font = new Font("Arial", 0, 20)
            finishLabel.font = new Font("Arial", 0, 20)
            testB.preferredSize = new Dimension(50,50)

            /*testB.icon = 
                val imagePath = "C:/Software-Engineering/MADN-1/src/main\resources/Icons/Kreis.png"
                val image: BufferedImage = Try(ImageIO.read(new File(imagePath))) match {
                    case s: Success[BufferedImage] => s.value
                    case f: Failure[BufferedImage] =>
                    new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)
                    }
                new ImageIcon(image)
            //testB.icon = image */
        }
        
        rollDiceB.reactions += {
            case event.ButtonClicked(`rollDiceB`) =>
                val rolledDice = dice1.diceRandom()
                infoLabel.text = "You rolled a " + rolledDice.toString
                controller.doAndPublish(controller.checkinput1 , rolledDice)
                fieldLabel.text = controller.game.mesh10.field1.toString
                houseLabel.text = controller.game.mesh10.house1.toString
                finishLabel.text = controller.game.mesh10.finish1.toString
        }

        contents = new BorderPanel {
        add(bottomPanel, BorderPanel.Position.South)
        add(topPanel, BorderPanel.Position.North)
        add(centerPanel, BorderPanel.Position.Center)
        }
    
    override def closeOperation() = this.close()
    override def update = println()
    pack()
    centerOnScreen()
    open()
}
