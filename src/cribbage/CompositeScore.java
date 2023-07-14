package cribbage;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.List;

public class CompositeScore extends ScoreRule{
    private List<ScoreRule> scorelist = new ArrayList<>();

    @Override
    public int getScore(int player, int playerScore, Hand hand) {
        int finalscore = 0;
        for(ScoreRule rule:scorelist){
            finalscore += rule.getScore(player, playerScore, hand);
            System.out.println(finalscore);
        }
        return finalscore;
    }

    public void addRule(ScoreRule rule)
    {
        scorelist.add(rule);
    }
    public boolean isEmpty(){
        return scorelist.isEmpty();
    }
}
