package com.rafael.lottolandchallenge.beans.game.rps;

public class RPSPlayerFactoryImp implements RPSPlayerFactory {

	// Returns a player instance of a given type.
	@Override
	public RPSPlayer getPlayer(String type) throws RPSPlayerFactoryExeption {

		switch (type) {

		case "TheRock":
			return new RPSPlayerRockImp();
		case "ThePapper":
			return new RPSPlayerPapperImp();
		case "TheScissors":
			return new RPSPlayerScissorsImp();
		case "Rnd":
			return new RPSPlayerRndImp();

		default:
			throw new RPSPlayerFactoryExeption(type);
		}

	}

}
