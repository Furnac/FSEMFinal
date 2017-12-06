package automaton;

import java.util.Scanner;

public class Automaton2DTester {
  public static void main (String[] args) {
    Scanner scan = new Scanner(System.in);
    Automaton2D ca = new Automaton2D();
    System.out.println(ca.toString());
    ca.init();
    System.out.println(ca.toString());
    
    do {
      ca.step();
      System.out.println(ca.toString());
    } while (!scan.nextLine().equals("STOP"));
  }
}
