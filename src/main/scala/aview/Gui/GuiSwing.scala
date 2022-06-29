package aview.Gui

import util._
import scala.swing._
import javax.swing.{JPanel, JScrollPane, SwingUtilities, ImageIcon}
import java.awt.{BorderLayout, Image, Toolkit, Font}
import java.awt.Font._
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import scala.util.{Try, Success, Failure}
import java.io.File
import scala.swing.event._
import controller.Controller
import model.diceComponent.diceBase.Dice
import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase.Game
import model.gameComponent.GameInterface
import scala.language.postfixOps
import controller.ControllerInterface

class GuiSwing(controller: ControllerInterface) extends MainFrame with Observer{
    controller.add(this)
    var oldDice: Int = 0
    var playerturnC = ' '
    title = "Mensch ärgere dich nicht!"
    preferredSize = new Dimension(1440, 720)
    var piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
    val game2 = new Game(1, mesh,piecesOutMap)
    var infoLabel = new TextField("Put in the amount of Players to start the game") {
        background = java.awt.Color.GRAY
        foreground = java.awt.Color.WHITE
        font = new Font("Arial Black", 0, 12)
    }
    val rollDiceB = new Button("Roll the Dice")
    listenTo(rollDiceB)
    val rollMagicDiceB = new Button("Magic Dice")
    listenTo(rollMagicDiceB)
    val piece1B = new Button("1")
    listenTo(piece1B)
    val piece2B = new Button("2")
    listenTo(piece2B)
    val piece3B = new Button("3")
    listenTo(piece3B)
    val piece4B = new Button("4")
    listenTo(piece4B)
    val undoB = new Button("Undo")
    listenTo(undoB)
    val redoB = new Button("Redo")
    listenTo(redoB)
    val houseAIcon = new ImageIcon("src/main/resources/Icons/PlayerAHome.png")
    val houseBIcon = new ImageIcon("src/main/resources/Icons/PlayerBHome.png")
    val houseCIcon = new ImageIcon("src/main/resources/Icons/PlayerCHome.png")
    val houseDIcon = new ImageIcon("src/main/resources/Icons/PlayerDHome.png")
    //val PC1H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerCHome.png") }
    val PlayerC = new ImageIcon("src/main/resources/Icons/PlayerC.png")
    /*val PC2H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerCHome.png") }
    val PC3H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerCHome.png") }
    val PC4H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerCHome.png") }
    val PD1H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerDHome.png") }*/
    val PlayerD = new ImageIcon("src/main/resources/Icons/PlayerD.png")
    /*val PD2H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerDHome.png") }
    val PD3H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerDHome.png") }
    val PD4H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerDHome.png") }
    val PA1H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerAHome.png") }*/
    val PlayerA = new ImageIcon("src/main/resources/Icons/PlayerA.png")
    val PA1 = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerA.png") }
    /*val PA2H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerAHome.png") }
    val PA3H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerAHome.png") }
    val PA4H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerAHome.png") }
    val PB1H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerBHome.png") }*/
    val PlayerB = new ImageIcon("src/main/resources/Icons/PlayerB.png")
    /*val PB2H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerBHome.png") }
    val PB3H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerBHome.png") }
    val PB4H = new Label { icon = new ImageIcon("src/main/resources/Icons/PlayerBHome.png") }*/
    val kreis = new ImageIcon("src/main/resources/Icons/EmptyKreis.png")
    val circle1 = new Label { icon = kreis }
    val circle2 = new Label { icon = kreis }
    val circle3 = new Label { icon = kreis }
    val circle4 = new Label { icon = kreis }
    val circle5 = new Label { icon = kreis }
    val circle6 = new Label { icon = kreis }
    val circle7 = new Label { icon = kreis }
    val circle8 = new Label { icon = kreis }
    val ziel = new ImageIcon("src/main/resources/Icons/EmptyFinish.png")
    val fin = Array(new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label)
    var i = 0
    while (i < 16)
        fin(i).icon = ziel
        i = i + 1
    val house = Array(new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label, new Label)
    i = 0
    while (i < 16)
        if(i <= 3)
            house(i).icon = houseAIcon
        else if(i <= 7 && i >= 4)
            house(i).icon = houseBIcon
        else if(i <= 11 && i >= 8)
            house(i).icon = houseCIcon
        else
            house(i).icon = houseDIcon
        i = i + 1
    val platzhalter = new Label("       ")
    val platzhalter1 = new Label("       ")
    val feld = new ImageIcon("src/main/resources/Icons/NormalField.png")
    val field = Array(new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label,new Label, new Label, new Label, new Label)
    i = 1
    while (i < 40)
        field(i).icon = feld
        i = i + 1
    val feldAStart = new ImageIcon("src/main/resources/Icons/PlayerAStartField.png")
    field(0) = new Label { icon = feldAStart }

