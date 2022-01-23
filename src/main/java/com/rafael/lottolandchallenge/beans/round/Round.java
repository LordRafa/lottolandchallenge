package com.rafael.lottolandchallenge.beans.round;

import java.io.Serializable;
import java.util.List;

public interface Round extends Serializable {

	public void addPlayerChoice(String player);

	public List<String> getPlayersChoices();

	public int getWinner();

	public void setWinner(int winner);

}
