package de.htwg.madn.model.DataComponent

class Data {
  
    var pieceList: Array[Array[Int]] = Array(
        Array(0, 0), Array(1, 0), Array(0, 1), Array(1, 1),
        Array(9, 0), Array(10, 0), Array(9, 1), Array(10, 1),
        Array(0, 9), Array(1, 9), Array(0, 10), Array(1, 10),
        Array(9, 9), Array(10, 9), Array(9, 10), Array(10, 10)
    );
    
    var playerturn: Int = 0;

    var piecesOut: Array[Int] = Array(0, 0, 0, 0);

    var timesPlayerRolled: Int = 0;

    var playeramount: Int = 0;

    var rolledDice: Int = 1;

}
