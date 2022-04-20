package model

case class Finish() {
    val eol = sys.props("line.separator")
    def finishfield(Amount: Int, Player: Int) =
        val fArr = ((("-") * Amount + "  ") * Player).toArray
        fArr.mkString("") + eol
}
