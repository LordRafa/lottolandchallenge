package com.rafael.lottolandchallenge.beans.state;

import java.util.ArrayList;
import java.util.List;

import org.jvnet.hk2.annotations.Service;

import com.rafael.lottolandchallenge.beans.round.Round;

@Service
public class GameRoundsStateImp implements GameRoundsState {

	private static final long serialVersionUID = -4085653325422583609L;

	private List<Round> rounds = new ArrayList<Round>();
	
	@Override
	public void addRound(Round round) {
		rounds.add(round);
	}

	@Override
	public List<Round> getRounds() {
		return rounds;
	}
	
	@Override
	public int getRoundsNumber() {
		return rounds.size();
	}
	
	@Override
	public void resetRounds() {
		rounds.clear();
	}

}
