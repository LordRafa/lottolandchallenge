package com.rafael.lottolandchallenge.beans.state;

import java.io.Serializable;
import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.rafael.lottolandchallenge.beans.round.Round;

@Contract
public interface GameRoundsState extends Serializable {

	public void addRound(Round round);

	public List<Round> getRounds();

	public int getRoundsNumber();

	public void resetRounds();

}
