package aview.Gui

import util._
import scala.swing._
import javax.swing.{JPanel, JScrollPane, SwingUtilities}
import BorderPanel.Position._
import scala.swing.Swing.LineBorder
import java.awt.BorderLayout
import scala.swing.event._
import controller.Controller
import model.DiceComponent.DiceBase.Dice
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game

class GuiSwing(controller: Controller) extends MainFrame with Observer{
     //listenTo(controller)
    var mesh: Mesh = new Mesh(0,0,0)
    override def update = println()
    val infolabel = new TextField("Put in the amount of Players/Houses/Cells to start the game")
    val rollDiceB = new Button("Roll the Dice")
    title = "Mensch ärgere dich nicht!"
    preferredSize = new Dimension(800, 700)
    val dice1 = new Dice
        def infoPanel = new FlowPanel {
            infolabel.preferredSize = new Dimension(690,50)
            infolabel.editable = false
            contents += infolabel
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
                        val mesh = new Mesh(cellamountTF.text.toInt, playeramountTF.text.toInt, houseamoutTF.text.toInt) 
                        val controller = new Controller(new Game(1, mesh,0,0,0,0))
                }
            }
        }
        contents = new BorderPanel {
            add(infoPanel, BorderPanel.Position.South)
            add(topPanel, BorderPanel.Position.North)
        }
        
        rollDiceB.reactions += {
            case event.ButtonClicked(`rollDiceB`) =>
                val rolledDice = dice1.magicDice()
                infolabel.text = "You rolled a " + rolledDice.toString
                controller.doAndPublish(controller.checkinput1 , rolledDice)
        }
    
    override def closeOperation() = {
        this.close()
    }
    pack()
    centerOnScreen()
    open()
}
