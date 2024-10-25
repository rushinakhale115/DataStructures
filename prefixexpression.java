import java.util.Stack;

class Node {
	char data;
	Node left, right;

	public Node(char item) {
		data = item;
		left = right = null;
	}
}


class ExpressionTree {

	static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	static Node buildTree(String prefix) {
		Stack<Node> stack = new Stack<Node>();

		// Traverse the prefix expression in reverse order
		for (int i = prefix.length() - 1; i >= 0; i--) {
			char c = prefix.charAt(i);

			if (isOperator(c)) {
				Node left = stack.pop();
				Node right = stack.pop();
				Node node = new Node(c);
				node.left = left;
				node.right = right;
				stack.push(node);
			} else {
				Node node = new Node(c);
				stack.push(node);
			}
		}

		Node root = stack.peek();
		stack.pop();

		return root;
	}

	// Function to print the infix expression for the tree
	static void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node.data + " ");
			inOrder(node.right);
		}
	}


	public static void main(String[] args) {
		String prefix = "*+ab-cd";
		Node root = buildTree(prefix);

		System.out.println("Infix expression is:");
		inOrder(root);

	}


}

