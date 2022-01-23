package com.rafael.lottolandchallenge.beans.game.rps;

// This player will choose paper each time that he plays
public class RPSPlayerPaperImp implements RPSPlayer {

	private final String playerName = "ThePaper";

	public String getPlayerName() {
		return playerName;
	}

	public RPSHand getHand() {

		return RPSHand.PAPER;

	}

}
