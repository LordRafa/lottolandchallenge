package com.rafael.lottolandchallenge.unit.rps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.rafael.lottolandchallenge.beans.game.rps.RPSHand;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerRndImp;

public class RPSPlayerRndImpTest {

	@Test
	public void testRndHand() {
		RPSPlayerRndImp player = new RPSPlayerRndImp();
		assertNotNull(RPSHand.valueOf(player.getHand().name()));
	}

	@Test
	public void testRndPlayerName() {
		RPSPlayerRndImp player = new RPSPlayerRndImp();
		assertEquals("Rnd", player.getPlayerName());
	}

}
