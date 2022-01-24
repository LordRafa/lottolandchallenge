package com.rafael.lottolandchallenge;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Session;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

import com.rafael.lottolandchallenge.beans.game.GameEngine;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactory;
import com.rafael.lottolandchallenge.beans.game.rps.RPSPlayerFactoryImp;
import com.rafael.lottolandchallenge.beans.game.rps.TwoPlayerRPSGameEngineImp;
import com.rafael.lottolandchallenge.beans.session.SessionFactory;
import com.rafael.lottolandchallenge.beans.state.GameRoundsState;
import com.rafael.lottolandchallenge.beans.state.GameRoundsStateImp;
import com.rafael.lottolandchallenge.beans.state.GameStatsState;
import com.rafael.lottolandchallenge.beans.state.GameStatsStateImp;
import com.rafael.lottolandchallenge.ws.GameRPSResources;

import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.ContextResolver;

/**
 * Main class.
 *
 */
public class Main {

	// Base URI the Grizzly REST server will listen on
	public static final String BASE_URI = "http://0.0.0.0:8080";
	public static final String BASE_URI_REST = BASE_URI + "/rest";

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and providers
		// in com.rafael.lottolandchallenge package
		final ResourceConfig rc = new ResourceConfig();

		rc.register(createMoxyJsonResolver());
		rc.register(GameRPSResources.class);

		rc.register(new AbstractBinder() {
			@Override
			protected void configure() {

				bindFactory(SessionFactory.class).to(Session.class).proxy(true).proxyForSameScope(false)
						.in(RequestScoped.class);

				// Notice that this class is defined with a singleton life.cycle
				// only one instance of this class will be shared
				bind(RPSPlayerFactoryImp.class).to(RPSPlayerFactory.class).in(Singleton.class);

				// Notice that this class is defined with a singleton life.cycle
				// only one instance of this class will be shared
				bind(TwoPlayerRPSGameEngineImp.class).to(GameEngine.class).in(Singleton.class);

				// The life-cycle of this component will be modified to a per-session cycle. See
				// PerSessionComponentProvider.java
				bind(GameRoundsStateImp.class).to(GameRoundsState.class).in(RequestScoped.class);
				// Notice that this class is defined with a singleton life.cycle
				// only one instance of this class will be shared
				bind(GameStatsStateImp.class).to(GameStatsState.class).in(Singleton.class);

			}
		});

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI_REST
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI_REST), rc);

		// Add static resources to the root
		CLStaticHttpHandler clStaticHttpHandler = new CLStaticHttpHandler(Main.class.getClassLoader(), "/www/");
		server.getServerConfiguration().addHttpHandler(clStaticHttpHandler, "/");

		return server;
	}

	// Add JSON support to Jersey
	public static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
		final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
		Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
		namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
		moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
		return moxyJsonConfig.resolver();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		final HttpServer server = startServer();

		// register shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Stopping server..");
				server.shutdown();
			}
		}, "shutdownHook"));

		System.out.println(String.format("Jersey app started with endpoints available at http://localhost:8080/rest%n"
				+ "the user interface is avaiable at http://localhost:8080/%n" + "Hit Ctrl-C to stop it..."));
		Thread.currentThread().join();

	}

}
