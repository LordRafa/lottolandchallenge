package com.rafael.lottolandchallenge.unit.rps;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerPapperImp;

public class RPSPlayerPapperImpTest {

	@Test
	public void testPapperHand() {
		RPSPlayerPapperImp player = new RPSPlayerPapperImp();
		assertEquals("PAPPER", player.getHand().name());
	}

	@Test
	public void testPapperPlayerName() {
		RPSPlayerPapperImp player = new RPSPlayerPapperImp();
		assertEquals("ThePapper", player.getPlayerName());
	}

}
