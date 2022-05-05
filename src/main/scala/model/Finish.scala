package model

case class Finish(Amount: Int, Player: Int) {
    val fArr = ((("-") * Amount + "  ") * Player).toArray
    val eol = sys.props("line.separator")
    def finishfield() =
        fArr.mkString("") + eol
}
