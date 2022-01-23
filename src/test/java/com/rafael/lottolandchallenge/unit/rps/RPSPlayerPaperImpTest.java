package com.rafael.lottolandchallenge.unit.rps;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerPaperImp;

public class RPSPlayerPaperImpTest {

	@Test
	public void testPaperHand() {
		RPSPlayerPaperImp player = new RPSPlayerPaperImp();
		assertEquals("PAPER", player.getHand().name());
	}

	@Test
	public void testPaperPlayerName() {
		RPSPlayerPaperImp player = new RPSPlayerPaperImp();
		assertEquals("ThePaper", player.getPlayerName());
	}

}
