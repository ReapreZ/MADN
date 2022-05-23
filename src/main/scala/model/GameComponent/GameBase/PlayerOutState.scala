/*package model.GameComponent.GameBase

import model.GameComponent.GameBase.Game

import model.GameComponent.GameBase.Event
import model.GameComponent.GameBase.Event.OnEvent
import model.GameComponent.GameBase.Event.OffEvent

object PlayerOutState {
	var state: OnState
	def handle(e: Event) = {
			e match {
					case on:OnEvent => state = onState
					case off:OffEvent => state = offState
		}
		state
	}

	def changeState(newState:state) =
		state = newState


	def onState = print("Eine Figut ist bereits draussen\n")
	def offState = print("Bisher ist noch keine Figur draussen\n")
}*/