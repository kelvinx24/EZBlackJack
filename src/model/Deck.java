package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Deck {
  private final Map<Integer, List<Card>> cards = new HashMap<Integer, List<Card>>();
  private int cardCount;

  private final Random rand;

  public Deck(Random rand) {
    this.rand = rand;
    this.buildDeck();
    this.cardCount = 52;
  }

  public Deck() {
    this(new Random());
  }

  private void buildDeck() {
    for (int i = 1; i < 14; i++) {
      List<Card> cardSet = new ArrayList<Card>();
      cardSet.add(new Card(i, Card.Suit.Diamonds));
      cardSet.add(new Card(i, Card.Suit.Hearts));
      cardSet.add(new Card(i, Card.Suit.Clubs));
      cardSet.add(new Card(i, Card.Suit.Spades));
      this.cards.put(i, cardSet);
    }
  }

  public int getRemaining() {
    return this.cardCount;
  }

  public Card drawCard() {
    if (cardCount == 0) {
      throw new IllegalStateException("No cards left in deck!\n");
    }

    while (true) {
      int nextCard = rand.nextInt(13) + 1;
      List<Card> cards = this.cards.get(nextCard);
      if (cards.size() > 0) {
        this.cardCount--;
        return cards.get(this.rand.nextInt(cards.size()));
      }
    }
  }

  public Card[] drawCard(int amount) {
    Card[] res = new Card[amount];

    for (int i = 0; i < amount; i++) {
      Card c = this.drawCard();
      res[i] = c;
    }

    return res;
  }

  public void reshuffle() {
    this.cards.clear();
    this.buildDeck();
  }
}
