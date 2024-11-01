
package trees;
 class InorderTraversal {

    // Define a node class for the binary tree
    public static class Node {
        int data;
        Node left;
        Node right;

        // Constructor to initialize a node
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Function to perform inorder traversal
    public static void traversetree(Node root) {
        if (root == null) {
            return; // Base case to stop recursion on null nodes
        }
        traversetree(root.left); // Traverse left subtree
        System.out.println(root.data); // Visit the current node (root)
        traversetree(root.right); // Traverse right subtree
    }

    public static void main(String[] args) {
        // Construct the binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        // Call inorder traversal on the binary tree
        traversetree(root);
    }
}
