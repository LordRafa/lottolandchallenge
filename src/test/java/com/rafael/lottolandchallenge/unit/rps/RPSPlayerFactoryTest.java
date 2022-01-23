package com.rafael.lottolandchallenge.unit.rps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactory;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryExeption;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryImp;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerPaperImp;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerRndImp;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerRockImp;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerScissorsImp;

public class RPSPlayerFactoryTest {

	@Test
	public void testCreateRnd() throws RPSPlayerFactoryExeption {
		RPSPlayerFactory rpsPlayerFactory = new RPSPlayerFactoryImp();
		assertTrue(rpsPlayerFactory.getPlayer("Rnd") instanceof RPSPlayerRndImp);
	}

	@Test
	public void testCreateRock() throws RPSPlayerFactoryExeption {
		RPSPlayerFactory rpsPlayerFactory = new RPSPlayerFactoryImp();
		assertTrue(rpsPlayerFactory.getPlayer("TheRock") instanceof RPSPlayerRockImp);
	}

	@Test
	public void testCreatePaper() throws RPSPlayerFactoryExeption {
		RPSPlayerFactory rpsPlayerFactory = new RPSPlayerFactoryImp();
		assertTrue(rpsPlayerFactory.getPlayer("ThePaper") instanceof RPSPlayerPaperImp);
	}

	@Test
	public void testCreateScissors() throws RPSPlayerFactoryExeption {
		RPSPlayerFactory rpsPlayerFactory = new RPSPlayerFactoryImp();
		assertTrue(rpsPlayerFactory.getPlayer("TheScissors") instanceof RPSPlayerScissorsImp);
	}

	@Test(expected = RPSPlayerFactoryExeption.class)
	public void testCreateUnknowPlayer() throws RPSPlayerFactoryExeption {
		RPSPlayerFactory rpsPlayerFactory = new RPSPlayerFactoryImp();
		rpsPlayerFactory.getPlayer("XXXX");
	}

}
