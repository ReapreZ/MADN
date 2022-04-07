import model._

@main def MADN: Unit =
  println("Welcome to Mensch aergere dich nicht!" + eol)
  println(mesh1.mesh(fieldnumber, playernumber, housenumber))

val eol = sys.props("line.separator")
/*val field2 = new field()
val house2 = new house()
val finish2 = new finish()*/

val fieldnumber = 2
val playernumber = 2
val housenumber = 2
val mesh1 = mesh()

//val mesh = field2.cell(fieldnumber,playernumber) + house2.housefield(housenumber,playernumber) + finish2.finishfield(housenumber,playernumber)

/*
def field(Field: Int = fieldnumber, Player: Int = playernumber) =
  ("x" + ("_" * Field * Player)) * Player + eol * eolA

def house(Amount: Int = housenumber, Player: Int = playernumber) =
  (("H") * Amount + "  ") * Player + eol * eolA

def finish(Amount: Int = housenumber, Player: Int = playernumber) =
  (("-") * Amount + "  ") * Player + eol * eolA*/