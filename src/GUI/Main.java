package GUI;

import automaton.Automaton1D;
import automaton.Automaton2D;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
  
  private static Automaton1D ca1d;
  private static Thread ca1d_thread;
  private static boolean ca1d_isRunning;
  private static int ca1d_speed = 60;
  
  private static Automaton2D ca2d;
  private static Thread ca2d_thread;
  private static boolean ca2d_isRunning;
  private static int ca2d_speed = 1;
  
  private static Controller controller;
  private static Stage stage;
  
  @Override
  public void start(Stage primaryStage) throws Exception{
    stage = primaryStage;
    FXMLLoader loader = new FXMLLoader(Main.class.getResource("Scene1.fxml"));
    Parent root = loader.load();
    controller = loader.getController();
    controller.init();
    stage.setTitle("Luis Rovira's Cellular Automata Simulator");
    stage.setScene(new Scene(root, 800, 800));
    stage.show();
    ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> controller.resizeCanvas();
    stage.widthProperty().addListener(stageSizeListener);
    stage.heightProperty().addListener(stageSizeListener);
  }
  

  public static void main(String[] args) {
    ca1d = new Automaton1D();
    ca2d = new Automaton2D(Automaton2D.MOORE, 100);
    launch(args);
  }
  
  //<editor-fold desc="ONE DIMENSIONAL">
  public static void ca1d_setWidth(int width) {
    ca1d.setWidth(width);
  }
  
  public static void ca1d_setMiddle() {
    ca1d.clear();
    ca1d.set(1, ca1d.getSize()/2);
    controller.ca1d_updateCanvas(ca1d.getCellSpace());
  }
  
  public static void ca1d_randomize() {
    ca1d.init();
    controller.ca1d_updateCanvas(ca1d.getCellSpace());
  }
  
  public static void ca1d_setSpeed(int speed) {
    ca1d_speed = speed;
  }
  
  public static void ca1d_setRules(String ruleSet) {
    ca1d.setRuleSet(ruleSet);
  }
  
  public static void ca1d_run() {
    ca1d_isRunning = true;
    Runnable r = new Runnable() {
      public void run(){
        while(ca1d_isRunning){
          try {
            ca1d.step();
            Platform.runLater(new Runnable(){
              public void run(){
                controller.ca1d_updateCanvas(ca1d.getCellSpace());
              }
            });
            Thread.sleep(1000 / ca1d_speed);
          }catch(Exception e) {
            ca1d_isRunning = false;
            return;
          }
        }
      }
    };
    ca1d_thread = new Thread(r);
    ca1d_thread.start();
  }
  
  public static void ca1d_stop() {
    ca1d_isRunning = false;
  }
  
  public static boolean ca1d_getIsRunning() {
    return ca1d_isRunning;
  }
  
  public static void ca1d_step() {
    ca1d.step();
    controller.ca1d_updateCanvas(ca1d.getCellSpace());
  }
  //</editor-fold>
  
  //<editor-fold desc="TWO DIMENSIONAL">
  
  public static void ca2d_generateRandomRuleSet() {
    ca2d.genRuleSet();
  }
  
  public static void ca2d_setBirthRule(int surrounding, int state) {
    ca2d.setRules(0, surrounding, "=", state);
  }
  
  public static void ca2d_setSurvivalRule(int surrounding, int state) {
    ca2d.setRules(1, surrounding, "=", state);
  }
  
  public static void ca2d_randomize() {
    controller.ca2d_updateCanvas(ca2d.init());
  }
  
  public static void ca2d_clear() {
    controller.ca2d_updateCanvas(ca2d.clear());
  }
  
  public static int[][] ca2d_toggleState(int x, int y) {
    return ca2d.toggleState(x, y);
  }
  
  public static void ca2d_setSize(int size) {
    ca2d.setSize(size);
  }
  
  public static int ca2d_getSize() {
    return ca2d.getSize();
  }
  
  public static void ca2d_setSpeed(int speed) {
    ca2d_speed = speed;
  }
  
  public static void ca2d_run() {
    ca2d_isRunning = true;
    Runnable r = new Runnable() {
      public void run(){
        while(ca2d_isRunning){
          try {
            ca2d.step();
            Platform.runLater(new Runnable(){
              public void run(){
                controller.ca2d_updateCanvas(ca2d.getCellSpace());
              }
            });
            Thread.sleep(1000 / ca2d_speed);
          }catch(Exception e) {
            ca2d_isRunning = false;
            return;
          }
        }
      }
    };
    ca2d_thread = new Thread(r);
    ca2d_thread.start();
  }
  
  public static void ca2d_stop() {
    ca2d_isRunning = false;
  }
  
  public static boolean ca2d_getIsRunning() {
    return ca2d_isRunning;
  }
  
  public static void ca2d_step() {
    ca2d.step();
    controller.ca2d_updateCanvas(ca2d.getCellSpace());
  }
  //</editor-fold>
  
  @Override
  public void stop() {
    System.exit(0);
  }
  
}
