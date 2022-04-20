package model

case class House() {
    val eol = sys.props("line.separator")
    def housefield(Amount: Int, Player: Int) =
        val hArr = ((("H") * Amount + "  ") * Player).toArray
        hArr.mkString("") + eol

}
