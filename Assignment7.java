import java.util.*;
class Main {
    TreeNode root;
    public static void main(String[] args) {
        Main main = new Main();
        int arr[] = {10, 20, 35, 15, 8, 5, 3};
// Insert elements into the BST
        for (int i = 0; i < arr.length; i++) {
            main.insert(arr[i]);
        }
// Delete a node
        System.out.println("Deleting node 15:");
        main.delete(15);
        System.out.println("In-order Traversal After Deletion:");
        main.inorder();
// Search for a node
        System.out.println("Searching for node 10: " + main.search(10));
// Traversals
        System.out.println("In-order Traversal:");
        main.inorder();
        System.out.println("Pre-order Traversal:");
        main.preorder();
        System.out.println("Post-order Traversal:");
        main.postorder();
// Depth of tree
        System.out.println("Depth of the tree: " + main.depth());
// Mirror the tree
        System.out.println("Mirrored Tree:");
        main.mirror_tree();
        System.out.println("In-order Traversal After mirroring:");
        main.inorder();
// Create a copy of the tree
        System.out.println("Copied Tree:");
        main.copy_tree();
// Display parent-child relationships
        main.parent_child();
// Display leaf nodes
        System.out.println("Leaf nodes:");
        main.leafNodes();
// Level-wise traversal
        System.out.println("Level-wise Traversal:");
        main.levelOrderTraversal();
    }
    void insert(int value) {
        root = insertRec(root, value);
    }
    TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        } else {
// Duplicate case: do nothing (ignore duplicate value)
            System.out.println("Duplicate value " + value + " not inserted.");
        }
        return root;
    }
    void inorder() {
        inorderRec(root);
        System.out.println();
    }
    void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }
    void preorder() {
        preorderRec(root);
        System.out.println();
    }
    void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorderRec(root.right);
            preorderRec(root.left);
        }
    }
    void postorder() {
        postorderRec(root);
        System.out.println();
    }
    void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.value + " ");
        }
    }
    // Delete method
    void delete(int key) {
        root = deleteRec(root, key);
    }
    TreeNode deleteRec(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.value) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.value) {
            root.right = deleteRec(root.right, key);
        } else {
// Node with key found
// Case 1: Node has no children
            if (root.left == null && root.right == null) {
                return null;
            }
// Case 2: Node has one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
// Case 3: Node has two children
            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }
    private int minValue(TreeNode root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }
    // Search method
    boolean search(int key) {
        return searchRec(root, key);
    }
    boolean searchRec(TreeNode root, int key) {
        if (root == null) {
            return false;
        }
        if (key == root.value) {
            return true;
        }
        return key < root.value ? searchRec(root.left, key) : searchRec(root.right, key);
    }
    // Depth of the tree
    int depth() {
        return depthRec(root);
    }
    private int depthRec(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = depthRec(root.left);
            int rightDepth = depthRec(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
    // Mirror tree
    void mirror_tree() {
        mirror(root);
    }
    void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }
    // Copy tree
    void copy_tree() {
        TreeNode copiedRoot = copy(root);
        System.out.println("Copied Tree:");
        inorderRec(copiedRoot);
        System.out.println();
    }
    TreeNode copy(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newNode = new TreeNode(root.value);
        newNode.left = copy(root.left);
        newNode.right = copy(root.right);
        return newNode;
    }
    // Parent-child representation
    void parent_child() {
        ArrayList<String> list = new ArrayList<>();
        parentChild(root, list);
        System.out.println("List of Parent-Child Relationships:");
        list.forEach(System.out::println);
    }
    void parentChild(TreeNode root, ArrayList<String> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            list.add("Parent: " + root.value + ", Left Child: " + root.left.value);
        }
        if (root.right != null) {
            list.add("Parent: " + root.value + ", Right Child: " + root.right.value);
        }
        parentChild(root.left, list);
        parentChild(root.right, list);
    }
    // Display leaf nodes
    void leafNodes() {
        leafNodesRec(root);
        System.out.println();
    }
    private void leafNodesRec(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            System.out.print(root.value + " ");
        } else {
            leafNodesRec(root.left);
            leafNodesRec(root.right);
        }
    }
    // Level-order traversal (Breadth-First Traversal)
    void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.value + " ");
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
        System.out.println();
    }
}
class TreeNode {
    int value;
    TreeNode left, right;
    TreeNode(int item) {
        value = item;
        left = right = null;
    }
}
