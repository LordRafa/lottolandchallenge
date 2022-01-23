package com.rafael.lottolandchallenge.unit.state;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.rafael.lottolandchallenge.beans.state.GameStatsStateImp;

public class GameStatsStateImpTest {

	GameStatsStateImp gameStatsStateImp = new GameStatsStateImp();

	@Test
	public void incPlayerWinsStatsTest() {

		gameStatsStateImp.incPlayerWins("FOO");
		Map<String, Integer> playerWins = gameStatsStateImp.getPlayerWins();
		assertEquals(1, playerWins.size());
		assertEquals(1, playerWins.get("FOO").intValue());
		assertEquals(0, gameStatsStateImp.getTotalDraws());
		assertEquals(1, gameStatsStateImp.getTotalGames());

	}

	@Test
	public void incThreeDifferentPlayerWinsStatsTest() {

		gameStatsStateImp.incPlayerWins("FOO");
		gameStatsStateImp.incPlayerWins("BAR");
		gameStatsStateImp.incPlayerWins("BAR");

		Map<String, Integer> playerWins = gameStatsStateImp.getPlayerWins();
		assertEquals(2, playerWins.size());
		assertEquals(1, playerWins.get("FOO").intValue());
		assertEquals(2, playerWins.get("BAR").intValue());
		assertEquals(0, gameStatsStateImp.getTotalDraws());
		assertEquals(3, gameStatsStateImp.getTotalGames());

	}

	@Test
	public void incDrawsStatsTest() {

		gameStatsStateImp.incDraws();

		Map<String, Integer> playerWins = gameStatsStateImp.getPlayerWins();
		assertEquals(0, playerWins.size());
		assertEquals(1, gameStatsStateImp.getTotalDraws());
		assertEquals(1, gameStatsStateImp.getTotalGames());

	}

	@Test
	public void incThreeDifferentPlayerWinsStatsAndIncDrawStatsTest() {

		gameStatsStateImp.incPlayerWins("FOO");
		gameStatsStateImp.incPlayerWins("BAR");
		gameStatsStateImp.incPlayerWins("BAR");
		gameStatsStateImp.incDraws();

		Map<String, Integer> playerWins = gameStatsStateImp.getPlayerWins();
		assertEquals(2, playerWins.size());
		assertEquals(1, playerWins.get("FOO").intValue());
		assertEquals(2, playerWins.get("BAR").intValue());
		assertEquals(1, gameStatsStateImp.getTotalDraws());
		assertEquals(4, gameStatsStateImp.getTotalGames());

	}

}
