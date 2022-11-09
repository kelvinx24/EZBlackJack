package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player implements Person {
  private final List<Card> hand;

  public Player() {
    this.hand = new ArrayList<>();
  }

  public void addCardToHand(Card c) {
    this.hand.add(c);
  }

  public void addCardToHand(Card[] cards) {
    for (Card c : cards) {
      this.hand.add(c);
    }
  }

  public List<Card> getCards() {
    return this.hand;
  }
}
