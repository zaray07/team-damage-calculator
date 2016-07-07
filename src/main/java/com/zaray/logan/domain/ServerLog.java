package com.zaray.logan.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ServerLog {

	private String serverLogText;
	private List<Player> listOfPlayers;

	public void doTextAnalize() {
		listOfPlayers = new ArrayList<Player>();
		Player youAsPlayer = new Player("You");
		listOfPlayers.add(youAsPlayer);

		BufferedReader bufferedReader = new BufferedReader(new StringReader(serverLogText));
		String currentStringLine;
		try {
			while ((currentStringLine = bufferedReader.readLine()) != null) {

				if (currentStringLine.matches("(.*) hitpoints due to your attack.")) {
					String[] splitLine = currentStringLine.split(" ");
					this.countPlayerDamage(youAsPlayer, this.foundDamageInLine(splitLine));

				} else if (currentStringLine.matches("(.*) hitpoints due to an attack by (.*)")) {
					String[] splitLine = currentStringLine.split(" ");
					this.countPlayerDamage(this.recognizePlayerName(splitLine, listOfPlayers),
							this.foundDamageInLine(splitLine));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Player currentPlayer : listOfPlayers) {
			System.out.println("Name : " + currentPlayer.getPlayerName());
			System.out.println("DPS : " + currentPlayer.getPlayerDamageDone());
		}
	}

	private double foundDamageInLine(String[] splitLine) {
		double currentDamage = 0;
		for (int i = splitLine.length - 1; i > 0; i--)
			if (splitLine[i].equals("hitpoints"))
				currentDamage = Double.parseDouble(splitLine[i - 1]);
		return currentDamage;
	}

	private void countPlayerDamage(Player currentPlayer, double tempDamageFromLine) {
		currentPlayer.setPlayerDamageDone(currentPlayer.getPlayerDamageDone() + tempDamageFromLine);

	}

	private Player recognizePlayerName(String[] splitLine, List<Player> listOfPlayers) {
		int i = splitLine.length - 1;
		String currentPlayerName = "";
		String newPlayerName = "";
		while (!splitLine[i - 1].equals("attack") || !splitLine[i].equals("by")) {
			currentPlayerName = splitLine[i] + " " + currentPlayerName;
			i--;
		}
		newPlayerName = currentPlayerName.substring(0, ((currentPlayerName.length()) - 2));

		for (Player currentPlayer : listOfPlayers) {
			if (currentPlayer.getPlayerName().equals(newPlayerName)) {
				return currentPlayer;
			}
		}

		Player recognizedPlayer = new Player(newPlayerName);
		listOfPlayers.add(recognizedPlayer);
		return recognizedPlayer;
	}

	public String getServerLogText() {
		return serverLogText;
	}

	public void setServerLogText(String serverLog) {
		this.serverLogText = serverLog;
	}

	public List<Player> getListOfPlayers() {
		return listOfPlayers;
	}

	public void setListOfPlayers(List<Player> listOfPlayers) {
		this.listOfPlayers = listOfPlayers;
	}

}
