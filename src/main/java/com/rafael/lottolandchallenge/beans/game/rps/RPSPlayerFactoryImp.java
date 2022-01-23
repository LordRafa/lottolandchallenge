package com.rafael.lottolandchallenge.beans.game.rps;

// Factory used to create players
public class RPSPlayerFactoryImp implements RPSPlayerFactory {

	@Override
	public RPSPlayer getPlayer(String type) throws RPSPlayerFactoryExeption {

		switch (type) {

		case "TheRock":
			return new RPSPlayerRockImp();
		case "ThePaper":
			return new RPSPlayerPaperImp();
		case "TheScissors":
			return new RPSPlayerScissorsImp();
		case "Rnd":
			return new RPSPlayerRndImp();
		default:
			throw new RPSPlayerFactoryExeption(type);
		}

	}

}
