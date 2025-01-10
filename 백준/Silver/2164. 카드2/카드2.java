import java.util.Scanner;

class Queue {
    int[] arr;
    int front = 0;
    int rear = 0;
    int size = 0;

    public Queue(int capacity) {
        arr = new int[capacity];
    }

    public void enQueue(int data) {
        arr[rear] = data;
        rear = (rear + 1) % arr.length;
        size++;
    }

    public int deQueue() {
        int data = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return data;
    }

    public void printQueue() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(front + i) % arr.length]);
        }
    }
}

public class Main {
    public static void solution(int size) {
        Queue queue = new Queue(size);

        for(int i = 1 ; i <= size ; i++){
            queue.enQueue(i);
        }

        while (queue.size > 1) {
            queue.deQueue();
            queue.enQueue(queue.deQueue());
        }

        queue.printQueue();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        solution(scanner.nextInt());
    }
}
