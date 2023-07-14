package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SFlush extends TheShow{
    public SFlush(Hand hand) {
        super(hand);
    }

    @Override
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        int highest=0;
        int num = 0;
        int score = 0;
        //use a hashmap to keep track of how many cards of the same suit
        List<Integer> cards = new ArrayList<>();
        for(Card card: hand.getCardList()){
            cards.add(card.getSuitId());
        }
        cards.add(this.starterS);
        Collections.sort(cards);
        HashMap<Integer, Integer> map = new HashMap<>();
        //populate the hashmap with current cards
        for(Integer card:cards){
            if(map.containsKey(card)){
                map.put(card,map.get(card) + 1);
            }else{
                map.put(card,1);
            }
        }
        //for each suit, pick the suit with the highest number of cards
        for(Integer Suit: map.keySet()){
            num = map.get(Suit);
            if(num > highest){
                highest = num;
            }
        }
        //if the highest number of suit is 4, get 4 points, if it is 5 or larger, get 5, as the max point allowed is for flush5
        if(highest == 4){
            score = 4;
        }else if (highest >= 5){
            score =5;
        }
        System.out.println("flush with "+score);
        return score;
    }
}
