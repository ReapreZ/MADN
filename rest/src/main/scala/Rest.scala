package rest

import controller.ControllerInterface
import util.Observer
import scala.concurrent.ExecutionContext
import aview.Tui

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.model.* 
import akka.http.scaladsl.server.Directives.*
import model.fileIOComponent.fileIOJsonImpl.FileIO
import model.diceComponent.diceBase.Dice


class Rest(controller: ControllerInterface) extends Observer {
    controller.add(this)
    override def update: Unit = {}

    implicit val system: ActorSystem = ActorSystem()
    implicit val executionContext: ExecutionContext = system.dispatcher
    val tui = new Tui(controller)
    val fileio = new FileIO
    val dice = new Dice

    val controllerRoute = 
        get {
            concat(
                path("move" / IntNumber) { (rolledDice: Int) =>
                    complete(controller.move1(rolledDice).toString)
                },
                path("getTurn" / IntNumber) { (playerturn: Int) =>
                    complete(controller.getTurnC1(playerturn).toString)
                },
                path("playerturn") {
                    complete(controller.game.playerturn.toString)
                },
                path("undo") {
                    complete(controller.undo.toString)
                },
                path("redo") {
                    complete(controller.redo.toString)
                },
                path("save") {
                    complete(controller.save.toString)
                }, 
                path("load") {
                    complete(controller.load.toString)
                },
                path("") {
                    sys.error("FEHLER")
                }
            )
        }
    /*val controllerUpdateRoute = put {
        path("playerturn" / IntNumber) { (playerturn: Int) =>
            complete(controller.game = controller.game.updatePlayerTurn)
        }
    }*/
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(concat(controllerRoute, tui.tuiRoute, fileio.fileIoRoute, dice.diceRoute))
}