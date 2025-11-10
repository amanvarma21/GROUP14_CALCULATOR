package app;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        CalculatorView view = new CalculatorView();
        CalculatorModel model = new CalculatorModel();
        new CalculatorController(view, model);

        Scene scene = new Scene(view.getRoot(), 320, 450);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

        stage.setTitle("iPhone Style Calculator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}