import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;


public class MyTest {
    //player hand
    ArrayList<Cards> highCard = new ArrayList<>();
    ArrayList<Cards> pair = new ArrayList<>();
    ArrayList<Cards> flush = new ArrayList<>();
    ArrayList<Cards> straight = new ArrayList<>();
    ArrayList<Cards> threeKind = new ArrayList<>();
    ArrayList<Cards> straightFlush = new ArrayList<>();

    //dealer hand
    ArrayList<Cards> dealerPair = new ArrayList<>();
    ArrayList<Cards> playerPairBetter = new ArrayList<>();
    ArrayList<Cards> playerHighCardWins = new ArrayList<>();
    ArrayList<Cards> dealerHighCardWins = new ArrayList<>();
    ArrayList<Cards> tieHand1 = new ArrayList<>();
    ArrayList<Cards> tieHand2 = new ArrayList<>();


    @BeforeEach
    void setUp() {
        highCard.clear();
        pair.clear();
        flush.clear();
        straight.clear();
        threeKind.clear();
        straightFlush.clear();

        // 1. Nothing Hand
        highCard.add(new Cards("Hearts", 7));
        highCard.add(new Cards("Spades", 14));
        highCard.add(new Cards("Diamonds", 2));

        // 2. Pair
        pair.add(new Cards("Hearts", 2));
        pair.add(new Cards("Spades", 14));
        pair.add(new Cards("Diamonds", 2));

        // 3. Flush
        flush.add(new Cards("Hearts", 7));
        flush.add(new Cards("Hearts", 14));
        flush.add(new Cards("Hearts", 2));

        // 4. Straight
        straight.add(new Cards("Hearts", 7));
        straight.add(new Cards("Spades", 8));
        straight.add(new Cards("Diamonds", 9));

        // 5. Three of a Kind
        threeKind.add(new Cards("Hearts", 7));
        threeKind.add(new Cards("Spades", 7));
        threeKind.add(new Cards("Diamonds", 7));

        // 6. Straight Flush
        straightFlush.add(new Cards("Hearts", 7));
        straightFlush.add(new Cards("Hearts", 8));
        straightFlush.add(new Cards("Hearts", 9));

        // tiebreakers
        //dealer win
        dealerPair.add(new Cards("Clubs", 5));
        dealerPair.add(new Cards("Diamonds", 5));
        dealerPair.add(new Cards("Hearts", 10));

        //player win
        playerPairBetter.add(new Cards("Spades", 5));
        playerPairBetter.add(new Cards("Hearts", 5));
        playerPairBetter.add(new Cards("Clubs", 13));

        // High Card Tie
        dealerHighCardWins.add(new Cards("Hearts", 12));
        dealerHighCardWins.add(new Cards("Spades", 6));
        dealerHighCardWins.add(new Cards("Diamonds", 2));

        // High Card Tie
        playerHighCardWins.add(new Cards("Clubs", 12));
        playerHighCardWins.add(new Cards("Diamonds", 7));
        playerHighCardWins.add(new Cards("Hearts", 2));

        // Perfect Tie
        tieHand1.add(new Cards("Hearts", 7));
        tieHand1.add(new Cards("Spades", 5));
        tieHand1.add(new Cards("Diamonds", 3));
        tieHand2.add(new Cards("Clubs", 7));
        tieHand2.add(new Cards("Diamonds", 5));
        tieHand2.add(new Cards("Hearts", 3));
    }


    //TESTS
    @Test
    void testNothingHand() {
        int result = ThreeCardLogic.evalHand(highCard);
        assertEquals(ThreeCardLogic.nothingHand, result,
                "High Card evaluate to NOTHING HAND");
    }

    @Test
    void testPair() {
        int result = ThreeCardLogic.evalHand(pair);
        assertEquals(ThreeCardLogic.aPair, result,
                "Pair evaluate to Pair");
    }

    @Test
    void testFlush() {
        int result = ThreeCardLogic.evalHand(flush);
        assertEquals(ThreeCardLogic.Flush, result,
                "Flush evaluate to Flush");
    }

    @Test
    void testStraight() {
        int result = ThreeCardLogic.evalHand(straight);
        assertEquals(ThreeCardLogic.Straight, result,
                "Straight evaluate to Straight");
    }

    @Test
    void testThreeOfAKind() {
        int result = ThreeCardLogic.evalHand(threeKind);
        assertEquals(ThreeCardLogic.ThreeOfAKind, result,
                "Three of a Kind evaluate to ThreeOfAKind");
    }

    @Test
    void testStraightFlush() {
        int result = ThreeCardLogic.evalHand(straightFlush);
        assertEquals(ThreeCardLogic.StraightFlush, result,
                "Straight Flush evaluate to StraightFlush");
    }


    @Test
    void testPlayerWinsHigherRank() {
        // Player vs Dealer  - Player wins
        assertEquals(1, ThreeCardLogic.compareHands(flush, straight),
                "Player (Straight) beat Dealer (Flush)");
    }

    @Test
    void testDealerWinsHigherRank() {
        // Dealer vs Player - Dealer wins
        assertEquals(-1, ThreeCardLogic.compareHands(threeKind, pair),
                "Dealer (Three of a Kind) beat Player (Pair)");
    }

    @Test
    void testPairTiePlayerWins() {
        assertEquals(1, ThreeCardLogic.compareHands(dealerPair, playerPairBetter),
                "Player win Pair tie-breaker w King");
    }

    @Test
    void testHighCardTieDealerWins() {
        assertEquals(1, ThreeCardLogic.compareHands(dealerHighCardWins, playerHighCardWins),
                "Player win High Card tie-breaker");

        // test Dealer winning
        assertEquals(-1, ThreeCardLogic.compareHands(playerHighCardWins, dealerHighCardWins),
                "Dealer win High Card tie-breaker");
    }

    @Test
    void testPerfectTie() {
        // Hand 1 - (7, 5, 3) : Hand 2 - (7, 5, 3). Perfect match.
        assertEquals(0, ThreeCardLogic.compareHands(tieHand1, tieHand2),
                "Hands of the same rank and same cards should be a Tie");
    }
}