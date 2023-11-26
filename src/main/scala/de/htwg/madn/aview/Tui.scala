package de.htwg.madn.aview

import de.htwg.madn.model.meshComponent.meshBase.Mesh
import de.htwg.madn.model.diceComponent.diceBase.Dice
import de.htwg.madn.model.diceComponent.diceBase.DiceStrategy
import de.htwg.madn.controller.controllerBase.Controller
import de.htwg.madn.controller.ControllerInterface
import de.htwg.madn.util.Observer
import de.htwg.madn.model.Move
import scala.io.StdIn.readLine
import scala.util.{Try,Success,Failure}
import de.htwg.madn.model.DataComponent.DataToJson

class Tui(controller: ControllerInterface) extends Observer{

    controller.add(this)
    val data: DataToJson = new DataToJson()
    
    override def update = { println(controller.game.mesh.mesh()) }
    val dice1 = new Dice
    def inputLoop(): Unit = {
        val output = readLine()
        if (output == "q") return
        processInputLine(output) match {
            case None   => ()
            case Some(rolledDice) => controller.doAndPublish(controller.move1,rolledDice)
        }
        inputLoop()
    }
    def processInputLine(input: String): Option[Int] = {
        data.playeramount = 4
        val a = data.getPlayerAmountAsJson
        data.setPlayerTurnFromJson(a)
        val playertuuurn = data.getPlayerTurnAsJson
        print(data.toJsonString(playertuuurn) + "<----- \n")
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
            case _ => None
        }
    }
}