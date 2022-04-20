package aview

import model.{Mesh, Field, House, Player}

class tui {

  def processInputLine(input: String, mesh: Mesh): Mesh = {
    input match {
      case "m" => mesh
      case "n" => new Mesh(2, 2, 2)

    }
  }

}
