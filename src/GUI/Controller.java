package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {
  
  //ONE DIMENSIONAL
  //<editor-fold desc="all ca1d stuff">
  @FXML private Slider ca1d_heightSlider;
  @FXML private Slider ca1d_widthSlider;
  @FXML private Slider ca1d_speedSlider;
  @FXML private RadioButton ca1d_000rb;
  @FXML private RadioButton ca1d_001rb;
  @FXML private RadioButton ca1d_010rb;
  @FXML private RadioButton ca1d_011rb;
  @FXML private RadioButton ca1d_100rb;
  @FXML private RadioButton ca1d_101rb;
  @FXML private RadioButton ca1d_110rb;
  @FXML private RadioButton ca1d_111rb;
  @FXML private TextField ca1d_ruleTextField;
  @FXML private AnchorPane ca1d_cAnchorPane;
  @FXML private Canvas ca1d_canvas;
  
  private int ca1d_yCurrent;
  private boolean ca1d_reset;
  
  @FXML void ca1d_runToggle(ActionEvent event) {
    if (!Main.ca1d_getIsRunning()) {
      Main.ca1d_run();
    } else {
      Main.ca1d_stop();
    }
  }
  @FXML void ca1d_step(ActionEvent event) {
    Main.ca1d_step();
  }
  @FXML void ca1d_setMiddle(ActionEvent event) {
    Main.ca1d_setMiddle();
  }
  @FXML void ca1d_randomize(ActionEvent event) {
    Main.ca1d_randomize();
  }
  @FXML void ca1d_clear(ActionEvent event) {
    ca1d_yCurrent = 0;
    GraphicsContext gc = ca1d_canvas.getGraphicsContext2D();
    gc.setFill(Color.WHITE);
    gc.fillRect(0, 0, ca1d_canvas.getWidth(), ca1d_canvas.getHeight());
  }
  @FXML void ca1d_setRulesRad(ActionEvent event) {
    int b0 = ca1d_000rb.isSelected() ? 1 : 0;
    int b1 = ca1d_001rb.isSelected() ? 1 : 0;
    int b2 = ca1d_010rb.isSelected() ? 1 : 0;
    int b3 = ca1d_011rb.isSelected() ? 1 : 0;
    int b4 = ca1d_100rb.isSelected() ? 1 : 0;
    int b5 = ca1d_101rb.isSelected() ? 1 : 0;
    int b6 = ca1d_110rb.isSelected() ? 1 : 0;
    int b7 = ca1d_111rb.isSelected() ? 1 : 0;
    String set = "" + b0 + b1 + b2 + b3 + b4 + b5 + b6 + b7;
    Main.ca1d_setRules(set);
    ca1d_ruleTextField.setText((new StringBuilder(set)).reverse().toString());
  }
  
  public void ca1d_updateCanvas(int[] cellSpace) {
    resizeCanvas();
    GraphicsContext gc = ca1d_canvas.getGraphicsContext2D();
    double width = ca1d_canvas.getWidth();
    double height = ca1d_canvas.getHeight();
    if (ca1d_reset) {
      gc.clearRect(0, 0, width, height);
      ca1d_reset = false;
    }
    gc.setFill(Color.BLACK);
    double cs = Math.round(((width < height) ? width/cellSpace.length : height/ca1d_heightSlider.getValue()));
    for (int x = 0; x < cellSpace.length; x++) {
      if(cellSpace[x] == 1) gc.fillRect(x * cs, ca1d_yCurrent * cs, cs, cs);
    }
    ca1d_yCurrent++;
    if (ca1d_yCurrent >= ca1d_heightSlider.getValue()) {
      ca1d_yCurrent = 0;
      Main.ca1d_stop();
      ca1d_reset = true;
    }
  }
  //</editor-fold>
  
  //TWO DIMENSIONAL
  //<editor-fold desc="all ca2d stuff">
  //<editor-fold desc="elements">
  @FXML private Slider ca2d_speedSlider;
  @FXML private Slider ca2d_sizeSlider;
  //@FXML private ComboBox<?> ca2d_neighborhoodComboBox;
  @FXML private RadioButton ca2d_b0RadioButton;
  @FXML private RadioButton ca2d_b1RadioButton;
  @FXML private RadioButton ca2d_b2RadioButton;
  @FXML private RadioButton ca2d_b3RadioButton;
  @FXML private RadioButton ca2d_b4RadioButton;
  @FXML private RadioButton ca2d_b5RadioButton;
  @FXML private RadioButton ca2d_b6RadioButton;
  @FXML private RadioButton ca2d_b7RadioButton;
  @FXML private RadioButton ca2d_b8RadioButton;
  @FXML private RadioButton ca2d_s0RadioButton;
  @FXML private RadioButton ca2d_s1RadioButton;
  @FXML private RadioButton ca2d_s2RadioButton;
  @FXML private RadioButton ca2d_s3RadioButton;
  @FXML private RadioButton ca2d_s4RadioButton;
  @FXML private RadioButton ca2d_s5RadioButton;
  @FXML private RadioButton ca2d_s6RadioButton;
  @FXML private RadioButton ca2d_s7RadioButton;
  @FXML private RadioButton ca2d_s8RadioButton;
  @FXML private AnchorPane ca2d_cAnchorPane;
  @FXML private Canvas ca2d_canvas;
  //</editor-fold>
  
  @FXML void ca2d_runToggle(ActionEvent event) {
    if (!Main.ca2d_getIsRunning()) {
      Main.ca2d_run();
    } else {
      Main.ca2d_stop();
    }
  }
  @FXML void ca2d_step(ActionEvent event) {
    Main.ca2d_step();
  }
  @FXML void ca2d_initialize(ActionEvent event) {
    Main.ca2d_randomize();
  }
  @FXML void ca2d_clear(ActionEvent event) {
    Main.ca2d_clear();
  }
  @FXML void ca2d_setRulesMultiple(ActionEvent event) {
    int[] ruleb = new int[9];
    int[] rules = new int[9];
    ruleb[0] = (ca2d_b0RadioButton.isSelected() ? 1 : 0);
    ruleb[1] = (ca2d_b1RadioButton.isSelected() ? 1 : 0);
    ruleb[2] = (ca2d_b2RadioButton.isSelected() ? 1 : 0);
    ruleb[3] = (ca2d_b3RadioButton.isSelected() ? 1 : 0);
    ruleb[4] = (ca2d_b4RadioButton.isSelected() ? 1 : 0);
    ruleb[5] = (ca2d_b5RadioButton.isSelected() ? 1 : 0);
    ruleb[6] = (ca2d_b6RadioButton.isSelected() ? 1 : 0);
    ruleb[7] = (ca2d_b7RadioButton.isSelected() ? 1 : 0);
    ruleb[8] = (ca2d_b8RadioButton.isSelected() ? 1 : 0);
    rules[0] = (ca2d_s0RadioButton.isSelected() ? 1 : 0);
    rules[1] = (ca2d_s1RadioButton.isSelected() ? 1 : 0);
    rules[2] = (ca2d_s2RadioButton.isSelected() ? 1 : 0);
    rules[3] = (ca2d_s3RadioButton.isSelected() ? 1 : 0);
    rules[4] = (ca2d_s4RadioButton.isSelected() ? 1 : 0);
    rules[5] = (ca2d_s5RadioButton.isSelected() ? 1 : 0);
    rules[6] = (ca2d_s6RadioButton.isSelected() ? 1 : 0);
    rules[7] = (ca2d_s7RadioButton.isSelected() ? 1 : 0);
    rules[8] = (ca2d_s8RadioButton.isSelected() ? 1 : 0);
    for (int a = 0; a < 9; a++) {
      Main.ca2d_setBirthRule(a, ruleb[a]);
      Main.ca2d_setSurvivalRule(a, rules[a]);
    }
  }
  //@FXML void ca2d_setNeighborhood(ActionEvent event) {}
  @FXML void ca2d_generateRandomRuleSet(ActionEvent event) {
    Main.ca2d_generateRandomRuleSet();
  }
  
  public void ca2d_toggleState(int x, int y) {
    double width = ca2d_canvas.getWidth();
    double height = ca2d_canvas.getHeight();
    int s = Main.ca2d_getSize();
    double cs = Math.round(((width < height) ? width/s : height/s));
    x/=cs;
    y/=cs;
    ca2d_updateCanvas(Main.ca2d_toggleState(x, y));
  }
  
  public void ca2d_updateCanvas(int[][] cellSpace) {
    resizeCanvas();
    GraphicsContext gc = ca2d_canvas.getGraphicsContext2D();
    double width = ca2d_canvas.getWidth();
    double height = ca2d_canvas.getHeight();
    gc.clearRect(0, 0, width, height);
    gc.setFill(Color.BLACK);
    double cs = Math.round(((width < height) ? width/cellSpace.length : height/cellSpace.length));
    for (int y = 0; y < cellSpace.length; y++) {
      for (int x = 0; x < cellSpace.length; x++) {
        //gc.setFill((cellSpace[x][y] == 1) ? Color.BLACK : Color.WHITE);
        if(cellSpace[x][y] == 1) gc.fillRect(x * cs, y * cs, cs, cs);
      }
    }
  }
  //</editor-fold>
  
  //Other stuff
  @FXML void closeApplication(ActionEvent event) {}
  
  public void init() {
    ca1d_yCurrent = 0;
    ca1d_reset = false;
    ca1d_widthSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      Main.ca1d_setWidth((int)ca1d_widthSlider.getValue());
    });
    ca1d_speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      Main.ca1d_setSpeed((int)ca1d_speedSlider.getValue());
    });
    ca2d_canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        ca2d_toggleState((int)event.getX(), (int)event.getY());
      }
    });
    ca2d_sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      Main.ca2d_setSize((int)ca2d_sizeSlider.getValue());
    });
    ca2d_speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      Main.ca2d_setSpeed((int)ca2d_speedSlider.getValue());
    });
  }
  
  public void resizeCanvas() {
    double c1w = ca1d_cAnchorPane.getWidth();
    double c1h = ca1d_cAnchorPane.getHeight();
    ca1d_canvas.setWidth(c1w);
    ca1d_canvas.setHeight(c1h);
    double c2w = ca2d_cAnchorPane.getWidth();
    double c2h = ca2d_cAnchorPane.getHeight();
    ca2d_canvas.setWidth(c2w);
    ca2d_canvas.setHeight(c2h);
  }
  
}
