package MADN.model

case class field(value: Int) {

  // def field(Field: Int = fieldnumber, Player: Int = playernumber) =
  // ("x" + ("_" * Cell * playernumber)) * Player + eol * eolA
  def isSet: Boolean = value != 0
}
