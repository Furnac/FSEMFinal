package automaton;

public class Automaton1D {
  private int size;
  private String ruleSet;
  private int[] cellSpace;
  
  public Automaton1D () {
    size = 10;
    ruleSet = "00000000";
    cellSpace = new int[size];
  }
  
  public Automaton1D (int size) {
    this.size = size;
    ruleSet = "00000000";
    cellSpace = new int[size];
  }
  
}
