package MADN

import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase.Game
import aview._
import controller._
import aview.Gui.GuiSwing
import org.scalactic.Fail
import scala.util.{Try,Success,Failure}
import com.google.inject.Guice
import model.gameComponent.GameInterface


object MADN {

    val injector = Guice.createInjector(new MADNModule)
    val game = injector.getInstance(classOf[GameInterface])
    val controller = injector.getInstance(classOf[ControllerInterface])

    def main(args: Array[String]): Unit = {
        var input: String = ""
        var mesh = new Mesh(0)
        val piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
        val meshtry = mesh.startgame()
        meshtry match {
            case Success(v) => mesh = v
            case Failure(e) => print(e.getMessage)
        }
        //val controller = new Controller(new Game(1, mesh,piecesOutMap))
        val tui = new Tui(controller) 
        val gui = new GuiSwing(controller)
        tui.inputLoop()
        gui.closeOperation()
    }
}