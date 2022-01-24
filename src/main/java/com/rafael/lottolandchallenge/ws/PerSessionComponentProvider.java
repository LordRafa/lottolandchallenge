package com.rafael.lottolandchallenge.ws;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.glassfish.grizzly.http.server.Session;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.BindingBuilderFactory;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.server.spi.ComponentProvider;

import jakarta.inject.Inject;

// This class is described on Jersey documentation
// It allows to change jersey's component 
// default per-lockup life-cycle to a per-session one.
//
// This allows to keep a independent persistent
// storage per each session.
//
// The only component affected by this class is GameRPSRoundsResource
@jakarta.ws.rs.ext.Provider
public class PerSessionComponentProvider implements ComponentProvider {

	private ServiceLocator locator;

	static class PerSessionFactory implements Factory<GameRPSRoundsResource> {

		static ConcurrentHashMap<String, GameRPSRoundsResource> perSessionMap = new ConcurrentHashMap<String, GameRPSRoundsResource>();

		private final Session session;
		private final ServiceLocator locator;

		@Inject
		public PerSessionFactory(Session session, ServiceLocator locator) {

			this.session = session;
			this.locator = locator;
		}

		@Override
		@PerLookup
		public GameRPSRoundsResource provide() {

			if (session.isNew()) {
				GameRPSRoundsResource newInstance = createNewGameRPSResources();
				perSessionMap.put(session.getIdInternal(), newInstance);

				return newInstance;
			} else {
				return perSessionMap.get(session.getIdInternal());
			}
		}

		@Override
		public void dispose(GameRPSRoundsResource r) {
		}

		private GameRPSRoundsResource createNewGameRPSResources() {
			final GameRPSRoundsResource GameRPSResources = new GameRPSRoundsResource();
			locator.inject(GameRPSResources);
			return GameRPSResources;
		}
	}

	@Override
	public void initialize(InjectionManager injectionManager) {
		this.locator = injectionManager.getInstance(ServiceLocator.class);
	}

	@Override
	public boolean bind(Class<?> component, Set<Class<?>> providerContracts) {
		if (component == GameRPSRoundsResource.class) {

			final DynamicConfigurationService dynamicConfigService = locator
					.getService(DynamicConfigurationService.class);
			final DynamicConfiguration dynamicConfiguration = dynamicConfigService.createDynamicConfiguration();

			BindingBuilderFactory.addBinding(
					BindingBuilderFactory.newFactoryBinder(PerSessionFactory.class).to(GameRPSRoundsResource.class),
					dynamicConfiguration);

			dynamicConfiguration.commit();

			return true;
		}
		return false;
	}

	@Override
	public void done() {
	}

}
