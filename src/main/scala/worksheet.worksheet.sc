val eol = sys.props("line.separator")
def lineo(oamount: Int = 4) = "o|" * oamount
def linee(eamount: Int = 4) = "e|" * eamount
def lineone(oneamount: Int = 31) = "|" + ("-") * oneamount + "|" + eol
def linethree(color: String, color2: String) =
  "|" + color + "|" + color + "|        |o|e|o|        |" + color2 + "|" + color2 + "|" + eol

val line2 = "|y|y|        |o|o|s|        |g|g|" + eol
val linee = ("|            |o|e|o|            |" + eol) * 2
val line4 = "|    |s|" + lineo() + "e|" + lineo() + "o|    |" + eol
val line5 = "|    |o|" + linee() + "+|" + linee() + "o|    |" + eol
val line6 = "|    |o|" + lineo() + "e|" + lineo() + "s|    |" + eol
val line8 = "|b|b|        |s|o|o|        |r|r|" + eol

val mesh = eol + lineone() + line2 + linethree(
  "y",
  "g"
) + linee + line4 + line5 + line6 + linee + linethree(
  "b",
  "r"
) + line8 + lineone()
