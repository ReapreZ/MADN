package model.FinishComponent.FinishBase
import model.Factory

final case class Finish(Amount: Int, Player: Int) extends Factory{
    override val Arr = ((("-") * Amount + "  ") * Player).toArray
}