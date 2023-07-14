package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class OneFNob extends TheShow{
    public OneFNob(Hand hand) {
        super(hand);
    }

    // we assume that we've added the starter card to the end of the hand when calculating score
    @Override
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        ArrayList<Card> cards = hand.getCardList();
        //get the Starter card from the card list
        //for each card in the hand
        for(Card card:cards){
            //if the card is a Jack
            if(((Cribbage.Rank) card.getRank()).order == 11){
                //and the suit is the same as the starter card, get 1 point
                if((card.getSuitId() == this.starterS) && ((Cribbage.Rank) card.getRank()).order != this.starterO){
                    return 1;
                }
            }
        }
        System.out.println("NONOB");
        return 0;
    }
}
