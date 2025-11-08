package app;

public class CalculatorModel {
    public double Calculate(double a,double b,string op){
        return switch (op){
            case"+"->a+b;
            case"-"->a-b;
            case"*"->a*b;
            case"/"->{
                if (b==0)throw new arithmeticException("cannot divide by zero");
                yield a/b;
            }
             default ->throw new illegalArgumentException("unknown operator");  
        };
    }
}