package model.FieldComponent.FieldBase
import model.Factory

final case class Field(Cell: Int, Player: Int) extends Factory {
    override val Arr = (("x" + ("_" * Cell * Player)) * Player).toArray
}
