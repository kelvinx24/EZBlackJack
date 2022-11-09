package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import model.Better;
import model.BlackJack;
import model.Card;
import view.BlackJackTextView;

public class TextController {

  private final BlackJackTextView textView;
  private final BlackJack model;

  private final Readable in;

  public TextController(Readable in, BlackJack model, BlackJackTextView textView) {
    this.in = in;
    this.model = model;
    this.textView = textView;
  }

  public void playGame() {
    // add all betters to model
      Scanner sc = new Scanner(this.in);
      this.addPlayers(sc);

      for (Better b : model.getBetters()) {
        int bet = 0;
        while (bet <= 0 || bet > b.getChips()) {
          this.writeMessage(
              String.format("%s, how much do you want to bet?\n", b.getDisplayName()));
          bet = sc.nextInt();
          model.addBet(b, bet);
        }
      }

    model.startGame();

    try {
      this.writeMessage("model.Dealer Hand:\n");
      Card shown = model.getDealerCards().get(0);
      textView.renderCards(shown.getRepresentation(), "hidden");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

    this.playerActionPhase(sc);

    this.writeCards(model.getDealerCards());
    this.writeMessage("model.Dealer's turn':\n");
    while (model.getDealerHandTotal() < 16) {
      model.dealerDrawCard();
      this.writeCards(model.getDealerCards());
    }

    // int prev = b.getChips()
    // "model.Better": Balance = prev (+ - ) model.getBetAmount(b) = calculate
    // model.compareToDealer(model.Better) to see if better wins
    for(Better b : model.getBetters()) {
      int prev = b.getChips();
      int bet = model.getBet(b);
      StringBuilder fin = new StringBuilder().append(b.getDisplayName()).append(": ");

      if (model.compareToDealer(b) == 0) {
            fin.append("You tied\n");
      }
      else if (model.compareToDealer(b) < 1) {
        b.addChips(-bet);
        fin.append("You lost\n");
      }
      else {
        b.addChips(bet);
        fin.append("You won\n");
      }

      fin.append("Previous Balance: ").append(prev).append(" ")
          .append("Bet Amount: ").append(bet).append(" ")
          .append("New Balance: ").append(b.getChips()).append("\n");

      this.writeMessage(fin.toString());
    }
  }


  private void writeMessage(String message) throws IllegalStateException {
    try {
      textView.renderMessage(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void writeCards(List<Card> hand) throws IllegalStateException {
    try {
      List<String> rCards = new ArrayList<>();
      for (Card c : hand) {
        rCards.add(c.getRepresentation());
      }

      textView.renderCards(rCards.toArray(new String[0]));
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void addPlayers(Scanner sc) {
    this.writeMessage("Who is playing?\n");
    String[] playerNames = sc.nextLine().split(" ");
    for (String p : playerNames) {
      model.addBetter(new Better(p));
    }
  }

  private void playerActionPhase(Scanner sc) {
    sc.nextLine();
    for (Better b : model.getBetters()) {
      String command = null;
      int betterCardsTotal = model.getCardsValue(b);
      this.writeMessage(b.getDisplayName() + "'s Hand:\n");
      this.writeCards(b.getCards());

      while(Objects.isNull(command) || betterCardsTotal < 21) {
        this.writeMessage(
            String.format("%s, do you want to call, double down, or stay?\n", b.getDisplayName()));
        command = sc.nextLine();
        if (command.equals("call")) {
          model.playerAddCard(b);
          betterCardsTotal = model.getCardsValue(b);
          this.writeMessage(b.getDisplayName() + "'s Hand:\n");
          this.writeCards(b.getCards());
        }

        else if (command.equals("double down")) {
          int doubleDown = model.getBet(b) * 2;
          if (doubleDown <= b.getChips()) {
            model.playerAddCard(b);
            model.addBet(b, doubleDown);
            betterCardsTotal = model.getCardsValue(b);
            this.writeMessage(b.getDisplayName() + "'s Hand:\n");
            this.writeCards(b.getCards());
          }
          else {
            this.writeMessage(String.format("Not enough chips!\n"));
          }
        }

        else if (command.equals("stay")) {
          break;
        }

        else {
          this.writeMessage("Invalid command!\n");
        }
      }
    }
  }
}
