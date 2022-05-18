package model.FieldComponent.FieldBase
import scala.annotation.meta.field

final case class Field(Cell: Int, Player: Int) {
    var cArr = (("x" + ("_" * Cell * Player)) * Player).toArray
    val eol = sys.props("line.separator")
    def cell(): String =
        //val size = Cell * Player * Player + Player
        cArr.mkString("") + eol + eol
}
