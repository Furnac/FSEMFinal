package automaton;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Automaton2D {
  public static boolean VON_NEUMANN = false;
  public static boolean MOORE = true;
  private boolean neighborhood;
  private int size;
  private String ruleSet;
  private int[][] cellSpace;
  
  public Automaton2D () {
    neighborhood = VON_NEUMANN;
    size = 10;
    ruleSet = genRuleSet();
    cellSpace = new int[size][size];
  }
  
  public Automaton2D (boolean neighborhood, int size) {
    this.neighborhood = neighborhood;
    this.size = size;
    this.ruleSet = genRuleSet();
    cellSpace = new int[size][size];
  }
  
  public int[][] init () {
    Random rand = new Random();
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        cellSpace[x][y] = rand.nextInt(2);
      }
    }
    return cellSpace.clone();
  }
  
  public void clear () {
    cellSpace = new int[size][size];
  }
  
  public int[][] step () {
    int[][] cellSpaceBack = new int[size][size];
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        int n = getNeighborhood(x, y);
        cellSpaceBack[x][y] = Integer.parseInt("" + ruleSet.charAt(wrap(n-1, 0, (neighborhood ? 512: 32))));
      }
    }
    cellSpace = cellSpaceBack;
    return cellSpace.clone();
  }
  
  public void set (int val, int x, int y) {
    cellSpace[wrap(x,0,size)][wrap(y, 0, size)] = wrap(val, 0, 2);
  }
  
  public void setRule (int rule, int state) {
    String first = ruleSet.substring(0, Math.max(rule-1, 0));
    String last = ruleSet.substring(rule);
    state = wrap(state, 0, 2);
    ruleSet = first + state + last;
  }
  
  public void setRules (int currentState, int surrounding, String operation, int nextState) {
    for (int a = 0; a < (neighborhood ? 512 : 32); a++) {
      String b = Integer.toBinaryString(a);
      String bin;
      if (neighborhood) {
        bin = ("000000000" + b).substring(b.length());
      }else {
        bin = ("00000" + b).substring(b.length());
      }
      int bl = bin.length()/2;
      String fs = bin.substring(0, bl);
      String ls = bin.substring(bl + 1);
      String s = bin.substring(bl, bl + 1);
      if (Integer.parseInt(s) == currentState) {
        int sum = Integer.bitCount(Integer.parseInt(fs + ls, 2));
        switch (operation) {
          case "<":
            if (sum < surrounding) setRule(a, nextState);
            break;
          case ">":
            if (sum > surrounding) setRule(a, nextState);
            break;
          default:
            if (sum == surrounding) setRule(a, nextState);
            break;
        }
      }
    }
  }
  
  public void setRuleSet (String newSet) {
    ruleSet = newSet;
  }
  
  public int[][] getCellSpace () {
    return cellSpace.clone();
  }
  
  public String genRuleSet () {
    StringBuilder ruleSetNew = new StringBuilder ();
    Random rand = new Random();
    int length = neighborhood ? 512 : 32;
    for (int a = 0; a < length; a++) {
      ruleSetNew.append(rand.nextInt(2));
    }
    ruleSet = ruleSetNew.toString();
    return ruleSetNew.toString();
  }
  
  public String genRuleSet (int state) {
    StringBuilder ruleSetNew = new StringBuilder ();
    int length = neighborhood ? 512 : 32;
    for (int a = 0; a < length; a++) {
      ruleSetNew.append(wrap(state, 0, 2));
    }
    ruleSet = ruleSetNew.toString();
    return ruleSetNew.toString();
  }
  
  @Override
  public String toString () {
    StringBuilder str = new StringBuilder();
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        str.append(cellSpace[x][y]);
      }
      str.append("\n");
    }
    return str.toString();
  }
  
  private int getNeighborhood (int x, int y) {
    StringBuilder n = new StringBuilder();
    if (neighborhood == VON_NEUMANN) { //VON NEUMANN NEIGHBORHOOD
      n.append(cellSpace[x][wrap(y-1, 0, size)]);
      for (int xc = x - 1; xc <= x + 1; xc++) {
        int xb = wrap(xc, 0, size);
        n.append(cellSpace[xb][y]);
      }
      n.append(cellSpace[x][wrap(y+1, 0, size)]);
    } else { //MOORE NEIGHBORHOOD
      for (int yc = y - 1; yc <= y + 1; yc++) {
        for (int xc = x - 1; xc <= x + 1; xc++) {
          int xb = wrap(xc, 0, size);
          int yb = wrap(yc, 0, size);
          n.append(cellSpace[xb][yb]);
        }
      }
    }
    return Integer.parseInt(n.toString(), 2);
  }
  
  private static int wrap (int val, int min, int max) {
    int range = max - min;
    return Math.floorMod(val - min, range) + min;
  }
  
}