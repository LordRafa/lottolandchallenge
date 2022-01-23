package com.rafael.lottolandchallenge.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.glassfish.grizzly.http.server.Session;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import com.rafael.lottolandchallenge.beans.game.GameEngine;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactory;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryImp;
import com.rafael.lottolandchallenge.beans.game.rps.TwoPlayerRPSGameEngineImp;
import com.rafael.lottolandchallenge.beans.round.Round;
import com.rafael.lottolandchallenge.beans.round.RoundImp;
import com.rafael.lottolandchallenge.beans.session.SessionFactory;
import com.rafael.lottolandchallenge.beans.state.GameRoundsState;
import com.rafael.lottolandchallenge.beans.state.GameRoundsStateImp;
import com.rafael.lottolandchallenge.beans.state.GameStatsState;
import com.rafael.lottolandchallenge.beans.state.GameStatsStateImp;
import com.rafael.lottolandchallenge.ws.GameRPSResources;
import com.rafael.lottolandchallenge.ws.PlayersListMsg;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.inject.Singleton;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ContextResolver;

public class StepDefinitions {

	private PlayersListMsg playerMsg;

	private JerseyTestWrapper jerseyWrapper = new JerseyTestWrapper();

	private static final Map<String, String> targetsMapping;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("play a Round", "rps2p/rounds");
		aMap.put("get the rounds", "rps2p/rounds");
		aMap.put("reset the rounds", "rps2p/rounds");
		aMap.put("get the stats", "rps2p/stats");
		targetsMapping = Collections.unmodifiableMap(aMap);
	}

	private Map<String, Response> responses = new HashMap<String, Response>();

	private Cookie jsessionid;

	private class JerseyTestWrapper extends JerseyTest {

		@Override
		protected Application configure() {

			ResourceConfig rc = new ResourceConfig();

			rc.register(createMoxyJsonResolver());
			rc.register(GameRPSResources.class);

			rc.register(new AbstractBinder() {
				@Override
				protected void configure() {

					bindFactory(SessionFactory.class).to(Session.class).proxy(true).proxyForSameScope(false)
							.in(RequestScoped.class);

					bind(RPSPlayerFactoryImp.class).to(RPSPlayerFactory.class).in(Singleton.class);

					bind(TwoPlayerRPSGameEngineImp.class).to(GameEngine.class).in(Singleton.class);

					bind(GameRoundsStateImp.class).to(GameRoundsState.class).in(RequestScoped.class);
					bind(GameStatsStateImp.class).to(GameStatsState.class).in(Singleton.class);

				}
			});

			return rc;
		}

		// Add JSON support to Jersey
		public ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
			final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
			Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
			namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
			moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
			return moxyJsonConfig.resolver();
		}

	}

	@Before
	public void initialization() throws Exception {
		jerseyWrapper.setUp();
	}

	@After
	public void after() throws Exception {
		jerseyWrapper.tearDown();
	}

	@Given("I have two players: {string} and {string}")
	public void i_have_two_players(String player1, String player2) {
		String players[] = { player1, player2 };
		playerMsg = new PlayersListMsg(players);
	}

	@Given("I have only one players")
	public void i_have_one_players() {
		String players[] = { "TheRock" };
		playerMsg = new PlayersListMsg(players);
	}

	@When("I send a {string} to {string}")
	public void i_send_a_request_to(String method, String service) {

		String target = targetsMapping.get(service);
		if (target == null) {
			fail("Not yet implemented");
		}

		if ("POST".equals(method)) {
			if (!"rps2p/rounds".equals(target)) {
				fail("Not yet implemented");
			}
			Response response = jerseyWrapper.target(target).request().post(Entity.json(playerMsg));
			jsessionid = response.getCookies().get("JSESSIONID");
			responses.put(method + target, response);
		} else if ("GET".equals(method)) {
			if (!"rps2p/rounds".equals(target) && !"rps2p/stats".equals(target)) {
				fail("Not yet implemented");
			}

			Invocation.Builder nextRequestBuilder = jerseyWrapper.target(target).request();
			if (jsessionid != null) {
				nextRequestBuilder.cookie(jsessionid);
			}

			Response response = nextRequestBuilder.get();
			responses.put(method + target, response);
		} else if ("DELETE".equals(method)) {
			if (!"rps2p/rounds".equals(target)) {
				fail("Not yet implemented");
			}

			Invocation.Builder nextRequestBuilder = jerseyWrapper.target(target).request();
			if (jsessionid != null) {
				nextRequestBuilder.cookie(jsessionid);
			}

			Response response = nextRequestBuilder.delete();
			responses.put(method + target, response);
		} else {
			fail("Not yet implemented");
		}

	}

	@Then("I get a status code {int} for each reponse from GameRPSResources service")
	public void then_I_get_a_status_code_from_GameRPSResources(int code) {
		for (Response res : responses.values()) {
			assertEquals(code, res.getStatus());
		}
	}

	@Then("I get valid round with the {string}, the {string} and the {string}")
	public void I_get_valid_round(String handp1, String handp2, String winner) {

		RoundImp round = responses.get("POSTrps2p/rounds").readEntity(RoundImp.class);
		List<String> choices = round.getPlayersChoices();

		assertEquals(2, choices.size());
		assertTrue((round.getWinner() >= 0) && (round.getWinner() <= 2));

		if (!"SKIP".equals(handp1)) {
			assertEquals(handp1, choices.get(0));
		}
		if (!"SKIP".equals(handp2)) {
			assertEquals(handp2, choices.get(1));
		}
		if (!"SKIP".equals(winner)) {
			assertEquals(winner, Integer.toString(round.getWinner()));
		}

	}

	@Then("I get a list of rounds with one round with data: {string}, the {string} and the {string}")
	public void I_get_list_of_rounds_with(String handp1, String handp2, String winner) {

		Response res = responses.get("GETrps2p/rounds");
		GameRoundsStateMock roundsList = res.readEntity(GameRoundsStateMock.class);

		assertEquals(1, roundsList.getRoundsNumber());
		Round round = roundsList.getRounds().get(0);

		List<String> choices = round.getPlayersChoices();

		assertEquals(2, choices.size());
		assertTrue((round.getWinner() >= 0) && (round.getWinner() <= 2));

		if (!"SKIP".equals(handp1)) {
			assertEquals(handp1, choices.get(0));
		}
		if (!"SKIP".equals(handp2)) {
			assertEquals(handp2, choices.get(1));
		}
		if (!"SKIP".equals(winner)) {
			assertEquals(winner, Integer.toString(round.getWinner()));
		}
	}

	@Then("I get a list of rounds with no data")
	public void I_get_list_of_rounds_with_no_data() {
		Response res = responses.get("DELETErps2p/rounds");
		GameRoundsStateMock roundsList = res.readEntity(GameRoundsStateMock.class);
		assertNull(roundsList);
	}

	@Then("I get valid stats for {string}")
	public void I_get_valid_stats(String winnerName) {

		Response res = responses.get("GETrps2p/stats");
		GameStatsStateImp stats = res.readEntity(GameStatsStateImp.class);

		Map<String, Integer> playerWins = stats.getPlayerWins();
		int roundsCounted = 0;

		if (!"SKIP".equals(winnerName)) {

			assertEquals(1, playerWins.size());
			assertTrue(playerWins.containsKey(winnerName));
		}

		for (int wins : playerWins.values()) {
			roundsCounted += wins;
		}

		roundsCounted += stats.getTotalDraws();
		assertEquals(stats.getTotalGames(), roundsCounted);

	}

}
