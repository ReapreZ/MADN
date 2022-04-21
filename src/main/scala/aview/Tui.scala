package aview

import model.{Mesh, Field, House, Player, Dice}

class Tui {
    var mesh1 = new Mesh(0,0,0)
    var playernumber = 1
    var playerturn = 1
    val dice1 = new Dice
    def startgame(input: String) = {
        input match {
            case "1" => playeramount(1)
            case "2" => playeramount(2)
            case "3" => playeramount(3)
            case "4" => playeramount(4)
        }
    }

    def processInputLine(input: String, mesh: Mesh): Int = {
        mesh1 = mesh
        input match {
            case "r" => dice1.diceRandom()
            case "q" => return 0
        }
    }  
    def playeramount(playeramount: Int) = {
        playernumber = playeramount
    }
    def checkinput(input: String, output: Int) = {
        if (input == "r") {
            turn(output)
            if(playerturn == 1) println("It is Player A's turn")
            if(playerturn == 2) println("It is Player B's turn")
            if(playerturn == 3) println("It is Player C's turn")
            if(playerturn == 4) println("It is Player D's turn")
        }
    }
    def turn(output: Int) = {
        if(output == 6) {
            val player2 = mesh1.field1.Player * mesh1.field1.Cell + 1
            //println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
            //println("Player A can move out one Piece\n")
            println("You can roll again")
            playerturn match {
                case 1 => mesh1.field1.cArr(0) = 'A'
                    //mesh1.house1.hArr(0) = 'H'
                case 2 => mesh1.field1.cArr(player2) = 'B'
                    //mesh1.house1.hArr(4) = 'H'
                case 3 => mesh1.field1.cArr(player2 * 2) = 'C'
                    //mesh1.house1.hArr(8) = 'H'
                case 4 => mesh1.field1.cArr(player2 * 3) = 'D'
                    //mesh1.house1.hArr(12) = 'H'
            }
        } else if(output != 6) {
            if(playerturn == playernumber)
                playerturn = 1
            else playerturn += 1
        }
        println(mesh1.mesh())
        move(output)
    }
    def move(output: Int) = {
        println(mesh1.field1.cArr.indexOf('x')
        )
    }
}