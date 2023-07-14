package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PRun extends ThePlay{

    @Override
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        ArrayList<Card> cards = new ArrayList<>();
        for(Card card: hand.getCardList()){
            cards.add(card);
        }
        int score = 0;
        int handsize = cards.size();
        //runs from 3 to 7
        for(int i=3;i<8;i++){
            //if hand size is less than required for runs, return the score obtianed
            if(handsize < i){
                return score;
            }
            //get the last i cards, sort them, and see if they are consecutive, if yes, calculate score
            List<Integer> subcards = new ArrayList<>();
            for(Card card: cards.subList(handsize-i,handsize)){
                subcards.add(((Cribbage.Rank) card.getRank()).order);
            }
            Collections.sort(subcards);
            if(isConsecutive(subcards)){
                score = i;
            }
        }
        Logger.logScore(player, playerScore+score, score, "run", null);
        return score;
    }
    private boolean isConsecutive(List<Integer> cards){
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i-1) + 1 !=cards.get(i)) {
                return false;
            }
        }
        return true;
    }

}


