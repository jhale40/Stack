// Class: CS 3305 (02)
// Term: Spring 2023
// Name: Justin Hale
// Program Number: Assignment 3
// IDE: IntelliJ IDEA 2021.3.1 (Community Edition), openjdk-17 Oracle OpenJDK version17.0.2)

import java.util.*;
public class TestStackJustinHale {

    public static Scanner in = new Scanner(System.in);
    public static MyStackJustinHale<String> stack = new MyStackJustinHale<>();
    public static void main(String[] args) {

        String selection;

        do {
            System.out.print("\n-----MAIN MENU-----\n" +
                    "0 - Exit Program\n" +
                    "1 – Push\n" +
                    "2 - Pop\n" +
                    "3 – Peek (Top)\n" +
                    "4 - Size\n" +
                    "5 – Is Empty?\n" +
                    "6 – Print Stack\n" +
                    "Choose menu: ");
            selection = in.nextLine();
            System.out.println();


            switch (selection) {
                case "0":{
                    break;
                }
                case "1":{
                    pushToStack();
                    break;
                }
                case "2":{
                    popFromStack();
                    break;
                }
                case "3":{
                    peekAtTop();

                    break;
                }
                case "4":{
                    int size = stack.size();
                    System.out.println(size);
                    break;
                }
                case "5":{
                    System.out.println(stack.isEmpty());
                    break;
                }
                case "6":{
                    printStack();
                    break;
                }
                default:{
                    System.out.println("Invalid input");
                    break;
                }
            }
        } while (!(selection.equalsIgnoreCase("0")));

    }//main

    public static void pushToStack() {
        System.out.print("Enter String to push: ");
        String input = in.nextLine();
        stack.push(input);
    }

    public static void printStack() {
        String currentStack = stack.toString();
        System.out.println(currentStack);
    }

    public static void popFromStack() {
        if(stack.isEmpty()){
            System.out.println("Stack is empty");
        } else{
            stack.pop();
        }
    }

    public static void peekAtTop() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
        }else {
            String top = stack.peek();
            System.out.println(top);
        }
    }

}//Class
