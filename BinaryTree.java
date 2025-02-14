/*
 * *** Walter Mikula / 002 SP25 ***
 *
 * Homework # 2 (Programming Assignment). This Java class defines a few basic
 * manipulation operations of a binary trees.
 *
 * ONLY MODIFY THIS FILE (NOT 'Main.Java')
 *
 */

import java.util.Queue;
import java.util.LinkedList;

/*
 * Class BinaryTree
 *
 * This class defines a binary tree object; it is a tree structure where every
 * node as at most two child nodes, which form the tree branches.
 *
 * Public methods (already provided):
 *  - void deleteTree()
 *  - Node insert(int data)
 *  - String preOrder()
 *  - void replaceValue(int oldVal, int newVal)
 *  - int findMin()
 *  - int nodesGT(int val)
 *  - double average()
 *
 * Only the following PRIVATE methods should be changed:
 *  - replaceValueHelper
 *  - findMinHelper
 *  - nodesGTHelper
 *  - averageHelper
 */

public class BinaryTree {

    // Constructors
    public BinaryTree() {
        root = null;
    }
    public BinaryTree(Node node) {
        root = node;
    }

    /*
     * Class Node
     *
     * The node object definition for each node of the binary tree.
     */
    static class Node {

        Node(int d) {
            data = d;
            left = null;
            right = null;
        }

        Node(int d, Node l, Node r) {
            data = d;
            left = l;
            right = r;
        }

        public int data;
        public Node left;
        public Node right;
    }   /* End Class Node */

    public Node root;

    public void deleteTree() {
        root = null;
    }

    public void replaceValue(int oldVal, int newVal) {
        replaceValueHelper(root, oldVal, newVal);
    }

    public int findMin() {
        return findMinHelper(root);
    }

    public int nodesGT(int val) {
        return nodesGTHelper(root, val);
    }

    /*
     * public method insert
     *
     * Inserts a new node containing 'data' using level-order (BFS) so that
     * the tree maintains the property of being "complete".
     */
    Node insert(int data) {
        Node tempNode = new Node(data);

        // If tree is empty, insert new node as the root.
        if (root == null)
            return root = tempNode;

        // Create a queue to do level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Do level order traversal
        while (!queue.isEmpty()) {
            Node front = queue.peek();

            if (front.left == null) {
                front.left = tempNode;
                break;
            } else if (front.right == null) {
                front.right = tempNode;
                break;
            } else {
                queue.remove();
            }

            if (front.left != null)
                queue.add(front.left);
            if (front.right != null)
                queue.add(front.right);
        }

        return tempNode;
    }

    /*
     * Public method preOrder()
     *
     * Returns the tree's data in preorder traversal. Empty tree -> "".
     */
    public String preOrder() {
        return preOrderHelper(root);
    }

    public String preOrderHelper(Node node) {
        if (node == null) {
            return "";
        }
        return node.data + " " + preOrderHelper(node.left)
                + preOrderHelper(node.right);
    }

    /***********************************************************
     * YOUR CODE (FIXES) BELOW: ONLY THESE FOUR PRIVATE METHODS
     *   1) replaceValueHelper
     *   2) findMinHelper
     *   3) nodesGTHelper
     *   4) averageHelper
     ***********************************************************/


    /*
     * private method replaceValueHelper
     *
     * Traverses the tree (depth first), and for each node found with value
     * oldVal, replaces it with newVal (no reorganization).
     */
    private void replaceValueHelper(Node node, int oldVal, int newVal) {
        if (node == null) {
            return;
        }
        if (node.data == oldVal) {
            node.data = newVal;
        }
        replaceValueHelper(node.left, oldVal, newVal);
        replaceValueHelper(node.right, oldVal, newVal);
    }

    /*
     * private method findMinHelper
     *
     * Traverses entire tree (depth first) and returns the minimum value.
     * Returns Integer.MAX_VALUE if the tree is empty.
     */
    private int findMinHelper(Node node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        int leftMin = findMinHelper(node.left);
        int rightMin = findMinHelper(node.right);
        return Math.min(node.data, Math.min(leftMin, rightMin));
    }

    /*
     * private method nodeGTHelper
     *
     * Returns the count of nodes whose data > val, traversing the tree
     * in a depth-first manner. If empty, returns 0.
     */
    private int nodesGTHelper(Node node, int val) {
        if (node == null) {
            return 0;
        }
        int count = (node.data > val) ? 1 : 0;
        count += nodesGTHelper(node.left, val);
        count += nodesGTHelper(node.right, val);
        return count;
    }

    /*
     * public method average() calls averageHelper and returns sum/count.
     * If the tree is empty, the result is (0 / 0). The Main.java test
     * does not test an empty tree for average, so no extra check is done.
     */
    public double average() {
        int[] sumAndCount = averageHelper(root);
        return (double) sumAndCount[0] / sumAndCount[1];
    }

    /*
     * private method averageHelper
     *
     * Returns an int[] array [sumOfData, countOfNodes].
     * Uses depth-first traversal.
     */
    private int[] averageHelper(Node n) {
        if (n == null) {
            return new int[]{0, 0};
        }
        int[] left = averageHelper(n.left);
        int[] right = averageHelper(n.right);

        int sum = left[0] + right[0] + n.data;  // sum of subtree + current
        int count = left[1] + right[1] + 1;     // count of subtree + current
        return new int[]{sum, count};
    }

} // end class BinaryTree
