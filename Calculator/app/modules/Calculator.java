package modules;

/**
 * Created by qiqu on 2/15/16.
 */
public class Calculator {

    public double a;
    public double b;
    public String method="";

    public String calculate(){
        switch (method){
            case "+":
                return plus();
            case "-":
                return minus();
            case "*":
                return multiply();
            case "/":
                return divide();
        }
        return "";
    }

    private String plus(){
        return (a+b)+"";
    }

    private String minus(){
        return (a-b)+"";
    }

    private String multiply(){
        return (a*b)+"";
    }

    private String divide(){
        if (b==0) return "Error: divisor can't be 0.";
        else return (a/b)+"";
    }



}
