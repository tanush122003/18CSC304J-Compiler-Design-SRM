import java.util.Scanner;

class TreeNode {
    String value;
    TreeNode left;
    TreeNode right;

    TreeNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class SyntaxTreeTraversal {
    public static int traverseAndCalculate(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return Integer.parseInt(node.value);
        }
        int leftValue = traverseAndCalculate(node.left);
        int rightValue = traverseAndCalculate(node.right);
        switch (node.value) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Invalid operator: " + node.value);
        }
    }

    public static void printTree(TreeNode node, String[][] grid, int row, int col, int width) {
        if (node == null) {
            return;
        }
        grid[row][col] = node.value;
        if (node.left != null) {
            printTree(node.left, grid, row + 1, Math.max(0, col - 2), width);
        }
        if (node.right != null) {
            printTree(node.right, grid, row + 1, Math.min(col + 2, width - 1), width);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the root value:");
        String rootValue = scanner.nextLine();
        TreeNode root = new TreeNode(rootValue);

        System.out.println("Enter the left child value of the root node:");
        String leftChildValue = scanner.nextLine();
        root.left = new TreeNode(leftChildValue);

        System.out.println("Enter the right child value of the root node:");
        String rightChildValue = scanner.nextLine();
        root.right = new TreeNode(rightChildValue);

        System.out.println("Enter the left child value of the right node:");
        String leftGrandChildValue = scanner.nextLine();
        root.right.left = new TreeNode(leftGrandChildValue);

        System.out.println("Enter the right child value of the right node:");
        String rightGrandChildValue = scanner.nextLine();
        root.right.right = new TreeNode(rightGrandChildValue);

        // Create a 2D array to store the tree structure
        String[][] grid = new String[15][15];

        // Calculate the width of the tree
        int height = getHeight(root);
        int width = (1 << height) - 1;

        // Populate the grid with tree structure
        printTree(root, grid, 0, width / 2, width);

        int result = traverseAndCalculate(root);
        System.out.println("Result: " + result);

        // Print the tree structure
        for (String[] row : grid) {
            for (String value : row) {
                System.out.print(value != null ? value : " ");
                System.out.print(" ");
            }
            System.out.println();
        }

        // Perform traversal and calculation
        

        scanner.close(); // Close the scanner
    }

    public static int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}
