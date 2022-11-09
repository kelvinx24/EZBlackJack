package model;

public class Better extends Player {
  private final String displayName;
  private int chips;

  public Better(String displayName, int chips) {
    super();
    this.displayName = displayName;
    this.chips = chips;
  }

  public Better(String displayName) {
    this(displayName, 500);
  }

  public String getDisplayName() {
    return displayName;
  }

  public int getChips() {
    return this.chips;
  }

  public void addChips(int amount) {
    this.chips += amount;
  }
}
