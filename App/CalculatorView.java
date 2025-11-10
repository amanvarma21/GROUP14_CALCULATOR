package app;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.function.Consumer;

public class CalculatorView {

    private final VBox root = new VBox(10);
    private final TextField display = new TextField();

    private Consumer<String> numberHandler;
    private Consumer<String> operatorHandler;
    private Runnable equalsHandler;
    private Runnable clearHandler;

    public CalculatorView() {
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: black; -fx-padding: 20;");

        display.setPrefHeight(70);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setEditable(false);
        display.setText("0");
        display.getStyleClass().add("display");

        GridPane grid = buildButtons();
        root.getChildren().addAll(display, grid);
    }

    private GridPane buildButtons() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        String[][] layout = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", "C", "=", "+"}
        };

        for (int r = 0; r < layout.length; r++) {
            for (int c = 0; c < layout[r].length; c++) {
                String text = layout[r][c];
                Button btn = new Button(text);
                btn.getStyleClass().add("calc-button");

                if (text.matches("\\d")) {
                    btn.setOnAction(e -> numberHandler.accept(text));
                } else if (text.equals("=")) {
                    btn.setOnAction(e -> equalsHandler.run());
                } else if (text.equals("C")) {
                    btn.setOnAction(e -> clearHandler.run());
                } else {
                    btn.setOnAction(e -> operatorHandler.accept(text));
                }
                grid.add(btn, c, r);
            }
        }
        return grid;
    }

    public VBox getRoot() {
        return root;
    }

    public void setDisplay(String value) {
        display.setText(value);
    }

    public void appendDisplay(String value) {
        display.setText(display.getText() + value);
    }

    public String getDisplay() {
        return display.getText();
    }

    public void onNumberPressed(Consumer<String> handler) {
        this.numberHandler = handler;
    }

    public void onOperatorPressed(Consumer<String> handler) {
        this.operatorHandler = handler;
    }

    public void onEqualsPressed(Runnable handler) {
        this.equalsHandler = handler;
    }

    public void onClearPressed(Runnable handler) {
        this.clearHandler = handler;
    }
}
