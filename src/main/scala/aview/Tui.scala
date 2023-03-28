package aview

import model.meshComponent.meshBase.Mesh
import model.diceComponent.diceBase.Dice
import scala.io.StdIn.readLine
import model.diceComponent.diceBase.DiceStrategy
import controller.Controller
import controller.ControllerInterface
import util.Observer
import scala.util.{Try,Success,Failure}
import model.Move

class Tui(controller: ControllerInterface) extends Observer:

    controller.add(this)
    
    override def update = println(controller.game.mesh10.mesh())
    val dice1 = new Dice
    def inputLoop(): Unit = {
        val output = readLine()
        if (output == "q") return
        processInputLine(output) match
            case None   => 
            case Some(rolledDice) => controller.doAndPublish(controller.move1,rolledDice)
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
            //case "save" => file.save(controller.game);None
            case _ => None
        }
    }