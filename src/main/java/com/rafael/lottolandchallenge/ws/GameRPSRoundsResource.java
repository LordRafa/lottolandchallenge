package com.rafael.lottolandchallenge.ws;

import com.rafael.lottolandchallenge.beans.game.GameEngine;
import com.rafael.lottolandchallenge.beans.game.WrongPlayerNumberExeption;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryExeption;
import com.rafael.lottolandchallenge.beans.round.Round;
import com.rafael.lottolandchallenge.beans.state.GameRoundsState;
import com.rafael.lottolandchallenge.beans.state.GameStatsState;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Jersey rest-api for a two player rock paper scissors 
@Path("rps2p/rounds")
public class GameRPSRoundsResource {

	@Inject
	GameRoundsState gameRounds;

	// This is singleton class (see Main.java file)
	@Inject
	GameStatsState gameStats;

	@Inject
	GameEngine gameEngine;

	// Call to create/play a round in the current http session
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response playRound(PlayersListMsg playersMsg) {

		Round round;
		try {
			round = gameEngine.playRound(playersMsg);
		} catch (RPSPlayerFactoryExeption | WrongPlayerNumberExeption e) {
			return Response.status(404).build();
		}

		gameRounds.addRound(round);

		switch (round.getWinner()) {
		case 1: {
			gameStats.incPlayerWins(playersMsg.getPlayers()[0]);
			break;
		}
		case 2: {
			gameStats.incPlayerWins(playersMsg.getPlayers()[1]);
			break;

		}
		default: {
			gameStats.incDraws();
			break;
		}
		}

		return Response.status(200).entity(round).build();

	}

	// Call to delete all rounds in the current http session
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetRounds() {

		gameRounds.resetRounds();

		return Response.status(200).build();

	}

	// Call to get all rounds in the current http session
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRounds() {

		return Response.status(200).entity(gameRounds).build();

	}

}
