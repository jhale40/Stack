// Class: CS 3305 (02)
// Term: Spring 2023
// Name: Justin Hale
// Program Number: Assignment 3
// IDE: IntelliJ IDEA 2021.3.1 (Community Edition), openjdk-17 Oracle OpenJDK version17.0.2)

public class MyStackJustinHale<E> {
    private java.util.ArrayList<E> stack = new java.util.ArrayList<>();

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {

        return stack.size();
    }

    public E peek() {
        int last = (stack.size() - 1);
        return stack.get(last);
    }

    public E pop() {
        E topElement = stack.get(size() - 1);
        stack.remove(size() - 1);
        return topElement;
    }

    public void push(E element) {
        stack.add(element);
    }

    @Override
    public String toString() {
        return stack.toString();
    }


}
