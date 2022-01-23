package com.rafael.lottolandchallenge.beans.game.rps;

//This player will choice rock each time that he plays
public class RPSPlayerPapperImp implements RPSPlayer {

	private final String playerName = "ThePapper";

	public String getPlayerName() {
		return playerName;
	}

	public RPSHand getHand() {

		return RPSHand.PAPPER;

	}

}
