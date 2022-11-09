package view;

import java.io.IOException;
import java.util.List;

public interface BlackJackTextView {
  void renderMessage(String msg) throws IOException;

  void renderCards(String ...cards) throws IOException;
}
