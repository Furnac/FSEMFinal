package automaton;

import java.util.Scanner;

public class Automaton2DTester {
  public static void main (String[] args) {
    Scanner scan = new Scanner(System.in);
    Automaton2D ca = new Automaton2D(Automaton2D.VON_NEUMANN, 10);
    System.out.println(ca.toString());
    ca.init();
    
    String action = "";
    while (!action.equals("STOP")) {
      switch (action) {
        case "clear":
          ca.clear();
          break;
        case "gen":
          String s = scan.nextLine();
          if (!s.isEmpty()) {
            ca.genRuleSet(Integer.parseInt(s));
          }else {
            ca.genRuleSet();
          }
          break;
        case "set":
          int x = scan.nextInt();
          int y = scan.nextInt();
          int val = scan.nextInt();
          ca.set(val, x, y);
          break;
        case "rule":
          int rule = Integer.parseInt(scan.nextLine(), 2);
          int state = scan.nextInt();
          ca.setRule(rule, state);
        default:
          break;
      }
      ca.step();
      System.out.println(ca.toString().replace("0", "  ").replace("1", "[]"));
      action = scan.nextLine();
    }
  }
}
