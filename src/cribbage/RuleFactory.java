package cribbage;

import ch.aplu.jcardgame.Hand;

public class RuleFactory {
    private static RuleFactory instance;
    private CompositeScore showComp = new CompositeScore();
    private CompositeScore playComp = new CompositeScore();
    private RuleFactory(){
        
    }
    public static RuleFactory getInstance(){
        if(instance == null){
            instance = new RuleFactory();
        }
        return instance;
    }
    public ScoreRule getPlayRule(){
        if(playComp.isEmpty()) {
            playComp.addRule(new PFifAndTO());
            playComp.addRule(new PRun());
            playComp.addRule(new PPair());

        }
        return playComp;
    }
    public ScoreRule getRoundEnd(boolean isend){
        CompositeScore re = new CompositeScore();
        re.addRule(new PRoundEnd(isend));
        return re;
    }
    public ScoreRule getShowRule(Hand hand){
        if(showComp.isEmpty()) {
            showComp.addRule(new SFif(hand));
            showComp.addRule(new SFlush(hand));
            showComp.addRule(new SPair(hand));
            showComp.addRule(new SRun(hand));
            showComp.addRule(new OneFNob(hand));
        }
        return showComp;
    }
    public ScoreRule getCribRule(Hand hand){
        CompositeScore cribComp = new CompositeScore();
        cribComp.addRule(new CJack(hand));
        return cribComp;
    }


}
