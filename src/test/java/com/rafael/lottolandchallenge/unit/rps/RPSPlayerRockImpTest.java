package com.rafael.lottolandchallenge.unit.rps;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerRockImp;

public class RPSPlayerRockImpTest {

	@Test
	public void testRockHand() {
		RPSPlayerRockImp player = new RPSPlayerRockImp();
		assertEquals("ROCK", player.getHand().name());
	}

	@Test
	public void testRockPlayerName() {
		RPSPlayerRockImp player = new RPSPlayerRockImp();
		assertEquals("TheRock", player.getPlayerName());
	}

}
