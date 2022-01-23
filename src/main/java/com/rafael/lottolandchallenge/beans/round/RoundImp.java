package com.rafael.lottolandchallenge.beans.round;

import java.util.ArrayList;
import java.util.List;

public class RoundImp implements Round {

	private static final long serialVersionUID = -3427634576859519627L;

	private List<String> playersChoices = new ArrayList<String>();

	private int winner = 0;

	@Override
	public void addPlayerChoice(String player) {
		playersChoices.add(player);
	}

	public void setPlayersChoices(List<String> playersChoices) {
		this.playersChoices = playersChoices;
	}

	@Override
	public List<String> getPlayersChoices() {
		return playersChoices;
	}

	@Override
	public int getWinner() {
		return winner;
	}

	@Override
	public void setWinner(int winner) {
		this.winner = winner;
	}

}
