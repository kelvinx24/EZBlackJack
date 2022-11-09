package view;

import java.io.IOException;

public class EZBJTextView implements BlackJackTextView {
  private Appendable out;

  public EZBJTextView(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }

    this.out = out;
  }

  public EZBJTextView() {
    this(System.out);
  }
  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }

  @Override
  public void renderCards(String ...cards) throws IOException {
    StringBuilder builder = new StringBuilder();

    StringBuilder topBuilder = new StringBuilder();
    StringBuilder repBuilder = new StringBuilder();
    StringBuilder line3 = new StringBuilder();
    StringBuilder line4 = new StringBuilder();
    StringBuilder line5 = new StringBuilder();
    StringBuilder line6 = new StringBuilder();
    StringBuilder bottomBuilder = new StringBuilder();


    for (String c : cards) {
      if (c.equals("hidden")) {
        c = " ";
      }
      topBuilder.append("__________ ");

      if (c.equals("10")) {
        repBuilder.append("|").append(c).append("      | ");
      } else {
        repBuilder.append("|").append(c).append("       | ");
      }

      line3.append("|        | ");
      line4.append("|        | ");
      line5.append("|        | ");
      line6.append("|        | ");
      bottomBuilder.append("|________| ");
    }

    builder.append(topBuilder).append("\n")
        .append(repBuilder).append("\n")
        .append(line3).append("\n")
        .append(line4).append("\n")
        .append(line5).append("\n")
        .append(line6).append("\n")
        .append(bottomBuilder).append("\n");
    this.out.append(builder.toString());
  }
}
