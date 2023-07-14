package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SPair extends TheShow{
    public SPair(Hand hand) {
        super(hand);
    }

    @Override
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        List<Integer> cardsO = new ArrayList<>();
        List<Integer> cardsV = new ArrayList<>();
        for(Card card: hand.getCardList()){
            cardsO.add(((Cribbage.Rank) card.getRank()).order);
            cardsV.add(((Cribbage.Rank) card.getRank()).value);
        }
        cardsO.add(this.starterO);
        cardsV.add(this.starterV);
        Collections.sort(cardsO);
        Collections.sort(cardsV);

        int fscore = 0;
        int cscore = 0;
        int paired = 0;
        int handsize = cardsO.size();
        //as the cards are sorted, we can directly iterate through the list and check if two cards are not the same
        for(int i=1;i<handsize;i++){
            if((cardsV.get(i-1) != cardsV.get(i)) ||
                    ( cardsO.get(i-1) !=  cardsO.get(i))){
                paired = 0;
                //if they are not, then there is no paired, and record the current score into the final score
                fscore += cscore;
                cscore = 0;
            }else{
                //if there is a pair, paired + 1, and if paired is >= 1 (if paired is 1, there are 2 cards that are paired) caluclate score
                paired += 1;
                if(paired >= 1){
                    cscore = paired * (paired +1);
                }
            }
        }
        fscore += cscore;
        System.out.println("pair with "+fscore);
        return fscore;
    }
}
