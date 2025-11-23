import java.util.ArrayList;

public class ThreeCardLogic {
    //order of ranks to win
    public static final int StraightFlush =6;
    public static final int ThreeOfAKind = 5;
    public static final int Straight = 4;
    public static final int Flush = 3;
    public static final int aPair = 2;
    public static final int nothingHand = 1;

    public static int evalHand(ArrayList<Cards> hand){
        if(hand.size()<3){
            return nothingHand;
        }

        if(checkStraightFlush(hand)){
            return StraightFlush;
        }
        if(checkThreeOfAKind(hand)){
            return ThreeOfAKind;
        }
        if(checkStraight(hand)){
            return Straight;
        }
        if(checkFlush(hand)){
            return Flush;
        }
        if(checkTwoOfAKind(hand)){
            return aPair;
        }
        return nothingHand;
    }

    public static int evalPPWinnings(ArrayList<Cards> hand, int bet){
        int rankOfHand = evalHand(hand);

        if(rankOfHand == StraightFlush){
            return bet *  40;
        }
        if(rankOfHand == ThreeOfAKind){
            return bet *  30;
        }
        if(rankOfHand == Straight){
            return bet *  6;
        }
        if(rankOfHand == Flush){
            return bet *  3;
        }
        if(rankOfHand == aPair){
            return bet;
        }
        return 0;
    }

    public static int compareHands(ArrayList<Cards> dealer,
                                   ArrayList<Cards> player){
        int dealerRank = evalHand(dealer);
        int playerRank = evalHand(player);

        if(dealerRank > playerRank){
            return -1;
        }
        if(dealerRank < playerRank){
            return 1;
        }
        return CompareHighCards(dealer,player);
    }

    public static int CompareHighCards(ArrayList<Cards> dealer,ArrayList<Cards> player){//TODO
//        sort Arraylist in decending order
//        comp each card until a higher card is found in one of the 2 hands

        return 0;//everything is equal
    }

    public static boolean checkStraightFlush(ArrayList<Cards> hand){
        //checks if hand is both a straight and a flush
        return checkStraight(hand) && checkFlush(hand);
    }

    public static boolean checkThreeOfAKind(ArrayList<Cards> hand){
        //all three cards have same numerical value
        return hand.get(0).getValue() == hand.get(1).getValue() && hand.get(2).getValue() == hand.get(1).getValue();
    }

    public static boolean checkStraight(ArrayList<Cards> hand){//TODO
        //for a straight all cards must have numerically adjacent to each other
        // count for the wrap around where Ace card can be 1 or 14(higher than King)
        //maybe need to sort the array by value
        boolean checkAceLow = (hand.get(0).getValue() == hand.get(1).getValue()-1) &&
                (hand.get(2).getValue()-1 == hand.get(1).getValue());
        boolean checkAceHigh = (hand.get(0).getValue() == 2) &&
                (hand.get(1).getValue() == 3) &&
                (hand.get(2).getValue() == 14);

        return checkAceLow || checkAceHigh;
    }

    public static boolean checkFlush(ArrayList<Cards> hand){
//        all suits the same
        return hand.get(0).getSuit() == hand.get(1).getSuit() &&
                hand.get(2).getSuit() == hand.get(1).getSuit();
    }

    public static boolean checkTwoOfAKind(ArrayList<Cards> hand){
        //two cards have same numerical value
        boolean firstTwo = (hand.get(0).getValue() == hand.get(1).getValue());
        boolean secondTwo = (hand.get(2).getValue() == hand.get(1).getValue());
        boolean firstAndLast = (hand.get(0).getValue() == hand.get(2).getValue());
        return firstTwo || secondTwo || firstAndLast;
    }
}
