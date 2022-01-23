package com.rafael.lottolandchallenge.beans.state;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;

import org.jvnet.hk2.annotations.Service;

@Service
public class GameStatsStateImp implements GameStatsState, Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Integer> playerWins = new HashMap<String, Integer>(0);
	private int totalDraws;
	private int totalGames;

	private Semaphore mutex = new Semaphore(1);

	@Override
	public void incPlayerWins(String playerName) {
		try {
			mutex.acquire();
			playerWins.merge(playerName, 1, Integer::sum);
			totalGames++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}
	}

	@Override
	public void incDraws() {
		try {
			mutex.acquire();
			totalDraws++;
			totalGames++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}
	}

	@Override
	public Map<String, Integer> getPlayerWins() {
		return playerWins;
	}

	@Override
	public int getTotalDraws() {
		return totalDraws;
	}

	@Override
	public int getTotalGames() {
		return totalGames;
	}

	public void setPlayerWins(ConcurrentMap<String, Integer> playerWins) {
		this.playerWins = playerWins;
	}

	public void setTotalDraws(Integer totalDraws) {
		this.totalDraws = totalDraws;
	}

	public void setTotalGames(Integer totalGames) {
		this.totalGames = totalGames;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {

		try {
			mutex.acquire();
			stream.defaultWriteObject();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}

	}

}
