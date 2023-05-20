package MADN

import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase.Game
import aview._
import rest.Rest
import aview.Gui.GuiSwing
import scala.util.{Try,Success,Failure}
import com.google.inject.Guice
import controller.ControllerInterface
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.* 
import akka.http.scaladsl.server.Directives.*

@main
def main: Unit = {
    val injector = Guice.createInjector(new MADNModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    val rest = new Rest(controller)
    val tui = new Tui(controller)
    //val gui = new GuiSwing(controller)
    tui.inputLoop()
    //gui.closeOperation()
}
//docker run --rm -it -e DISPLAY=172.20.156.25:0.0 madn