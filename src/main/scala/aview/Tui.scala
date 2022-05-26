package aview

import model.MeshComponent.MeshBase.Mesh
import model.DiceComponent.DiceBase.Dice
import scala.io.StdIn.readLine
import model.DiceComponent.DiceBase.DiceStrategy
import controller.Controller
import util.Observer
import scala.util.{Try,Success,Failure}

class Tui(controller: Controller) extends Observer:

    controller.add(this)
    
    override def update = println(controller.game.mesh10.mesh())

    val dice1 = new Dice

    def inputLoop(): Unit =
        processInputLine(readLine) match
            case None   => 
            case Some(move) => controller.doAndPublish(controller.put,move)
        inputLoop()

    def processInputLine(input: String): Option[Move] = {

        input match {
            case "r" =>
                println("Which Dice? 1 = RandomDice 2 = MagicDice")
                val diceread = readLine()
                dice1.dicestra(diceread.toInt)
                
            case "q" => return None
            case "undo" => controller.doAndPublish(controller.undo);None
            //case _ => return -1
        }
    }


