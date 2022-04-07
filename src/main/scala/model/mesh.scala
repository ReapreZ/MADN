package model

case class mesh() {
    val field1 = field()
    val house1 = house()
    val finish1 = finish()
    def mesh(Cell: Int, Player: Int, House: Int) = field1.cell(Cell, Player) + house1.housefield(House, Player) + finish1.finishfield(House, Player)
}