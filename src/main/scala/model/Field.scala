package model

import scala.annotation.meta.field

case class Field() {

    val eol = sys.props("line.separator")
    def cell(Cell: Int, Player: Int) =
        var cArr = (("x" + ("_" * Cell * Player)) * Player).toArray
        //val size = Cell * Player * Player + Player
        
        cArr.mkString("") + eol

}
