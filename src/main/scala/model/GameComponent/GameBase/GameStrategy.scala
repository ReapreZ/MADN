package model.GameComponent.GameBase
import model.MeshComponent.MeshBase.Mesh
import scala.util.{Try,Success,Failure}

trait GameStrategy {
    //def getOut(rolledDice:Int):Game
    //def checkinput(rolledDice:Int):Game
    def move(rolledDice:Int):Game
    def getTurnC(playerturn: Int): Try[Char]
}
