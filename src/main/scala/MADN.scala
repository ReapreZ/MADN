package de.htwg.madn.MADN

import de.htwg.madn.model.meshComponent.meshBase.Mesh
import de.htwg.madn.model.gameComponent.gameBase.Game
import de.htwg.madn.rest.Rest
import de.htwg.madn.gui.GuiSwing
import de.htwg.madn.tui.Tui
import de.htwg.madn.controller.ControllerInterface
import scala.util.{Try,Success,Failure}
import com.google.inject.Guice
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

object MADN {
    def main(args: Array[String]): Unit = {
        val injector = Guice.createInjector(new MADNModule)
        val controller = injector.getInstance(classOf[ControllerInterface])
        val rest = new Rest(controller)
        val tui = new Tui(controller)
        //val gui = new GuiSwing(controller)
        tui.inputLoop()
        //gui.closeOperation()
    }   
}
//docker run --rm -it -e DISPLAY=172.20.156.25:0.0 madn