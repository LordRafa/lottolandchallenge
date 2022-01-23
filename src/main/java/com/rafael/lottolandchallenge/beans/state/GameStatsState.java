package com.rafael.lottolandchallenge.beans.state;

import java.util.Map;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface GameStatsState {

	// Increases the number of wins for playerName in the storage
	public void incPlayerWins(String playerName);

	// Increases the number of draws in the storage
	public void incDraws();

	// Returns the number of wins for all players from the storage
	public Map<String, Integer> getPlayerWins();

	// Return the number of draws from the storage
	public int getTotalDraws();

	// Return the number played fames from the storage
	public int getTotalGames();

}
