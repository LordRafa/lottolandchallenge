package com.rafael.lottolandchallenge.beans.game;

import org.jvnet.hk2.annotations.Contract;

import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryExeption;
import com.rafael.lottolandchallenge.beans.round.Round;
import com.rafael.lottolandchallenge.ws.PlayersListMsg;

// Define methods required by a game engine
@Contract
public interface GameEngine {

	// This method takes a list of players and executes a round
	public Round playRound(PlayersListMsg playersMsg) throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption;

}
