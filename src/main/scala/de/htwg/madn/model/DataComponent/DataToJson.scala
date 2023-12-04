package de.htwg.madn.model.DataComponent

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import scala.swing.Publisher
import de.htwg.madn.model.DataComponent.Changed.PiecesListChanged
import scala.swing.event.Event
import de.htwg.madn.model.DataComponent.Changed.PlayerturnChanged
import de.htwg.madn.model.DataComponent.Changed.TimesPlayerRolledChanged
import de.htwg.madn.model.DataComponent.Changed.PlayeramountChanged
import de.htwg.madn.model.DataComponent.Changed.RolledDiceChanged

class DataToJson extends Data with Publisher {
  
  // Array[Array[Int]] [[0,0][0,1]]
  def getPieceListAsJson: JsValue = Json.toJson(pieceList)
  // int
  def getPlayerTurnAsJson: JsValue = Json.toJson(playerturn)
  // Array[Int] [0,0,0,0]
  def getPiecesOutAsJson: JsValue = Json.toJson(piecesOut)
  // int
  def getTimesPlayerRolledAsJson: JsValue = Json.toJson(timesPlayerRolled)
  // int
  def getPlayerAmountAsJson: JsValue = Json.toJson(playeramount)
  // int
  def getRolledDiceAsJson: JsValue = Json.toJson(rolledDice)

  def toJsonString(value: JsValue): String = { Json.stringify(value) }

  def setPieceListFromJson(json: JsValue): Unit = { 
    pieceList = Json.fromJson[Array[Array[Int]]](json).getOrElse(Array.empty[Array[Int]])
    publish(new PiecesListChanged)
  }

  def setPlayerTurnFromJson(json: JsValue): Unit = { 
    playerturn = Json.fromJson[Int](json).getOrElse(0)
    publish(new PlayerturnChanged)
  }

  def setPiecesOutFromJson(json: JsValue): Unit = { 
    piecesOut = Json.fromJson[Array[Int]](json).getOrElse(Array.empty[Int]) 
  }

  def setTimesPlayerRolledFromJson(json: JsValue): Unit = { 
    timesPlayerRolled = Json.fromJson[Int](json).getOrElse(0) 
    publish(new TimesPlayerRolledChanged)
  }

  def setPlayerAmountFromJson(json: JsValue): Unit = { 
    playeramount = Json.fromJson[Int](json).getOrElse(0) 
    publish(new PlayeramountChanged)
  }

  def setRolledDiceFromJson(json: JsValue): Unit = { 
    rolledDice = Json.fromJson[Int](json).getOrElse(1)
    publish(new RolledDiceChanged)
  }
}
