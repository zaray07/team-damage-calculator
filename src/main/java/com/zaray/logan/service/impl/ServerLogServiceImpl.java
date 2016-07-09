package com.zaray.logan.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaray.logan.service.ServerLogService;
import com.zaray.logan.domain.Player;
import com.zaray.logan.domain.ServerLog;

@Service
public class ServerLogServiceImpl implements ServerLogService {
	

	public void doTextAnalize(ServerLog serverLog) {
		List<Player> listOfPlayers = new ArrayList<Player>();
		Player youAsPlayer = new Player("You");
		listOfPlayers.add(youAsPlayer);
		BufferedReader bufferedReader = new BufferedReader(new StringReader(serverLog.getServerLogText()));
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
		serverLog.setListOfPlayers(listOfPlayers);
	}

	@Override
	public double foundDamageInLine(String[] splitLine) {
		double currentDamage = 0;
		for (int i = splitLine.length - 1; i > 0; i--)
			if (splitLine[i].equals("hitpoints"))
				currentDamage = Double.parseDouble(splitLine[i - 1]);
		return currentDamage;
	}

	@Override
	public void countPlayerDamage(Player currentPlayer, double tempDamageFromLine) {
		currentPlayer.setPlayerDamageDone(currentPlayer.getPlayerDamageDone() + tempDamageFromLine);
	}

	@Override
	public Player recognizePlayerName(String[] splitLine, List<Player> listOfPlayers) {
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

}
