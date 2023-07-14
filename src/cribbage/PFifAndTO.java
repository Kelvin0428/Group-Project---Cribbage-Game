package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class PFifAndTO extends ThePlay{
    @Override
    //the input hand is from one segment
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        int tot = 0;
        //get the card list, do not sort it, as it is for play, we only look at the relationships with the last card
        ArrayList<Card> cards = hand.getCardList();

        //add the value of everysingle card played till now, if it is 15 or 31, give two points
        for(Card card: cards){
            tot += ((Cribbage.Rank) card.getRank()).value;
        }
        if(tot == 15){
            score = 2;
            Logger.logScore(player, playerScore+score, score, "fifteen", null);
        } else if (tot == 31){
            score = 2;
            Logger.logScore(player, playerScore+score, score, "thirtyone", null);
        }

        return score;
    }
}
