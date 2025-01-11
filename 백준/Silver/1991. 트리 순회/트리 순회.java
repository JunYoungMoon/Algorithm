import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class Main {
    public static void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data);
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data);
    }

    public static void main(String[] args) {
        Map<String, Node> nodeMap = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < size; i++) {
            String input = scanner.nextLine();

            String[] nodes = input.split(" ");

            String parentNode = nodes[0];
            String leftNode = nodes[1];
            String rightNode = nodes[2];

            nodeMap.putIfAbsent(parentNode, new Node(parentNode));
            nodeMap.putIfAbsent(leftNode, new Node(leftNode));
            nodeMap.putIfAbsent(rightNode, new Node(rightNode));

            if (!leftNode.equals(".")) {
                nodeMap.get(parentNode).left = nodeMap.get(leftNode);
            }

            if (!rightNode.equals(".")) {
                nodeMap.get(parentNode).right = nodeMap.get(rightNode);
            }
        }

        Node root = nodeMap.get("A");

        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
    }
}