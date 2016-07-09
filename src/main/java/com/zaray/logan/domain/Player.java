package com.zaray.logan.domain;

public class Player {

	private String playerName;
	private double playerDamageDone;

	public Player(String name) {
		this.playerName = name;
		this.playerDamageDone = 0;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public double getPlayerDamageDone() {
		return playerDamageDone;
	}

	public void setPlayerDamageDone(double playerDamageDone) {
		this.playerDamageDone = playerDamageDone;
	}

}
