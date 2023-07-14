package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.pow;

public class SFif extends TheShow{
    public SFif(Hand hand) {
        super(hand);
    }

    @Override
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        List<Integer> cards = new ArrayList<>();
        for(Card card: hand.getCardList()){
            cards.add(((Cribbage.Rank) card.getRank()).value);
        }
        cards.add(this.starterV);
        Collections.sort(cards);
        //sort the cards, as the starter card is assumed to be added as the last card in the 'hand'
        int handsize = cards.size();
        int score = 0;
        //use bitmasking
        //iterate 2^n times for all possibility of subsets
        for (int i = 0; i < (int) pow(2, handsize); i++){
            //reset temp
            int temp = 0;
            for (int j = 0; j < handsize; j++){
                //check if the current entry is in the bit, if yes, then add all values in that set
                if ((i & (1 << j)) > 0) {
                    temp += cards.get(j);
                    System.out.println(cards.get(j));
                }
            }
            //if the values in the current set adds up to 15, get two points
            if(temp == 15){
                score += 2;
            }
        }
        System.out.println("15 with "+score);
        return score;
    }
}
