package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PPair extends ThePlay{

    @Override
    public int getScore(int player, int playerScore, Hand hand) {

        ArrayList<Card> cards = hand.getCardList();
        int score = 0;
        int handsize = cards.size();
        //from pairs of 2 to pairs of 4
        for(int i=2;i<5;i++){
            //if the size of the card list is lower than the numbe required for pairs, return the score
            if(handsize < i) {
                return score;
            }
            //get the last card (a.k.a the card just played) and the previous played card
            //through each iteration, the compare card gets moved back one card, e.x. second last card to third last card
            Card lastCard = cards.get(cards.size()-1);
            Card compCard = cards.get(cards.size()-i);
            //if the last card and the compare card has different value, return the score
            if(((Cribbage.Rank) lastCard.getRank()).value != ((Cribbage.Rank) compCard.getRank()).value ||
                    ((Cribbage.Rank) lastCard.getRank()).order != ((Cribbage.Rank) compCard.getRank()).order){
                if (score > 0) {
                    Logger.logScore(player, playerScore+score, score, "Pair", null);
                }
                return score;
            }else{
                //else, calculate the current score, (if pairs of 2, the score is  2 * 1, if 3, 3 * 2
                score = i * (i-1);
            }
        }
        Logger.logScore(player, playerScore+score, score, "pair", null);
        return score;
    }
}
