package automaton;

import java.util.ArrayList;
import java.util.Random;

public class Automaton2D {
  private int size;
  private String rule_set;
  private int[][] cell_space;
  
  public Automaton2D (int size, String rule_set) {
    this.size = size;
    if (rule_set.length() < 8) {
      for (int a = 0; a < 8-rule_set.length(); a++) {
        rule_set = "0" + rule_set;
      }
    }
    if (rule_set.length() > 8) {
      rule_set = rule_set.substring(rule_set.length() - 8);
    }
    this.rule_set = rule_set;
    cell_space = new int[size][size];
  }
  
  public int[][] init () {
    Random rand = new Random();
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        cell_space[x][y] = rand.nextInt(2);
      }
    }
    return cell_space.clone();
  }
  
  public int[][] step () {
    
    int[][] cell_space_back = cell_space.clone();
    
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        String neighborhood = getNeighborhood(x, y);
        
      }
    }
    return cell_space;
  }
  
  private String getNeighborhood (int x, int y) {
    String neighborhood = "";
    for (int xc = x - 1; xc <= x + 1; xc++) {
      for (int yc = y - 1; yc <= y + 1; yc++) {
        int xb = wrap(xc, 0, size);
        int yb = wrap(yc, 0, size);
        neighborhood += "" + cell_space[xb][yb];
      }
    }
    return neighborhood;
  }
  
  private int wrap (int val, int min, int max) {
    int range = max - min;
    return Math.floorMod(val - min, range) + min;
  }
  
}