package automaton;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Automaton2D {
  private int size;
  private String rule_set;
  private int[][] cell_space;
  
  public Automaton2D () {
    this.size = 10;
    this.rule_set = genRuleSet();
    cell_space = new int[size][size];
  }
  
  public Automaton2D (int size) {
    this.size = size;
    this.rule_set = genRuleSet();
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
  
  public void clear () {
    cell_space = new int[size][size];
  }
  
  public int[][] step () {
    int[][] cell_space_back = new int[size][size];
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        int neighborhood = getNeighborhood(x, y);
        int place = neighborhood / 4;
        int val = (neighborhood % 4);
        String hex = rule_set.substring(place, place+1);
        String bin = String.format("%4s", Integer.toBinaryString(Integer.parseInt(hex, 16))).replace(' ', '0');
        cell_space_back[x][y] = Integer.parseInt(bin.substring(val, val+1));
      }
    }
    cell_space = cell_space_back;
    return cell_space.clone();
  }
  
  public void set (int val, int x, int y) {
    cell_space[wrap(x,0,size)][wrap(y, 0, size)] = wrap(val, 0, 1);
  }
  
  public void setRuleSet (String new_set) {
    rule_set = new_set;
  }
  
  public int[][] getCellSpace () {
    return cell_space.clone();
  }
  
  public static String genRuleSet () {
    StringBuilder new_rule_set = new StringBuilder ();
    Random rand = new Random();
    char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    for (int a = 0; a < 128; a++) {
      new_rule_set.append(chars[rand.nextInt(16)]);
    }
    return new_rule_set.toString();
  }
  
  @Override
  public String toString () {
    StringBuilder str = new StringBuilder();
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        str.append(cell_space[x][y]);
      }
      str.append("\n");
    }
    return str.toString();
  }
  
  private int getNeighborhood (int x, int y) {
    StringBuilder neighborhood = new StringBuilder();
    for (int xc = x - 1; xc <= x + 1; xc++) {
      for (int yc = y - 1; yc <= y + 1; yc++) {
        int xb = wrap(xc, 0, size);
        int yb = wrap(yc, 0, size);
        neighborhood.append(cell_space[xb][yb]);
      }
    }
    return Integer.parseInt(neighborhood.toString(), 2);
  }
  
  private int wrap (int val, int min, int max) {
    int range = max - min;
    return Math.floorMod(val - min, range) + min;
  }
  
}