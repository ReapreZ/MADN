package de.htwg.madn.start

import de.htwg.madn.model.meshComponent.meshBase.Mesh
import de.htwg.madn.model.gameComponent.gameBase.Game
import de.htwg.madn.controller.ControllerInterface
import de.htwg.madn.aview._
import scala.util.{Try,Success,Failure}
import com.google.inject.Guice

object MADN {
    def main(args: Array[String]): Unit = {
        val injector = Guice.createInjector(new MADNModule)
        val controller = injector.getInstance(classOf[ControllerInterface])
        val tui = new Tui(controller) 
        //val gui = new GuiSwing(controller)
        tui.inputLoop()
        //gui.closeOperation()
    }
}
//docker run --rm -it -e DISPLAY=172.20.156.25:0.0 madn