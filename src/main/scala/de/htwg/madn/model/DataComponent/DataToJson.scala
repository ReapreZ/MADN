package de.htwg.madn.model.DataComponent

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

class DataToJson extends Data {
  
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

    def toJsonString(value: JsValue): String = {
    Json.stringify(value)
  }

  def setPieceListFromJson(json: JsValue): Unit = {
    pieceList = Json.fromJson[Array[Array[Int]]](json).getOrElse(Array.empty[Array[Int]])
  }

  def setPlayerTurnFromJson(json: JsValue): Unit = {
    playerturn = Json.fromJson[Int](json).getOrElse(0)
  }

  def setPiecesOutFromJson(json: JsValue): Unit = {
    piecesOut = Json.fromJson[Array[Int]](json).getOrElse(Array.empty[Int])
  }

  def setTimesPlayerRolledFromJson(json: JsValue): Unit = {
    timesPlayerRolled = Json.fromJson[Int](json).getOrElse(0)
  }

  def setPlayerAmountFromJson(json: JsValue): Unit = {
    playeramount = Json.fromJson[Int](json).getOrElse(0)
  }

  def setRolledDiceFromJson(json: JsValue): Unit = {
    rolledDice = Json.fromJson[Int](json).getOrElse(1)
  }

}
