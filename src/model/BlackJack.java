package model;

import java.util.List;

public interface BlackJack {
  void startGame();

  int compareToDealer(Better b);

  void addBetter(Better b);

  void addBet(Better b, int amount);

  void playerAddCard(Better b);

  void dealerDrawCard();

  List<Better> getBetters();

  int getCardsValue(Better b);

  int getBet(Better b);

  List<Card> getCardsBetter(Better b);

  List<Card> getDealerCards();

  int getDealerHandTotal();
}
