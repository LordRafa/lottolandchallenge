package com.rafael.lottolandchallenge.beans.game.rps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// Possible RPS game choices
public enum RPSHand {
	SCISSORS(0), PAPER(1), ROCK(2);

	RPSHand(int weight) {
		this.weight = weight;
	}

	private int weight;

	private static final List<RPSHand> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	// Return a random choice
	public static RPSHand getRnd() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

	public int getWeight() {
		return weight;
	}
}
