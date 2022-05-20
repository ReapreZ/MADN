package model.FieldComponent.FieldBase
import model.FieldFactory

final case class Field(Cell: Int, Player: Int) extends FieldFactory {
    override val Arr = (("x" + ("_" * Cell * Player)) * Player).toArray
}
