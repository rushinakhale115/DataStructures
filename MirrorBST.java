class Mirror {
    static class Node {
        int data;
        Node left;
        Node right;
    }

    static Node createNode(int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.left = null;
        newNode.right = null;
        return newNode;
    }

    static void inorder(Node root) {
        if (root == null) 
            return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    static Node mirrorify(Node root) {
        if (root == null) {
            return null;
        }

        // Create new mirror node from original tree node
        Node mirror = createNode(root.data);
        mirror.right = mirrorify(root.left);
        mirror.left = mirrorify(root.right);
        return mirror;
    }

    // Driver code
    public static void main(String args[]) {
        Node tree = createNode(5);
        tree.left = createNode(3);
        tree.right = createNode(6);
        tree.left.left = createNode(2);
        tree.left.right = createNode(4);

        // Print inorder traversal of the input tree
        System.out.print("Inorder of original tree: ");
        inorder(tree);
        
        Node mirror = mirrorify(tree);

        // Print inorder traversal of the mirror tree
        System.out.print("\nInorder of mirror tree: ");
        inorder(mirror);
    }
}
