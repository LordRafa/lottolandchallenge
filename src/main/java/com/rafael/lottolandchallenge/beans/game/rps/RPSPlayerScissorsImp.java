package com.rafael.lottolandchallenge.beans.game.rps;

// This player will choose scissors each time that he plays
public class RPSPlayerScissorsImp implements RPSPlayer {

	private final String playerName = "TheScissors";

	public String getPlayerName() {
		return playerName;
	}

	public RPSHand getHand() {

		return RPSHand.SCISSORS;

	}

}
