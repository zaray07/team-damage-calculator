package com.zaray.logan.service;

import java.util.List;

import com.zaray.logan.domain.Player;
import com.zaray.logan.domain.ServerLog;

public interface ServerLogService {	
	
	void doTextAnalize(ServerLog serverLog) ;
	double foundDamageInLine(String[] splitLine);
	void countPlayerDamage(Player currentPlayer, double tempDamageFromLine);
	Player recognizePlayerName(String[] splitLine, List<Player> listOfPlayers);
	
	
}
