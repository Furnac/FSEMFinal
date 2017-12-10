package automaton;

import java.util.Random;

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
  
  public int[] init () {
    Random rand = new Random();
    for (int a = 0; a < size; a++) {
      cellSpace[a] = rand.nextInt(2);
    }
    return cellSpace.clone();
  }
  
  public void clear () {
    for (int x = 0; x < size; x++) {
      cellSpace[x] = 0;
    }
  }
  
  public void step () {
    int[] cellSpaceBack = cellSpace.clone();
    for (int a = 0; a < size; a++) {
      int n = getNeighborhood(a);
      cellSpaceBack[a] = Integer.parseInt("" + ruleSet.charAt(n));
    }
    cellSpace = cellSpaceBack.clone();
  }
  
  private int getNeighborhood (int x) {
    String bin = "" + cellSpace[wrap(x - 1, 0, size)] + cellSpace[x] + cellSpace[wrap(x + 1, 0, size)];
    return Integer.parseInt(bin, 2);
  }
  
  public void setWidth (int width) {
    size = width;
    cellSpace = new int[size];
  }
  
  public int getSize () {
    return size;
  }
  
  public void set (int val, int x) {
    cellSpace[wrap(x, 0, size)] = val;
  }
  
  public void setRule (int rule, int state) {
    String first = ruleSet.substring(0, Math.max(rule-1, 0));
    String last = ruleSet.substring(rule);
    state = wrap(state, 0, 2);
    ruleSet = first + state + last;
  }
  
  public void setRuleSet (String newSet) {
    ruleSet = newSet;
  }
  
  public void setRuleSet (int newSet) {
    String bin = Integer.toBinaryString(newSet);
    String s = ("00000000" + bin).substring(bin.length());
    ruleSet = new StringBuilder(s).reverse().toString();
  }
  
  public void genRuleSet () {
    StringBuilder builder = new StringBuilder();
    Random rand = new Random();
    for (int a = 0; a < 8; a++) {
      builder.append(rand.nextInt(2));
    }
    ruleSet = builder.toString();
  }
  
  private static int wrap (int val, int min, int max) {
    int range = max - min;
    return Math.floorMod(val - min, range) + min;
  }
  
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int a = 0; a < size ; a++) {
      s.append(cellSpace[a]);
    }
    return s.toString();
  }
  
  public int[] getCellSpace() {
    return cellSpace.clone();
  }
  
}
