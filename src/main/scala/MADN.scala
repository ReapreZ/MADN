package MADN

import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase.Game
import aview._
import aview.Gui.GuiSwing
import scala.util.{Try,Success,Failure}
import com.google.inject.Guice
import controller.ControllerInterface
object MADN {
    def main(args: Array[String]): Unit = {
        val injector = Guice.createInjector(new MADNModule)
        val controller = injector.getInstance(classOf[ControllerInterface])
        var input: String = ""
        var mesh = new Mesh(0)
        val piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
    
        val tui = new Tui(controller) 
        val gui = new GuiSwing(controller)
        tui.inputLoop()
        gui.closeOperation()
    }
}
//docker run --rm -it -e DISPLAY=172.20.156.25:0.0 madn