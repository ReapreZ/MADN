package model

case class Mesh(Cell: Int, Player: Int, Housenumber: Int) {
    val field1 = Field(Cell, Player)
    val house1 = House(Housenumber, Player)
    val finish1 = Finish(Housenumber, Player)
    def mesh() = field1.cell() + house1.housefield() + finish1.finishfield()
}