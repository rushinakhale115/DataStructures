package trees;

public class preordertraversal {
    public static class Node {
        int data;
        InorderTraversal.Node left;
        InorderTraversal.Node right;

        // Constructor to initialize a node
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Function to perform inorder traversal
    public static void traversetree(InorderTraversal.Node root) {
        if (root == null) {
            return; // Base case to stop recursion on null nodes
        }
        System.out.println(root.data); // Visit the current node (root)
        traversetree(root.left); // Traverse left subtree
        traversetree(root.right); // Traverse right subtree
    }

    public static void main(String[] args) {
        // Construct the binary tree
        InorderTraversal.Node root = new InorderTraversal.Node(1);
        root.left = new InorderTraversal.Node(2);
        root.right = new InorderTraversal.Node(3);
        root.left.left = new InorderTraversal.Node(4);
        root.left.right = new InorderTraversal.Node(5);
        root.right.right = new InorderTraversal.Node(6);

        // Call inorder traversal on the binary tree
        traversetree(root);
    }
}

