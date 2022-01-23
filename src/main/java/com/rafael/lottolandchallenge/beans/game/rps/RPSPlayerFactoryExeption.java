package com.rafael.lottolandchallenge.beans.game.rps;

public class RPSPlayerFactoryExeption extends Exception {

	private static final long serialVersionUID = 210648928486428824L;

	RPSPlayerFactoryExeption(String msg) {
		super("Unknow player type: " + msg);
	}

}
