package model

case class Mesh() {
    val field1 = Field()
    val house1 = House()
    val finish1 = Finish()
    def mesh(Cell: Int, Player: Int, House: Int) = field1.cell(Cell, Player) + house1.housefield(House, Player) + finish1.finishfield(House, Player)
}