package com.rafael.lottolandchallenge.beans.game.rps;

public interface RPSPlayerFactory {

	// Returns a player instance of a given type.
	public RPSPlayer getPlayer(String type) throws RPSPlayerFactoryExeption;

}
