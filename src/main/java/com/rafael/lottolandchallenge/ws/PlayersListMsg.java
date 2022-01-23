package com.rafael.lottolandchallenge.ws;

import jakarta.xml.bind.annotation.XmlRootElement;

// Helper class to deserialize playRound post parameters 
@XmlRootElement
public class PlayersListMsg {

	private String players[];

	public PlayersListMsg() {
	}

	public PlayersListMsg(String players[]) {
		this.players = players;

	}

	public String[] getPlayers() {
		return players;
	}

	public void setPlayers(String[] players) {
		this.players = players;
	}

}
