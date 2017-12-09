package automaton;

import java.util.Scanner;

public class Automaton1DTester {
  public static void main (String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("1D");
    Automaton1D ca = new Automaton1D(100);
    ca.set(1, 49);
    ca.setRuleSet(0);
    System.out.print(ca.toString().replace("0", "  ").replace("1", "[]"));
    while (!scan.nextLine().equals("STOP")) {
      ca.step();
      System.out.print(ca.toString().replace("0", "  ").replace("1", "[]"));
    }
  }
}
