package com.rafael.lottolandchallenge.beans.game;

public class WrongPlayerNumberExeption extends Exception {

	private static final long serialVersionUID = 7274723167892352602L;

	public WrongPlayerNumberExeption(int expected, int got) {
		super("Wrong player number. Expected:" + expected + "Got:" + got);
	}

}
