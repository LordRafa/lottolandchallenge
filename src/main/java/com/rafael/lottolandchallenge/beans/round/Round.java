package com.rafael.lottolandchallenge.beans.round;

import java.io.Serializable;
import java.util.List;

public interface Round extends Serializable {

	// Add a players choice into the bean, the implementation
	// must allow to store an arbitrary number of players
	public void addPlayerChoice(String player);

	// Get an arbitrary list of player choices
	public List<String> getPlayersChoices();

	// Return the round the winner
	public int getWinner();

	// Set the round the winner
	public void setWinner(int winner);

}
