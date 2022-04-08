package model

case class Field() {
    val eol = sys.props("line.separator")
    def cell(Cell: Int, Player: Int) =
    ("x" + ("_" * Cell * Player)) * Player + eol
}
