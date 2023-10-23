package de.htwg.madn.tui

import de.htwg.madn.model.meshComponent.meshBase.Mesh
import de.htwg.madn.model.diceComponent.diceBase.Dice
import de.htwg.madn.model.Move
import de.htwg.madn.model.diceComponent.diceBase.DiceStrategy
import de.htwg.madn.controller.controllerBase.Controller
import de.htwg.madn.controller.ControllerInterface
import de.htwg.madn.util.Observer
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

class Tui(controller: ControllerInterface) extends Observer {

    controller.add(this)
    
    //override def update = println(controller.game.mesh.mesh())
    override def update: Unit = {}
    val dice1 =  {new Dice }
    def inputLoop(): Unit = {
        val output = readLine()
        if (output == "q") return
        processInputLine(output) match {
            case None   => ()
            case Some(rolledDice) => controller.doAndPublish(controller.move1,rolledDice)
        }
        println(controller.game.mesh.mesh())
        inputLoop()
    }
    def processInputLine(input: String): Option[Int] = {
        input match {
            case "r" =>
                println("Which Dice? 1 = RandomDice 2 = MagicDice")
                val diceread = readLine()
                val rolledDice = dice1.dicestra(diceread.toInt)
                println("\nYou rolled a " + rolledDice + "\n")
                Some(rolledDice)
            case "q" => None
            case "undo" => controller.doAndPublish(controller.undo);None
            case "redo" => controller.doAndPublish(controller.redo);None
            case "insertInDB" => controller.insertInDB();None
            case "readFromDB" => controller.readFromDB();None
            //case "save" => file.save(controller.game);None
            case _ => None
        }
    }

    val tuiRoute = get {
        val mesh: String = controller.game.mesh.mesh()
        concat(
            path("mesh") {
                complete(mesh)
            },
            path("processInput"/ Remaining) { (input: String) =>
                complete(processInputLine(input).toString)
            }
        )
    }
    
}