package model

case class House() {
    val eol = sys.props("line.separator")
    def housefield(Amount: Int, Player: Int) =
        (("H") * Amount + "  ") * Player + eol

}
