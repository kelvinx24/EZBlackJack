package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EZBlackJack implements BlackJack {
  private final Deck deck;

  private final List<Better> betters;
  private final Dealer dealer;
  private final Random rand;
  private final Map<Better, Integer> bettersBet;

  public EZBlackJack(List<Better> betters, Random rand) {
    this.betters = betters;
    this.bettersBet = new HashMap<>();
    this.dealer = new Dealer();
    this.rand = rand;
    this.deck = new Deck(this.rand);
  }

  public EZBlackJack() {
    this(new ArrayList<>(), new Random());
  }

  private int getHandTotal(List<Card> hand) {
    int sum = 0;
    int aceCount = 0;
    for (Card c : hand) {
      int cardNum = c.getNumber();
      if (cardNum == 1) {
        sum += 11;
        aceCount += 1;
      }

      else if (cardNum >= 10) {
        sum += 10;
      }

      else {
        sum += cardNum;
      }
    }

    while (sum > 21 && aceCount != 0) {
      sum -= 10;
      aceCount--;
    }

    return sum;
  }

  @Override
  public void startGame() {
    this.dealer.addCardToHand(deck.drawCard(2));

    for (Better b : betters) {
      b.addCardToHand(deck.drawCard(2));
    }
  }

  @Override
  public int compareToDealer(Better b) {
    int bHand = this.getCardsValue(b);
    int dHand = this.getDealerHandTotal();

    if (bHand > 21 && dHand > 21) {
      return 0;
    }
    else if (bHand > 21) {
      return -1;
    }
    else if (dHand > 21) {
      return 1;
    }
    else {
      return bHand - dHand;
    }
  }

  @Override
  public void addBetter(Better b) {
    this.betters.add(b);
  }

  @Override
  public void addBet(Better b, int amount) {
    if (amount < 5) {
      throw new IllegalArgumentException("Bet amount must be at least 5\n");
    }

    this.bettersBet.put(b, amount);
  }

  @Override
  public void playerAddCard(Better b) {
    b.addCardToHand(deck.drawCard());
  }

  @Override
  public void dealerDrawCard() {
    this.dealer.addCardToHand(deck.drawCard());
  }

  @Override
  public List<Better> getBetters() {
    return this.betters;
  }

  @Override
  public int getCardsValue(Better b) {
    return this.getHandTotal(b.getCards());
  }

  @Override
  public int getBet(Better b) {
    return bettersBet.get(b);
  }

  @Override
  public List<Card> getCardsBetter(Better b) {
    return b.getCards();
  }

  @Override
  public List<Card> getDealerCards() {
    return dealer.getCards();
  }

  @Override
  public int getDealerHandTotal() {
    return this.getHandTotal(getDealerCards());
  }
}
