@main def MADN: Unit =
  println("Welcome to Mensch aergere dich nicht!" + eol)
  println(mesh)
  // test aa

val eol = sys.props("line.separator")

val eolA = 2
val fieldnumber = 2
val playernumber = 2
val housenumber = 2
def field(Field: Int = fieldnumber, Player: Int = playernumber) =
  ("x" + ("_" * Field * playernumber)) * Player + eol * eolA

def house(Amount: Int = housenumber, Player: Int = playernumber) =
  (("A") * Amount + "  ") * Player + eol * eolA

def finish(Amount: Int = housenumber, Player: Int = playernumber) =
  (("-") * Amount + "  ") * Player + eol * eolA

val mesh = field() + house() + finish()
