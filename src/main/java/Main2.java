import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main2 {
    public static boolean isNumeric(String s)  {
        try  {
            double d = Double.parseDouble(s);
        } catch(NumberFormatException nfe)  {
            return false;
        }
        return true;
    }

    // enqueue -> | 3 | 2 | 1 | -> dequeue
    public static void main(String[] argv) {
        LinkedList<String> input          = new LinkedList<String>();
        LinkedList<String> tokens         = new LinkedList<String>();
        Stack<Operator> operators         = new Stack<Operator>();
        LinkedList<String> output         = new LinkedList<String>();
        String[] supported_operators = {"+", "-", "*", "/", "(", ")"};


        String stdIn = "((3 + (4 / 2)) * (5 + 6)))";

        stdIn = stdIn.replaceAll("\\s+","");

        for (String in : stdIn.split("")) {
            tokens.addFirst(in);
        }

        System.out.println("tokens");
        System.out.println(tokens);

        for (String in: tokens) {
            input.addFirst(in);
        }

        System.out.println("input");
        System.out.println(input);


        while (!tokens.isEmpty()) {
            String token = tokens.removeLast();

            if (isNumeric(token))
                output.addFirst(token);
            else if (Arrays.asList(supported_operators).contains(token)) {
                Operator operator     = new Operator(token);
                Operator top_operator = null;

                if (!operators.empty())
                    top_operator = operators.peek();

                if (token.equals(")")) {
                    while (top_operator != null && !top_operator.operator().equals("(")) {
                        output.addFirst(top_operator.operator());
                        operators.pop();
                        if (!operators.empty())
                            top_operator = operators.peek();
                    }
                    operators.pop();

                } else if (token.equals("(")) {
                    operators.push(operator);
                } else {
                    while (top_operator != null && top_operator.precedence() > operator.precedence()) {
                        output.addFirst(top_operator.operator());
                        operators.pop();
                        top_operator = operators.peek();
                    }

                    operators.push(operator);
                }
            }
        }

        while (!operators.isEmpty()) {
            Operator operator = operators.pop();
            output.addFirst(operator.operator());
        }

        String in = "";
        //Polish calculator = new Polish(output);

        for (String s: input) {
            in += s + " ";
        }

        System.out.printf("Infix: %s \n", in);
        //System.out.printf("Postfix: %s \n", calculator.toString());
        //System.out.printf("Result: %d", calculator.calculate());

    }
}

class Operator {
    private String operator;

    public Operator(String op) {
        operator = op;
    }

    public String operator() {
        return operator;
    }

    public int precedence() {
        if (operator.equals("("))                              return 1;
        else if (operator.equals("+") || operator.equals("-")) return 2;
        else if (operator.equals("*") || operator.equals("/")) return 3;

        return -1;
    }
}

