package model

case class finish() {
  val eol = sys.props("line.separator")
  def finishfield(Amount: Int, Player: Int) =
  (("-") * Amount + "  ") * Player + eol
}
