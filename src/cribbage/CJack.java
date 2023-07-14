package cribbage;

import ch.aplu.jcardgame.Hand;

public class CJack extends TheShow{

    public CJack(Hand hand) {
        super(hand);
    }

    @Override
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        if(this.starterO == 11){
            score = 2;
            Logger.logScore(player, playerScore+score, score, "starter", starter);

        }
        return score;
    }
}