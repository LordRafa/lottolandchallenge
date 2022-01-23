package com.rafael.lottolandchallenge.integration;

import java.util.ArrayList;
import java.util.List;

import com.rafael.lottolandchallenge.beans.round.RoundImp;

public class GameRoundsStateMock {

	private List<RoundImp> rounds = new ArrayList<RoundImp>();

	private int roundsNumber;

	public List<RoundImp> getRounds() {
		return rounds;
	}

	public void setRounds(List<RoundImp> rounds) {
		this.rounds = rounds;
	}

	public int getRoundsNumber() {
		return roundsNumber;
	}

	public void setRoundsNumber(int roundsNumber) {
		this.roundsNumber = roundsNumber;
	}

}
