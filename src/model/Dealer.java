package model;

import java.util.Objects;

public class Dealer extends Player {
  private  Card showCard;
  private Card hiddenCard;


  public Dealer() {
    super();
    this.showCard = null;
    this.hiddenCard = null;
  }

  public Card getShowCard() {
    if (Objects.isNull(this.showCard)) {
      throw new IllegalStateException("model.Dealer currently has no cards!\n!");
    }

    return this.showCard;
  }

  public Card getHiddenCard() {
    if (Objects.isNull(this.hiddenCard)) {
      throw new IllegalStateException("model.Dealer currently has no cards!\n!");
    }

    return this.hiddenCard;
  }

  // might use override
  public void setShowCard(Card c){
    this.showCard = showCard;
  }

  public void setHiddenCard(Card c) {
    this.hiddenCard = hiddenCard;
  }

  @Override
  public void addCardToHand(Card c) {
    if(this.getCards().size() == 0) {
      this.showCard = c;
    }
    else if (this.getCards().size() == 1) {
      this.hiddenCard = c;
    }

    super.addCardToHand(c);
  }
}
