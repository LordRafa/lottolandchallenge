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

@Path("rps2p")
public class GameRPSResources {

	@Inject
	GameRoundsState gameRounds;

	@Inject
	GameStatsState gameStats;

	@Inject
	GameEngine gameEngine;

	@POST
	@Path("rounds")
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
	
	@DELETE
	@Path("rounds")
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetRounds() {
		
		gameRounds.resetRounds();

		return Response.status(200).build();

	}

	@GET
	@Path("rounds")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRounds() {

		return Response.status(200).entity(gameRounds).build();

	}

	@GET
	@Path("stats")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStats() {

		return Response.status(200).entity(gameStats).build();

	}

}
