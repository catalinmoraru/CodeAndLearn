import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class HackerRankInfixFullParantEx2 {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */


        Scanner scanner = new Scanner(System.in);
        // scanner.useDelimiter("");

        String infix ="((*2) + 3*)";

        // parantesis operator
        Pattern p1 = Pattern.compile("[(][-+/*]");
        Matcher m = p1.matcher(infix);
        if (m.find()){
            System.out.println("ERR VALID parantesis operator");
        }

        //  operator parantesis
        Pattern p2 = Pattern.compile("[(][-+/*]");
        Matcher m2 = p1.matcher(infix);
        if (m2.find()){
            System.out.println("ERR VALID  operator parantesis");
        }

       while (scanner.hasNext()) {
            infix = scanner.nextLine();
            //System.out.println(infix);
        }
        scanner.close();

        String expr = infix;
        //System.out.println(infix);

        expr = expr.replaceAll("\\(-", "(0-");
        //System.out.println(expr);

        System.out.println(evaluateInfix(expr));
    }

    public static String evaluateInfix(String exps){

        /**remove if any spaces from the expression**/
        exps = exps.replaceAll("\\s+", "");
        /**we assume that the expression is in valid format**/


        Stack<String> stack = new Stack<String>();
        /**break the expression into tokens**/
        StringTokenizer tokens = new StringTokenizer(exps, "{}()*/+-", true);
        while(tokens.hasMoreTokens()){
            String tkn = tokens.nextToken();
            /**read each token and take action**/
            if(tkn.equals("(")
                    || tkn.equals("{")
                    || tkn.matches("[0-9]+")
                    || tkn.equals("*")
                    || tkn.equals("/")
                    || tkn.equals("+")
                    || tkn.equals("-")){
                /**push token to the stack**/
                stack.push(tkn);
            } else if(tkn.equals("}") || tkn.equals(")")){
                try {
                    double op2 = Double.parseDouble(stack.pop());
                    String oprnd = stack.pop();
                    double op1 = Double.parseDouble(stack.pop());
                    /**Below pop removes either } or ) from stack**/
                    if(!stack.isEmpty()){
                        stack.pop();
                    }
                    double result = 0;
                    if(oprnd.equals("*")){
                        result = op1*op2;
                    } else if(oprnd.equals("/")){
                        if ( op2 == 0){
                            return "ERROR";
                        }
                        result = op1/op2;
                    } else if(oprnd.equals("+")){
                        result = op1+op2;
                    } else if(oprnd.equals("-")){
                        result = op1-op2;
                    }
                    /**push the result to the stack**/
                    stack.push(result+"");
                } catch (Exception e) {
                    return "ERROR";
                }
            }
        }
        String finalResult = "";
        try {
            finalResult = stack.pop();
        } catch (Exception e) {
            return "ERROR";
        }

        if ( Double.parseDouble(finalResult) % 1 == 0 ){
            finalResult = finalResult.replace(".0","");
        }
        return finalResult;
    }


}



