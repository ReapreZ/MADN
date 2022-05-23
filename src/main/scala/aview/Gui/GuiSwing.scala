package aview.Gui

import util._
import scala.swing._
import javax.swing.{JPanel, JScrollPane, SwingUtilities}
import scala.swing.Swing.LineBorder
import java.awt.BorderLayout
import scala.swing.event._
import controller.Controller
import model.DiceComponent.DiceBase.Dice
import model.MeshComponent.MeshBase.Mesh
import scala.util.{Try,Success,Failure}
import model.GameComponent.GameBase.Game

class GuiSwing(controller: Controller) extends MainFrame with Observer{
    var mesh: Mesh = new Mesh(0,0,0)
    override def update = println()

    //listenTo(controller)
    title = "Mensch ärgere dich nicht!"
    preferredSize = new Dimension(800, 700)
    val dice1 = new Dice
    contents = new FlowPanel {
        val rolledDiceTF = new TextField()
        rolledDiceTF.editable = false
        contents += rolledDiceTF
        contents += new Button("Roll the Dice") {
            reactions += {
                case event.ButtonClicked(_) =>
                    rolledDiceTF.text = "You rolled a " + controller.doAndPublish(controller.checkinput1 , dice1.magicDice())
            }
        }
    }
    contents = new FlowPanel {
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
                    println(cellamountTF.text.toInt)
                    val mesh = new Mesh(cellamountTF.text.toInt, playeramountTF.text.toInt, houseamoutTF.text.toInt) 
                    val controller = new Controller(new Game(1, mesh,0,0,0,0))
            }
        }
    }
    override def closeOperation() = {
        this.close()
    }
  
    pack()
    centerOnScreen()
    open()
}
