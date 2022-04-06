package model

case class field(value: Int) {
  def isSet: Boolean = value != 0
  // def cell(Cell: Int = cellnumber, Player: Int = playernumber) =
  // ("x" + ("_" * Cell * playernumber)) * Player + eol * eolA
}
