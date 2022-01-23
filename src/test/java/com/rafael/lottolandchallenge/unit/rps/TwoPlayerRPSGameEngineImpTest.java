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
	public void testP1RockLoosesP2Paper() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheRock", "ThePaper" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("ROCK", choices.get(0));
		assertEquals("PAPER", choices.get(1));
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
	public void testP1PaperWinsP2Rock() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "ThePaper", "TheRock" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("PAPER", choices.get(0));
		assertEquals("ROCK", choices.get(1));
		assertEquals(1, round.getWinner());
	}

	@Test
	public void testP1PaperLoosesP2Scissors() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "ThePaper", "TheScissors" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("PAPER", choices.get(0));
		assertEquals("SCISSORS", choices.get(1));
		assertEquals(2, round.getWinner());
	}

	@Test
	public void testP1PaperDrawsP2Paper() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "ThePaper", "ThePaper" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("PAPER", choices.get(0));
		assertEquals("PAPER", choices.get(1));
		assertEquals(0, round.getWinner());
	}

	@Test
	public void testP1ScissorsWinsP2Paper() throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {
		String[] players = { "TheScissors", "ThePaper" };
		Round round = twoPlayerRPSGameEngineImp.playRound(new PlayersListMsg(players));
		List<String> choices = round.getPlayersChoices();
		assertEquals(2, choices.size());
		assertEquals("SCISSORS", choices.get(0));
		assertEquals("PAPER", choices.get(1));
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
