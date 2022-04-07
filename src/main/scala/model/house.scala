package model

case class house() {
  val eol = sys.props("line.separator")
  def housefield(Amount: Int, Player: Int) =
    (("H") * Amount + "  ") * Player + eol

}