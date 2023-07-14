package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// the hand is assumed to have already been sorted, if not, then add the line Collections.sort();
public class SRun extends TheShow{
    public SRun(Hand hand) {
        super(hand);
    }

    //similar implementation with SPair
    public int getScore(int player, int playerScore, Hand hand) {
        score = 0;
        List<Integer> cards = new ArrayList<>();
        for(Card card: hand.getCardList()){
            cards.add(((Cribbage.Rank) card.getRank()).order);
        }
        cards.add(this.starterO);
        Collections.sort(cards);
        int fscore = 0;
        int newly = 1;
        int handsize = cards.size();
        List<Integer> runCards = new ArrayList<>();
        // 1, Here 1, 2, 3, 5, 7
        //1,1,2,2,3
        //multi
        for(int i=1;i<handsize;i++){
            if (cards.get(i-1) + 1 != (cards.get(i)) && cards.get(i-1) != cards.get(i)) {

                fscore += getRunScore(runCards);
                newly= 1;
                runCards.clear();
            }else if (cards.get(i-1) == cards.get(i)){
                if(newly==1) {
                    runCards.add(cards.get(i - 1));
                    runCards.add(cards.get(i));
                    newly=0;
                }else{
                    runCards.add(cards.get(i));
                }
            }
            else{
                if(newly==1) {
                    runCards.add(cards.get(i - 1));
                    runCards.add(cards.get(i));
                    newly=0;
                }else{
                    runCards.add(cards.get(i));
                }
            }
        }
        if(runCards.size()!=0){
            fscore += getRunScore(runCards);
        }
        return fscore;
    }
    public static int getRunScore(List<Integer> cards){
        List<List<Integer>> Comb = new ArrayList<>();
        int index = 0;
        int same = 1;
        int greatsame = 1;
        int unique = 0;
        int cscore=0;
        int newly=1;
        for(int i=1;i<cards.size();i++){
            if(cards.get(i) == cards.get(i-1)){
                same += 1;
                if(i==cards.size()-1){
                    greatsame = greatsame * same;
                }
                if(newly == 1) {
                    Comb.add(new ArrayList<>());
                    Comb.get(index).add(cards.get(i - 1));
                    Comb.get(index).add(cards.get(i));
                    newly = 0;
                }else{
                    Comb.get(index).add(cards.get(i));
                }

            }else if (cards.get(i) == cards.get(i-1)+1){
                unique += 1;
                greatsame = greatsame * same;
                same = 1;
                if(newly == 1){
                    Comb.add(new ArrayList<>());
                    Comb.get(index).add(cards.get(i-1));
                    Comb.add(new ArrayList<>());
                    index += 1;
                    Comb.get(index).add(cards.get(i));
                    newly = 0;
                }else{
                    index += 1;
                    Comb.add(new ArrayList<>());
                    Comb.get(index).add(cards.get(i));
                }
            }
        }
        if(unique >= 2){
            cscore = unique+ 1;
            if(cscore > 5){
                cscore = 5;
            }
        }
        List<String> list = new ArrayList<>();
        List<String> finala = new ArrayList<>();
        if(unique >=2) {
            generatePermutations(Comb, list, 0,"");
            finalise(list,finala);
        }
        for(int i=0;i<finala.size();i++){
            System.out.println(finala.get(i));
        }
        return greatsame * cscore;
    }
    static void generatePermutations(List<List<Integer>> lists, List<String> result, int depth, String current) {
        if (depth == lists.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < lists.get(depth).size(); i++) {
            generatePermutations(lists, result, depth + 1, current + " "+lists.get(depth).get(i));
        }
    }
    static void finalise(List<String> result,List<String> finala){
        int index = 0;
        for(String str:result){
            finala.add(str);
        }
    }
}
