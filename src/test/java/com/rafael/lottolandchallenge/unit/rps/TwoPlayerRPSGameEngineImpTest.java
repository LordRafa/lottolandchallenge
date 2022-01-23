package com.rafael.lottolandchallenge.unit.rps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.rafael.lottolandchallenge.beans.game.WrongPlayerNumberExeption;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryExeption;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryImp;
import com.rafael.lottolandchallenge.beans.game.rps.TwoPlayerRPSGameEngineImp;
import com.rafael.lottolandchallenge.beans.round.Round;
import com.rafael.lottolandchallenge.ws.PlayersListMsg;

public class TwoPlayerRPSGameEngineImpTest {

	TwoPlayerRPSGameEngineImp twoPlayerRPSGameEngineImp;

	public TwoPlayerRPSGameEngineImpTest() {
		twoPlayerRPSGameEngineImp = new TwoPlayerRPSGameEngineImp(new RPSPlayerFactoryImp());
	}

	@Test
	public void testP1RockWinsP2Scissors() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheRock", "TheScissors" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("ROCK", choices.get(0));
		assertEquals("SCISSORS", choices.get(1));
		assertEquals(1, round.getWinner());
	}

	@Test
	public void testP1RockLoosesP2Papper() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheRock", "ThePapper" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("ROCK", choices.get(0));
		assertEquals("PAPPER", choices.get(1));
		assertEquals(2, round.getWinner());
	}

	@Test
	public void testP1RockDrawsP2Rock() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheRock", "TheRock" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("ROCK", choices.get(0));
		assertEquals("ROCK", choices.get(1));
		assertEquals(0, round.getWinner());
	}

	@Test
	public void testP1PapperWinsP2Rock() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "ThePapper", "TheRock" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("PAPPER", choices.get(0));
		assertEquals("ROCK", choices.get(1));
		assertEquals(1, round.getWinner());
	}

	@Test
	public void testP1PapperLoosesP2Scissors() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "ThePapper", "TheScissors" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("PAPPER", choices.get(0));
		assertEquals("SCISSORS", choices.get(1));
		assertEquals(2, round.getWinner());
	}

	@Test
	public void testP1PapperDrawsP2Papper() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "ThePapper", "ThePapper" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("PAPPER", choices.get(0));
		assertEquals("PAPPER", choices.get(1));
		assertEquals(0, round.getWinner());
	}

	@Test
	public void testP1ScissorsWinsP2Papper() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheScissors", "ThePapper" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("SCISSORS", choices.get(0));
		assertEquals("PAPPER", choices.get(1));
		assertEquals(1, round.getWinner());
	}

	@Test
	public void testP1ScissorsLoosesP2Rock() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheScissors", "TheRock" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("SCISSORS", choices.get(0));
		assertEquals("ROCK", choices.get(1));
		assertEquals(2, round.getWinner());
	}

	@Test
	public void testP1ScissorsDrawsP2Scissors() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheScissors", "TheScissors" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("SCISSORS", choices.get(0));
		assertEquals("SCISSORS", choices.get(1));
		assertEquals(0, round.getWinner());
	}

	@Test(expected = WrongPlayerNumberExeption.class)
	public void testPlayWithAWrongNumberOfPlayers() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheScissors" };
		twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
	}

	@Test(expected = RPSPlayerFactoryExeption.class)
	public void testPlayWithWrongPlayers() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "FOO", "BAR" };
		twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
	}

}
