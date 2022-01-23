package com.rafael.lottolandchallenge.beans.game;

import org.jvnet.hk2.annotations.Contract;

import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryExeption;
import com.rafael.lottolandchallenge.beans.round.Round;
import com.rafael.lottolandchallenge.ws.PlayersListMsg;

@Contract
public interface GameEngine {

	public Round playRound(PlayersListMsg playersMsg) throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption;

}
