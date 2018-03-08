import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {

    static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 5;
            case '*':
            case '/':
                return 10;
            default:
                throw new IllegalArgumentException("invalid operator");
        }
    }

    public static String infixToPostfix(String infix) {
        StringTokenizer tokenizer = new StringTokenizer(infix);
        String postfix = "";
        Stack opStack = new Stack();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                postfix += (token + " ");
            }
            else {
                while (!opStack.empty()) {
                    char top = ((Character)opStack.peek()).charValue();
                    if (precedence(top) >= precedence(c)) {
                        postfix += (top + " ");
                        opStack.pop();
                    }
                    else {
                        break;
                    }
                }

                opStack.push(new Character(c));
            }
        }

        while (!opStack.empty()) {
            char top = ((Character)opStack.pop()).charValue();
            postfix += (top + " ");
        }

        return postfix;
    }

    public static int evalPostfix(String postfix) {
        StringTokenizer tokenizer = new StringTokenizer(postfix);
        Stack valStack = new Stack();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                valStack.push(new Integer(token));
            }
            else {
                int rightVal = ((Integer)valStack.pop()).intValue();
                int leftVal = ((Integer)valStack.pop()).intValue();
                int rslt;

                switch (c) {
                    case '+': rslt = leftVal + rightVal; break;
                    case '-': rslt = leftVal - rightVal; break;
                    case '*': rslt = leftVal * rightVal; break;
                    case '/': rslt = leftVal / rightVal; break;
                    default:
                        throw new IllegalArgumentException("invalid postfix expression");
                }

                valStack.push(new Integer(rslt));
            }
        }

        int rslt = ((Integer)valStack.pop()).intValue();
        if (!valStack.empty()) {
            throw new IllegalArgumentException("invalid postfix expression");
        }

        return rslt;
    }

    public static void main(String[] args) {
        String infix = "1+2*5/5";
        String postfix = infixToPostfix(infix);
        System.out.println("postfix: " + postfix);
        int value = evalPostfix(postfix);
        System.out.println("value: " + value);
    }
}