package cribbage;

import ch.aplu.jcardgame.Hand;

public abstract class ScoreRule {
    int score;
    abstract int getScore(int player, int playerScore, Hand hand);


}
