package cribbage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ch.aplu.jcardgame.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Logger {
	private static File f;
	private static final String OUTSTREAM = "cribbage.log";
	private Hand[] hands = new Hand[Cribbage.nPlayers];
	// private int cur = 0;
	
	public Logger() {
		try {
			f = new File(OUTSTREAM);
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void log(String output) {
		try {
			FileWriter w = new FileWriter(OUTSTREAM, true);
			w.write(output);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logStart(int SEED, String p1Prop, String p2Prop) {
		log("seed," + SEED + "\n" + p1Prop + ",P0\n" + p2Prop + ",P1\n");
	}
	
	public void logDeal(int player, String cards) {
		log("deal,P" + player + "," + cards + "\n");
	}
	
	public void logDiscard(int player, String cards) {
		log("discard,P" + player + "," + cards +"\n");
	}
	
	public void logStarter(String starter) {
		log("starter," + starter + "\n");
	}
	
	public void logPlay(int currentPlayer, int score, String card) {
		log("play,P" + currentPlayer + "," + score + "," + card + "\n");
	}
	
	public static void logScore(int player, int score, int points, String rule, Hand cards) {

		log("score,P" + player + "," + score + "," + points + "," + rule);
		if (cards != null) {
			String outputHand = canonical(cards);
			log("," + outputHand + "\n");
		} else {
			log("\n");
		}
	}


	
	public void logShow(int player, String starter, String cards) {
		log("show,P" + player + "," + starter + "+" + cards + "\n");
	}

	public void storeHand(int player, Hand hand) {
		hands[player] = new Hand(Cribbage.cribbage.getDeck()); // Clone to sort without changing the original hand
		for (Card C: hand.getCardList()) {
			hands[player].insert(C.getSuit(), C.getRank(), false);
		}
	}

	public void logHand(int player, String starter) {
		log("show,P" + player + "," + starter + "+" + canonical(hands[player]) + "\n");
	}

	/*
	Canonical String representations of Suit, Rank, Card, and Hand
	*/
	static String canonical(Cribbage.Suit s) {
		return s.toString().substring(0, 1);
	}

	static String canonical(Cribbage.Rank r) {
		switch (r) {
			case ACE:case KING:case QUEEN:case JACK:case TEN:
				return r.toString().substring(0, 1);
			default:
				return String.valueOf(r.value);
		}
	}

	static String canonical(Card c) {
		return canonical((Cribbage.Rank) c.getRank()) + canonical((Cribbage.Suit) c.getSuit());
	}

	static String canonical(Hand h) {
		Hand h1 = new Hand(Cribbage.cribbage.getDeck()); // Clone to sort without changing the original hand
		for (Card C: h.getCardList()) {
			h1.insert(C.getSuit(), C.getRank(), false);
		}
		h1.sort(Hand.SortType.POINTPRIORITY, false);
		return "[" + h1.getCardList().stream().map(Logger::canonical).collect(Collectors.joining(",")) + "]";
	}
}