    var mesh: Mesh = new Mesh(0)
    val dice1 = new Dice
    var fieldLabel = new Label
    var houseLabel = new Label
    var finishLabel = new Label

    val playeramountTF = new TextField()
    val houseamoutTF = new TextField()
    val cellamountTF = new TextField()


    menuBar = new MenuBar {
        contents += new Menu("Game") {
            contents += MenuItem(Action("Save"){
                controller.doAndPublish(controller.save)
            })
            contents += MenuItem(Action("Load"){
                controller.doAndPublish(controller.load)
                var j = 0
                while (j < 40) {
                    controller.game.mesh10.field1.Arr(j) match {
                        case '_' => field(j).icon = feld
                        case 'A' => field(j).icon = PlayerA
                        case 'B' => field(j).icon = PlayerB
                        case 'C' => field(j).icon = PlayerC
                        case 'D' => field(j).icon = PlayerD
                        case 'x' => field(j).icon = feld
                    }
                    j = j + 1
                }
                j = 0
                while (j < 16)
                    controller.game.mesh10.finish1.Arr(j) match {
                        case '-' => fin(j).icon = ziel
                        //case 'A' => fin(j).icon = 
                    }
                    j = j + 1
            })
        }
        contents += undoB
        contents += redoB
        contents += piece1B
        contents += piece2B
        contents += piece3B
        contents += piece4B
        piece1B.preferredSize = new Dimension(50,30)
        piece2B.preferredSize = new Dimension(50,30)
        piece3B.preferredSize = new Dimension(50,30)
        piece4B.preferredSize = new Dimension(50,30)
    }
    def bottomPanel = new FlowPanel {
        infoLabel.preferredSize = new Dimension(560,50)
        i = 7
        contents += house(8)
        contents += house(9)
        contents += house(10)
        contents += house(11)
        //while(i < 11)
        //    contents += house(i)
        //    i = i + 1
        contents += infoLabel
        contents += house(12)
        contents += house(13)
        contents += house(14)
        contents += house(15)
        //i = 0
        //while(i < 15)
        //    contents += house(i)
        //    i = i + 1
    }
    def topPanel = new FlowPanel {
        contents += circle1
        circle1.visible = false
        contents += house(0)
        contents+= circle2
        circle2.visible = false
        contents += house(1)
        contents += circle3
        circle3.visible = false
        contents += house(2)
        contents += circle4
        circle4.visible = false
        contents += house(3)
        contents += rollDiceB
        rollDiceB.preferredSize = new Dimension(140,30)
        contents += rollMagicDiceB
        rollMagicDiceB.preferredSize = new Dimension(140,30)
        //houseamoutTF.preferredSize = new Dimension(100,30)
        //cellamountTF.preferredSize = new Dimension(100,30)
        contents += playeramountTF
        playeramountTF.preferredSize = new Dimension(140,30)
        //contents += houseamoutTF
        //contents += cellamountTF
        contents += new Button("Start Game") {
            preferredSize = new Dimension(120,30)
            reactions += {
                case event.ButtonClicked(_) =>
                    mesh = startGame()
                    //controller.game = game.copy(1, mesh, piecesOutMap)
                    controller.game.pieceChooser = 0
                    updateField()
                    infoLabel.text = "Press the roll Button to roll the dice"
            }
        }
        contents += circle5
        circle5.visible = false
        contents += house(4)
        contents += circle6
        circle6.visible = false
        contents += house(5)
        contents += circle7
        circle7.visible = false
        contents += house(6)
        contents += circle8
        circle8.visible = false
        contents += house(7)
    }
    def rightPanel = new GridPanel(9,1) {
        contents += fin(8)
        contents += fin(9)
        contents += fin(10)
        contents += fin(11)
        contents += platzhalter1
        contents += fin(12)
        contents += fin(13)
        contents += fin(14)
        contents += fin(15)
    }
    def centerPanel = new GridPanel(4,10) {
        //contents += fieldLabel
        contents += field(0)
        i = 1
        while(i < 40)
            contents += field(i)
            i = i + 1
        //contents += houseLabel
        //contents += finishLabel
        //fieldLabel.font = new Font("Arial", 0, 20)
        //houseLabel.font = new Font("Arial", 0, 20)
        //finishLabel.font = new Font("Arial", 0, 20)
    }
    def leftPanel = new GridPanel(9,1) {
        contents += fin(0)
        contents += fin(1)
        contents += fin(2)
        contents += fin(3)
        contents += platzhalter
        contents += fin(4)
        contents += fin(5)
        contents += fin(6)
        contents += fin(7)

    }
    reactions += {
        case event.ButtonClicked(`rollDiceB`) =>
            println(controller.game.pieceChooser.toString)
            val rolledDice = dice1.diceRandom()
            controller.game.getTurnC(controller.game.playerturn) match {
                case Success(v) => playerturnC = v
                case Failure(e) => println(e.getMessage)
            }
            if(oldDice == 0)
                infoLabel.text = "It is Player's " + playerturnC + " and you rolled a " + rolledDice.toString
                checkForPieceChoosing(rolledDice)
            else 
                infoLabel.text = "It is Player's " + playerturnC + " and you rolled a " + oldDice.toString
                checkForPieceChoosing(oldDice)
                oldDice = 0
        case event.ButtonClicked(`rollMagicDiceB`) =>
            //println(controller.game.pieceChooser.toString)
            val rolledDice = dice1.magicDice(6)
            controller.game.getTurnC(controller.game.playerturn) match {
                case Success(v) => playerturnC = v
                case Failure(e) => println(e.getMessage)
            }
            infoLabel.text = "It is Player's " + playerturnC + " and you rolled a " + rolledDice.toString
            checkForPieceChoosing(rolledDice)
        case event.ButtonClicked(`piece1B`) => controller.game.pieceChooser = 1
        case event.ButtonClicked(`piece2B`) => controller.game.pieceChooser = 2
        case event.ButtonClicked(`piece3B`) => controller.game.pieceChooser = 3
        case event.ButtonClicked(`piece4B`) => controller.game.pieceChooser = 4
        case event.ButtonClicked(`undoB`) =>
            controller.doAndPublish(controller.undo)
            updateField()
        case event.ButtonClicked(`redoB`) =>
            controller.doAndPublish(controller.redo)
            updateField()
    }
    contents = new BorderPanel {
    add(bottomPanel, BorderPanel.Position.South)
    add(topPanel, BorderPanel.Position.North)
    add(centerPanel, BorderPanel.Position.Center)
    add(rightPanel, BorderPanel.Position.East)
    add(leftPanel, BorderPanel.Position.West)
    }
    def startGame(): Mesh = {
        return Mesh(playeramountTF.text.toInt)
    }
    def move(rolledDice: Int): Unit = {
            controller.doAndPublish(controller.move1 , rolledDice)
            //field(controller.game.mesh10.piecepos(controller.game.playerturn - 1)(controller.game.pieceChooser - 1)).icon = feld
            //field((controller.game.mesh10.piecepos(controller.game.playerturn - 1)(controller.game.pieceChooser - 1)) + rolledDice).icon = PlayerA
            controller.game.pieceChooser = 0
            updateField()
    }
    def movePiece(rolledDice: Int): Unit = {
        controller.game.playerturn match {
            case 1 => 
                field(controller.game.mesh10.piecepos(controller.game.playerturn - 1)(1 - 1)).icon = feld
                field((controller.game.mesh10.piecepos(controller.game.playerturn - 1)(1 - 1)) + rolledDice).icon = PlayerA
            case 2 =>
                field(controller.game.mesh10.piecepos(controller.game.playerturn - 1)(1 - 1)).icon = feld
                field((controller.game.mesh10.piecepos(controller.game.playerturn - 1)(1 - 1)) + rolledDice).icon = PlayerB
            case 3 =>
                field(controller.game.mesh10.piecepos(controller.game.playerturn - 1)(1 - 1)).icon = feld
                field((controller.game.mesh10.piecepos(controller.game.playerturn - 1)(1 - 1)) + rolledDice).icon = PlayerC
            case 4 =>
                field(controller.game.mesh10.piecepos(controller.game.playerturn - 1)(1 - 1)).icon = feld
                field((controller.game.mesh10.piecepos(controller.game.playerturn - 1)(1 - 1)) + rolledDice).icon = PlayerD
        }
    }
    def movePieceOut(): Unit = {
        controller.game.playerturn match {
            case 1 => field(0).icon = PlayerA
            case 2 => field(10).icon = PlayerB
            case 3 => field(20).icon = PlayerC
            case 4 => field(30).icon = PlayerD
        }
    }
    def updateField() : Unit = {
        fieldLabel.text = controller.game.mesh10.field1.toString
        //houseLabel.text = controller.game.mesh10.house1.toString
        //finishLabel.text = controller.game.mesh10.finish1.toString
        if(piecesOutMap != controller.game.piecesOutMap)
            var i = 0
            //while (i < 4)
                if(piecesOutMap(0) != controller.game.piecesOutMap(0))
                    controller.game.piecesOutMap(0) match {
                        case 0 => circle1.visible = false
                        case 1 => 
                            house(0).visible = false
                            circle1.visible = true
                        case 2 =>
                            house(1).visible = false
                            circle2.visible = true
                        case 3 =>
                            house(2).visible = false
                            circle3.visible = true
                        case 4 =>
                            house(3).visible = false
                            circle4.visible = true
                    }
                if(piecesOutMap(1) != controller.game.piecesOutMap(1))
                    controller.game.piecesOutMap(1) match {
                        case 0 => circle5.visible = false
                        case 1 => 
                            house(4).visible = false
                            circle5.visible = true
                        case 2 =>
                            house(5).visible = false
                            circle6.visible = true
                        case 3 =>
                            house(6).visible = false
                            circle7.visible = true
                        case 4 =>
                            house(7).visible = false
                            circle8.visible = true
                    }
        piecesOutMap = controller.game.piecesOutMap
    }
    def checkForPieceChoosing(rolledDice: Int): Unit = {
        if(rolledDice.toInt == 6 && controller.game.piecesOutMap(controller.game.playerturn - 1) == 0)
            movePieceOut()
            move(rolledDice)
        else if(rolledDice.toInt != 6 && (controller.game.piecesOutMap(controller.game.playerturn - 1) != 1 && controller.game.piecesOutMap(controller.game.playerturn - 1) != 0))
            infoLabel.text = infoLabel.text + "     Which Piece should move? After choosing, roll again to confirm"
            if(controller.game.pieceChooser > 0)
                movePiece(rolledDice)
                move(rolledDice)
        else if(rolledDice.toInt == 6 && controller.game.piecesOutMap(controller.game.playerturn - 1) > 0)
            infoLabel.text = infoLabel.text + "    Which Piece should move or get out? After choosing, roll again to confirm"
            if(controller.game.pieceChooser > 0)
                if(controller.game.pieceChooser > controller.game.piecesOutMap(controller.game.playerturn - 1))
                    movePieceOut()
                    move(rolledDice)
                else
                    movePiece(rolledDice)
                    move(rolledDice)
                else oldDice = rolledDice
                //movePiece(rolledDice)
        else if(rolledDice.toInt != 6 && controller.game.piecesOutMap(controller.game.playerturn -1) == 1)
            movePiece(rolledDice)
            move(rolledDice)

        else 
            move(rolledDice)
            //infoLabel.text = "Press the roll Button to roll"
    }
    override def closeOperation() = 
        this.close()
    override def update = println()
    pack()
    centerOnScreen()
    open()
}
