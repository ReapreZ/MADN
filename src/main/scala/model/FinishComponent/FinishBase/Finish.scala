package model.FinishComponent.FinishBase

final case class Finish(Amount: Int, Player: Int){
    val fArr = ((("-") * Amount + "  ") * Player).toArray
    val eol = sys.props("line.separator")
    def finishfield():String =
        fArr.mkString("") + eol
}
