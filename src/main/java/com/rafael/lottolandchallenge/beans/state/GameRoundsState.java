package com.rafael.lottolandchallenge.beans.state;

import java.io.Serializable;
import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.rafael.lottolandchallenge.beans.round.Round;

@Contract
public interface GameRoundsState extends Serializable {

	// Add a round to the storage
	public void addRound(Round round);

	// Get all rounds from the storage
	public List<Round> getRounds();

	// Get the number of stored rounds
	public int getRoundsNumber();

	// Remove all rounds from the storage
	public void resetRounds();

}
