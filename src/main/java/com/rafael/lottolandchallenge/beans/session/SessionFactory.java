package com.rafael.lottolandchallenge.beans.session;

import java.util.function.Supplier;

import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Session;

import jakarta.ws.rs.core.Context;

// Supplier class allow inject httpsessions from Grizzly web server
public class SessionFactory implements Supplier<Session> {

	private final Request request;

	public SessionFactory(@Context Request request) {
		this.request = request;
	}

	@Override
	public Session get() {
		return request.getSession();
	}

}
