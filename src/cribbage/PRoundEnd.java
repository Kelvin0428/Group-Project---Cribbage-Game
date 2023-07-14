package cribbage;

import ch.aplu.jcardgame.Hand;

public class PRoundEnd extends ThePlay{
    boolean isend;
    public PRoundEnd(boolean isend){
        this.isend = isend;
    }
    @Override
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        if(isend){
            score = 1;
            Logger.logScore(player, playerScore+score, score, "go", null);
        }else{

        }
        return score;
    }
}
