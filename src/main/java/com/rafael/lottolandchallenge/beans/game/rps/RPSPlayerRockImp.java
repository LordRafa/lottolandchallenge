package com.rafael.lottolandchallenge.beans.game.rps;

// This player will choose rock each time that he plays
public class RPSPlayerRockImp implements RPSPlayer {

	private final String playerName = "TheRock";

	public String getPlayerName() {
		return playerName;
	}

	public RPSHand getHand() {

		return RPSHand.ROCK;

	}

}
