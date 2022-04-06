@main def MADN: Unit =
  println("Welcome to Mensch aergere dich nicht!" + eol)
  println(mesh)

val eol = sys.props("line.separator")

val eolA = 2
val cellnumber = 2
val playernumber = 3
val housenumber = 2
//test
def cell(Cell: Int = cellnumber, Player: Int = playernumber) =
  ("x" + ("_" * Cell * playernumber)) * Player + eol * eolA

def house(Amount: Int = housenumber, Player: Int = playernumber) =
  (("A") * Amount + "  ") * Player + eol * eolA

def finish(Amount: Int = housenumber, Player: Int = playernumber) =
  (("-") * Amount + "  ") * Player + eol * eolA

val mesh = cell() + house() + finish()
