package com.rafael.lottolandchallenge.beans.state;

import java.util.Map;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface GameStatsState {

	public void incPlayerWins(String playerName);

	public void incDraws();

	public Map<String, Integer> getPlayerWins();

	public int getTotalDraws();

	public int getTotalGames();

}
