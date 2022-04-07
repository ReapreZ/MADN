package model

case class finish() {
  val eol = sys.props("line.separator") * 2
  def finishfield(Amount: Int, Player: Int) =
  (("-") * Amount + "  ") * Player + eol
}
