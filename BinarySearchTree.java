
import java.util.Scanner;

class BST {
    class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    Node root = null;

    // Insert a node
    Node insert(Node root, int data) {
        if (root == null)
            return new Node(data);

        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);

        return root;
    }

    // Search a node
    boolean search(Node root, int key) {
        if (root == null)
            return false;

        if (root.data == key)
            return true;

        if (key < root.data)
            return search(root.left, key);

        return search(root.right, key);
    }

    // Find minimum value
    Node minValue(Node root) {
        while (root.left != null)
            root = root.left;

        return root;
    }

    // Delete a node
    Node delete(Node root, int key) {
        if (root == null)
            return null;

        if (key < root.data)
            root.left = delete(root.left, key);
        else if (key > root.data)
            root.right = delete(root.right, key);
        else {
            if (root.left == null)
                return root.right;

            if (root.right == null)
                return root.left;

            Node temp = minValue(root.right);
            root.data = temp.data;
            root.right = delete(root.right, temp.data);
        }

        return root;
    }

    // Inorder traversal
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST tree = new BST();
        int choice, value;

        do {
            System.out.println("\n1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display (Inorder)");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    value = sc.nextInt();
                    tree.root = tree.insert(tree.root, value);
                    break;

                case 2:
                    System.out.print("Enter value to search: ");
                    value = sc.nextInt();

                    if (tree.search(tree.root, value))
                        System.out.println("Value found");
                    else
                        System.out.println("Value not found");
                    break;

                case 3:
                    System.out.print("Enter value to delete: ");
                    value = sc.nextInt();
                    tree.root = tree.delete(tree.root, value);
                    break;

                case 4:
                    System.out.print("Inorder: ");
                    tree.inorder(tree.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);

        sc.close();
    }
}
