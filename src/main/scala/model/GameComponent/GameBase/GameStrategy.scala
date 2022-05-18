package model.GameComponent.GameBase
import model.MeshComponent.MeshBase.Mesh

trait GameStrategy {
    def getOut(rolledDice:Int,mesh1:Mesh):Game
    def checkinput(rolledDice:Int,mesh1:Mesh):Game
    def move(rolledDice:Int,mesh1:Mesh):Game
    def getTurnC(playerturn: Int):Char
}
