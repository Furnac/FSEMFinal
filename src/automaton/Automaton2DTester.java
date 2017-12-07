package automaton;

import java.util.Scanner;

public class Automaton2DTester {
  public static void main (String[] args) {
    Scanner scan = new Scanner(System.in);
    Automaton2D ca = new Automaton2D(60);
    System.out.println(ca.toString());
    ca.set(1, 10, 10);
    
    String action = "";
    while (!action.equals("STOP")) {
      switch (action) {
        case "clear":
          ca.clear();
          break;
        case "gen":
          ca.setRuleSet(Automaton2D.genRuleSet());
          break;
        case "set":
          int x = scan.nextInt();
          int y = scan.nextInt();
          int val = scan.nextInt();
          ca.set(val, x, y);
          break;
        default:
          break;
      }
      ca.step();
      System.out.println(ca.toString().replace('0', '_').replace('1', '@'));
      action = scan.nextLine();
    }
  }
}
