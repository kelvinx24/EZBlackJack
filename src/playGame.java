import controller.TextController;
import java.io.InputStreamReader;
import model.BlackJack;
import model.EZBlackJack;
import view.BlackJackTextView;
import view.EZBJTextView;

public class playGame {
  public static void main(String[] args) {
    BlackJack model = new EZBlackJack();
    BlackJackTextView view = new EZBJTextView();
    TextController controller = new TextController(new InputStreamReader(System.in), model, view);
    controller.playGame();
  }
}
