// Class: CS 3305 (02)
// Term: Spring 2023
// Name: Justin Hale
// Program Number: Assignment 3
// IDE: IntelliJ IDEA 2021.3.1 (Community Edition), openjdk-17 Oracle OpenJDK version17.0.2)

import java.util.ArrayList;
import java.util.Scanner;

public class ExprJustinHale {
    public static MyStackJustinHale<String> stack = new MyStackJustinHale<>();
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String choice;

        do {
            System.out.print("Enter an infix:\t\t");
            String infix = in.nextLine();
            String postfix = infixToPostfix(infix);

            System.out.println("Postfix Expression:\t" + postfix);

            double result = postfixEval(postfix);
            System.out.println("Result value:\t\t" +result);

            System.out.print("Do you want to continue? (Y/N) ");
            choice = in.nextLine();

        } while (!(choice.equalsIgnoreCase("N")));


    }//main

    /** Converts infix expression to postfix expression **/
    public static String infixToPostfix(String infix){
        String postfix= "";

        // operand to String
        for (int i = 0; i < infix.length(); i++) {
            char currentSymbol = infix.charAt(i);

            // If the currentSymbol is an operand
            if ((!isOperator(currentSymbol)) && (!isParentheses(currentSymbol))) {
                String s = String.valueOf(currentSymbol);
                postfix += s;

            // If the currentSymbol is an operator
            } else if (isOperator(currentSymbol)) {

            // Pop off all operators that have equal or higher precedence and append them to the output string
                    while (precedence(currentSymbol)) {

                        String topStackOperand = stack.pop();
                        postfix += topStackOperand;
                    }
                    String currSymbol = String.valueOf(currentSymbol);
                    stack.push(currSymbol);

            } else if (isParentheses(currentSymbol)) {
                String operatorsInParentheses = parenthesesMethod(currentSymbol);
                postfix += operatorsInParentheses;
            }
        }//for loop

        /// When last operand is processed. Pop and append remaining operators to postfix String
        while (!stack.isEmpty()) {
            String operator = stack.pop();
            postfix+=operator;
        }
        return postfix;
    }



    /** Evaluates postfix expression and returns the result as a double **/
    public static double postfixEval(String postfix){
        MyStackJustinHale<Double> doubleStack = new MyStackJustinHale<>();

        double finalResult;
        for (int i = 0; i < postfix.length(); i++) {
            char currentSymbol = postfix.charAt(i);
            if (!isOperator(currentSymbol)) {
                String l = String.valueOf(currentSymbol);
                Double r;
                r = Double.valueOf(l);
                doubleStack.push(r);
            } else {
                //Result = Top2 OPERATOR Top1
                double topOne = doubleStack.pop();
                double topTwo = doubleStack.pop();
                double result;

                switch (currentSymbol) {
                    case '+': {
                        result = topTwo + topOne;
                        doubleStack.push(result);
                        break;
                    }
                    case '-': {
                        result = topTwo - topOne;
                        doubleStack.push(result);
                        break;
                    }
                    case '*': {
                        result = topTwo * topOne;
                        doubleStack.push(result);
                        break;
                    }
                    case '/': {
                        result = topTwo / topOne;
                        doubleStack.push(result);
                        break;
                    }
                    case '^': {
                        result = Math.pow(topTwo,topOne);
                        doubleStack.push(result);
                        break;
                    }
                }
                //doubleStack.push(result);
            }
        }
        finalResult = doubleStack.pop();
        return finalResult;
    }


    public static boolean isOperator(char currentSymbol) {
        switch (currentSymbol){
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }


    public static boolean isParentheses(char currentSymbol) {
        switch (currentSymbol){
            case '(':
            case ')':
                return true;
        }
        return false;
    }


    /** If the current input token is '(', push it onto the stack.
        If the current input token is ')', pop off all operators
        and append them to the output string until a '(' is popped; discard the '('. **/
    public static String parenthesesMethod(char currentSymbol) {
        String postfix = "";
         if (currentSymbol == '(') {
            String token = String.valueOf(currentSymbol);
            stack.push(token);
        } else if (currentSymbol == ')') {

             String topElement;
             do {
                 String topStackOperand = stack.pop();
                 postfix += topStackOperand;
                 topElement = stack.peek();
             } while( !(topElement.equalsIgnoreCase("(")));{
                stack.pop();
            }
        }
         return postfix;
    }

/** Checks if Operator on top of Stack has higher precedence than Current Operator **/
    public static boolean precedence(char currentOperator) {

        boolean bool;

        if (stack.isEmpty()) {
            bool = false;
        } else {
            String stackOperatorString = stack.peek();
            char stackOperator = stackOperatorString.charAt(0);

            int stackOperatorPrecedence = orderOfPrecedence(stackOperator);
            int currentOperatorPrecedence = orderOfPrecedence(currentOperator);

            if (stackOperatorPrecedence >= currentOperatorPrecedence) {
                bool = true;
            } else
                bool = false;
        }
        return bool;
    }

/** Returns an Integer for each Operator. Order of precedence is ranked by higher Integers **/
    public static int orderOfPrecedence(char operator) {
        int x = 0;
        switch (operator) {
            case '^':{
                x = 3;
                break;
            }
            case '*':
            case '/':{
                x = 2;
                break;
            }
            case '+':
            case '-':{
                x = 1;
                break;
            }
        }
        return x;
    }

}
