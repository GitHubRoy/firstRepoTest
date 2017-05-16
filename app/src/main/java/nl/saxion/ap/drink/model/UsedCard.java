package nl.saxion.ap.drink.model;

/**
 * Created by Roy on 13-3-2017.
 */

public class UsedCard {

    private Card card;
    private Card previousCard;
    private int state;//1=higher, 0=equal, -1=lower.
    boolean right;
    private String name;

    public UsedCard(Card card, Card previousCard, int state, boolean right, String name) {
        this.card = card;
        this.previousCard = previousCard;
        this.state = state;
        this.right = right;
        this.name = name;
    }

    public boolean isRight() {
        return right;
    }

    public Card getCard() {
        return card;
    }

    public Card getPreviousCard() {
        return previousCard;
    }

    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
