package automaton;

import java.util.ArrayList;

public class Automaton {
  private int dimensions;
  private int size;
  private int rule_set;
  private int[] cell_space;
  
  public Automaton (int dimensions, int size, int rule_set) {
    this.dimensions = dimensions;
    this.size = size;
    this.rule_set = wrap(rule_set, 0, (int) Math.pow(2, Math.pow(3, dimensions)));
    cell_space = new int[(int)Math.pow(size, dimensions)];
  }
  
  
  private int wrap (int val, int min, int max) {
    int range = max - min;
    return Math.floorMod(val - min, range) + min;
  }
  
}