package app;

public class CalculatorController {
    private final CalculatorView view;
    private final CalculatorModel model;

    private double firstValue = 0;
    private String operator = "";
    private boolean startNew = true;

    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;

        view.onNumberPressed(n -> {
            if (startNew) {
                view.setDisplay(n);
                startNew = false;
            } else {
                view.appendDisplay(n);
            }
        });

        view.onOperatorPressed(op -> {
            firstValue = Double.parseDouble(view.getDisplay());
            operator = op;
            startNew = true;
        });

        view.onEqualsPressed(() -> {
            try {
                double secondValue = Double.parseDouble(view.getDisplay());
                double result = model.calculate(firstValue, secondValue, operator);
                view.setDisplay(String.valueOf(result));
                startNew = true;
            } catch (Exception e) {
                view.setDisplay("Error");
                startNew = true;
            }
        });

        view.onClearPressed(() -> {
            view.setDisplay("0");
            firstValue = 0;
            operator = "";
            startNew = true;
        });
    }
}
