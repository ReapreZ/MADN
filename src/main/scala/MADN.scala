package MADN


import scala.util.{Failure, Success, Try}
import com.google.inject.Guice
import gui.GuiSwing

object MADN {
    def main(args: Array[String]): Unit = {
        val injector = Guice.createInjector(new MADNModule)
        val controller = injector.getInstance(classOf[ControllerInterface])
        val tui = new Tui(controller) 
        val gui = new GuiSwing(controller)
        tui.inputLoop()
        gui.closeOperation()
    }
}
//docker run --rm -it -e DISPLAY=172.20.156.25:0.0 madn