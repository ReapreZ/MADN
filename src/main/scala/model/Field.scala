package model

import scala.annotation.meta.field

case class Field(Cell: Int, Player: Int) {
    var cArr = (("x" + ("_" * Cell * Player)) * Player).toArray
    val eol = sys.props("line.separator")
    def cell() =
        //val size = Cell * Player * Player + Player
        cArr.mkString("") + eol + eol
}
