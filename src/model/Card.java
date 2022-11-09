package model;

import java.util.Random;

public class Card {
  private final int number;
  private final CardName name;
  private final Suit suit;

  private final Random rand;

  public enum Suit {Clubs, Spades, Hearts, Diamonds};

  public enum CardName {Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King};

  public Card(int val, Suit suit, Random rand) {
    if (val < 1 || val > 13) {
      throw new IllegalArgumentException("model.Card Value must be between 1-13\n");
    }
    this.rand = rand;
    this.number = val;
    this.suit = suit;
    this.name = CardName.values()[val-1];
  }

  public Card(int val, Suit suit) {
    this(val, suit, new Random());
  }
  public Card(int val) {
    this(val, Suit.values()[new Random().nextInt(Suit.values().length)]);
  }

  public int getNumber() {
    return number;
  }

  public String getRepresentation() {
    switch (this.number) {
      case 13:
        return "K";
      case 12:
        return "Q";
      case 11:
        return "J";
      case 1:
        return "A";
      default:
        return Integer.toString(this.number);
    }
  }

  public CardName getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return this.name.name() + " of " + this.suit.name();
  }
}
