import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Stack {
    char[] charArr;
    int size;
    int top = -1;

    public Stack(int size) {
        charArr = new char[size];
        this.size = size;
    }

    public void push(char c) {
        if (!isFull()) {
            charArr[++top] = c;
        }
    }

    public void pop() {
        if (!isEmpty()) {
            top--;
        }
    }

    public int peek() {
        return top;
    }

    public boolean isFull() {
        return top >= size - 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }
}

public class Main {

    public static String solution(String parentheses) {
        Stack stack = new Stack(parentheses.length());

        for (int i = 0; i < parentheses.length(); i++) {
            if (parentheses.charAt(i) == '(') {
                stack.push(parentheses.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return "NO";
                }
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        scanner.nextLine();

        String[] parentheses = new String[size];
        List<String> results = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            parentheses[i] = scanner.nextLine();
            results.add(solution(parentheses[i]));
        }

        for (String result : results) {
            System.out.println(result);
        }
    }
}