package com.rafael.lottolandchallenge.beans.game.rps;

// This player will return a random choice each time that he plays
public class RPSPlayerRndImp implements RPSPlayer {

	private final String playerName = "Rnd";

	public String getPlayerName() {
		return playerName;
	}

	public RPSHand getHand() {

		return RPSHand.getRnd();

	}

}
