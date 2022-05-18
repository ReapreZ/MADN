package Controller


import model.GameComponent.GameBase.Game
import model.MeshComponent.MeshBase.Mesh
import model.DiceComponent.DiceBase.Dice
import scala.io.StdIn.readLine
import util.Observable

class Controller(var mesh:Mesh) extends Observable {

    var game = new Game(0,new Mesh(0,0,0),0,0,0,0)

    def doAndPublish(doThis: (Int,Mesh) => Game,rolledDice:Int,mesh:Mesh) =
    game = doThis(rolledDice,mesh)
    notifyObservers

    def getOut1(rolledDice:Int,mesh1:Mesh): Game= {
        game.getOut(rolledDice,mesh1)
     
    }
    def move1(rolledDice:Int,mesh1:Mesh):Game= {
        game.move(rolledDice,mesh1)
       
    }

    def checkinput1(rolledDice:Int,mesh1:Mesh):Game= {
        game.checkinput(rolledDice,mesh1)
    
    }

}