package com.rafael.lottolandchallenge.unit.rps;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerScissorsImp;

public class RPSPlayerScissorsImpTest {

	@Test
	public void testScissorsHand() {
		RPSPlayerScissorsImp player = new RPSPlayerScissorsImp();
		assertEquals("SCISSORS", player.getHand().name());
	}

	@Test
	public void testScissorsPlayerName() {
		RPSPlayerScissorsImp player = new RPSPlayerScissorsImp();
		assertEquals("TheScissors", player.getPlayerName());
	}

}
