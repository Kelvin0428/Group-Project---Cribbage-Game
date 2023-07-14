package cribbage;

import ch.aplu.jcardgame.Hand;


public abstract class ThePlay extends ScoreRule{
    public abstract int getScore(int player, int playerScore, Hand hand);
}
