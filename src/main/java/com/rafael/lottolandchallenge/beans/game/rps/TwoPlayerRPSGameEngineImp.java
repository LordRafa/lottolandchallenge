package com.rafael.lottolandchallenge.beans.game.rps;

import org.jvnet.hk2.annotations.Service;

import com.rafael.lottolandchallenge.beans.game.GameEngine;
import com.rafael.lottolandchallenge.beans.game.WrongPlayerNumberExeption;
import com.rafael.lottolandchallenge.beans.round.Round;
import com.rafael.lottolandchallenge.beans.round.RoundImp;
import com.rafael.lottolandchallenge.ws.PlayersListMsg;

import jakarta.inject.Inject;

@Service
public class TwoPlayerRPSGameEngineImp implements GameEngine {

	private final RPSPlayerFactory rpsPlayerFactory;

	@Inject
	public TwoPlayerRPSGameEngineImp(RPSPlayerFactory rpsPlayerFactory) {
		this.rpsPlayerFactory = rpsPlayerFactory;
	}

	@Override
	public Round playRound(PlayersListMsg playersMsg) throws RPSPlayerFactoryExeption, WrongPlayerNumberExeption {

		Round round = new RoundImp();

		String[] players = playersMsg.getPlayers();

		if (players.length != 2) {
			throw new WrongPlayerNumberExeption(2, players.length);
		}

		RPSHand player1Hand = rpsPlayerFactory.getPlayer(players[0]).getHand();
		RPSHand player2Hand = rpsPlayerFactory.getPlayer(players[1]).getHand();

		round.addPlayerChoice(player1Hand.name());
		round.addPlayerChoice(player2Hand.name());

		if (player1Hand == player2Hand) {
			round.setWinner(0);
		} else if (((player1Hand.getWeight() + 1) % 3) == player2Hand.getWeight()) {
			round.setWinner(1);
		} else {
			round.setWinner(2);
		}

		return round;
	}

}
