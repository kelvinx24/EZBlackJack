package model;

import java.util.List;

public interface Person {
  void addCardToHand(Card c);

  void addCardToHand(Card[] cards);

  List<Card> getCards();
}
