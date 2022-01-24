package com.rafael.lottolandchallenge.ws;

import com.rafael.lottolandchallenge.beans.state.GameStatsState;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Jersey rest-api for a two player rock paper scissors 
@Path("rps2p/stats")
public class GameRPSStatsResource {

	// This is singleton class (see Main.java file)
	@Inject
	GameStatsState gameStats;

	// Call to get the statistic in this server (singleton)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStats() {

		return Response.status(200).entity(gameStats).build();

	}

}
