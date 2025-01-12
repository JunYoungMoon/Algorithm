import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Heap {
    List<Integer> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    public int findParentIndex(int index) {
        return (index - 1) / 2;
    }

    public int findLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    public int findRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    public void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void push(int data) {
        heap.add(data);

        int currentIndex = heap.size() - 1;

        while (currentIndex > 0) {
            int parentIndex = findParentIndex(currentIndex);
            if (heap.get(parentIndex) <= heap.get(currentIndex)) {
                break;
            }
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
    }

    public int pop() {
        if (heap.isEmpty()) {
            return 0;
        }

        int returnValue = heap.get(0);

        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int currentIndex = 0;

        while (true) {
            int leftIndex = findLeftChildIndex(currentIndex);
            int rightIndex = findRightChildIndex(currentIndex);
            int smallerIndex = currentIndex;

            if (leftIndex < heap.size() && heap.get(leftIndex) < heap.get(smallerIndex)) {
                smallerIndex = leftIndex;
            }
            if (rightIndex < heap.size() && heap.get(rightIndex) < heap.get(smallerIndex)) {
                smallerIndex = rightIndex;
            }
            if (smallerIndex == currentIndex) {
                break;
            }

            swap(currentIndex, smallerIndex);
            currentIndex = smallerIndex;
        }

        return returnValue;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        Heap heap = new Heap();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size; i++) {
            int inputValue = scanner.nextInt();
            if (inputValue == 0) {
                output.append(heap.pop()).append("\n");
            } else {
                heap.push(inputValue);
            }
        }

        System.out.print(output);
    }
}