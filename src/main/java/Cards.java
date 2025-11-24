import java.io.Serializable;

public class Cards implements Serializable{
    private final String suit;
    private final int value;
    private String cardImage;

    public Cards(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}