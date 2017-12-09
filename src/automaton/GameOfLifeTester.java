package automaton;

import java.util.Scanner;

public class GameOfLifeTester {
  public static void main(String[] args) {
    System.out.println("Hello!");
    Scanner scan = new Scanner(System.in);
    Automaton2D gol = new Automaton2D(Automaton2D.MOORE, 50);
    gol.genRuleSet(0);
    //gol.setRules(0,0,"=",0);
    gol.init();
    
    gol.setRules(1, 2, "<", 0);
    gol.setRules(1, 2, "=", 1);
    gol.setRules(1,3,"=",1);
    gol.setRules(1,3,">",0);
    gol.setRules(0,3,"=",1);
    /*
    gol.set(1, 5, 5);
    gol.set(1, 5, 6);
    gol.set(1, 5, 7);
    gol.set(1, 5, 8);
    gol.set(1, 5, 2);
    gol.set(1, 5, 3);
    gol.set(1, 5, 10);
    gol.set(1, 5, 11);
  
    gol.set(1, 4, 4);
    gol.set(1, 4, 9);
    gol.set(1, 6, 4);
    gol.set(1, 6, 9);
    */
    System.out.println(gol.toString().replace("0", "  ").replace("1", "[]"));
    while(!scan.nextLine().equals("STOP")) {
      gol.step();
      System.out.println(gol.toString().replace("0", "  ").replace("1", "[]"));
    }
  }
}
