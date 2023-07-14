package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
public abstract class TheShow extends ScoreRule {
    int starterO;
    int starterV;
    int starterS;
    Hand starter;
    Card starterCard;
    public TheShow(Hand hand){
        this.starterO = ((Cribbage.Rank) hand.getFirst().getRank()).order;
        this.starterV = ((Cribbage.Rank) hand.getFirst().getRank()).value;
        this.starterS = (hand.getFirst().getSuitId());
        this.starter = hand;
        this.starterCard = new Card(Cribbage.cribbage.getDeck(), hand.getFirst().getSuit(), hand.getFirst().getRank());
    }
    public abstract int getScore(int player, int playerScore, Hand hand);
}