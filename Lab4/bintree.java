import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class bintree {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int info) {
            this.data = info;
            this.right = null;
            this.left = null;
        }
    }

    static class BinaryTree {

        static int inorderSuccessor(Node root) {
            Node temp = root;
            int min = root.data;
            while (temp.left != null) {
                min = temp.left.data;
                temp = temp.left;
            }
            return min;
        }

        static Node insert(Node root, int data) {
            if (root == null) {
                root = new Node(data);
                // System.out.println(newNode);
                return root;
            } else {
                if (data > root.data) {
                    root.right = insert(root.right, data);
                }

                else {
                    root.left = insert(root.left, data);
                }

            }
            return root;
        }

        static void preorder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }

        static void inorder(Node root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }

        static Node delete(Node root, int data) {
            if (root == null) {
                return root;
            }
            if (root.data < data) {
                root.right = delete(root.right, data);
            } else if (root.data > data) {
                root.left = delete(root.left, data);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }

                int minright = inorderSuccessor(root);

                root.data = minright;

                root.right = delete(root.right, minright);
            }
            return root;
        }

        static boolean searchNode(Node root, int data) {
            if (root == null) {
                return false;
            }
            if (root.data == data) {
                return true;
            } else if (root.data < data) {
                return searchNode(root.right, data);
            } else if (root.data > data) {
                return searchNode(root.left, data);
            }
            return false;
        }

    }

    public static void main(String[] args) {

        Node root = null;

        try {
            File obj = new File("C:\\Users\\Lenovo\\IdeaProjects\\Lab4\\src\\inputTree.txt");
            Scanner reader = new Scanner(obj);
            while (reader.hasNextInt()) {
                int num = reader.nextInt();
                root = BinaryTree.insert(root, num);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("error");
            return;
        }
        BinaryTree.inorder(root);
        BinaryTree.delete(root, 50);

        System.out.print("\n");
        BinaryTree.inorder(root);

        boolean bool_val = BinaryTree.searchNode(root, 12);
        if (bool_val) {
            System.out.println("\nelement found");
        } else {
            System.out.println("\nelement not found");
        }
    }

}