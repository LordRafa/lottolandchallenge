package com.rafael.lottolandchallenge.beans.game.rps;

public interface RPSPlayerFactory {

	public RPSPlayer getPlayer(String type) throws RPSPlayerFactoryExeption;

}
