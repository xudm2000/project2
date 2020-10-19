// --== CS400 File Header Information ==--
// Name: Deming Xu
// Email: dxu227@wisc.edu
// Team: CG
// Role: Test Engineer
// TA: Yeping Wang
// Lecturer: Gary Dahl
// Notes to Grader: None

/**
 * Red Black Tree class with additional features.
 *
 * @param <T>
 * @author Deming Xu
 */
public class RBTAddition<T extends Comparable<T>> extends RedBlackTree<T> {

    /**
     * Find the size of the red black tree
     *
     * @return size of the Red black tree
     */
    public int size() {
        // Count the total number of nodes in the tree
        return count(node -> 1);
    }

    /**
     * Get the target node with finder
     *
     * @param f
     * @return target node or null if not found
     */
    public T get(Finder<T> f) {
        // If the root is null, return null
        if (root == null) {
            return null;
        }

        // Find the node by passing a finder f
        return PreOrderTraversalFinderHelper(this.root, f);
    }

    /**
     * Count the number of nodes with traverser
     *
     * @param t
     * @return total count of nodes based on visit method implementation
     */
    public int count(Traverser<T> t) {
        // If the root is null, return 0
        if (root == null) {
            return 0;
        }

        // Count the total number of nodes by passing a traverser t
        return PreOrderTraversalCountHelper(this.root, t);
    }

    /**
     * Finder Interface
     *
     * @param <T> node data type
     */
    public interface Finder<T> {

        /**
         * Method to visit the node
         *
         * @param node
         */
        public boolean check(Node<T> node);
    }

    /**
     * Pre-order traversal helper, pass the finder that help to determine whether the node is target.
     * If the node is the target, return it. Otherwise, return null.
     *
     * @param n
     * @param f
     * @return target node if found. Return null if not found
     */
    private T PreOrderTraversalFinderHelper(Node<T> n, Finder<T> f) {
        // Check if the node is null
        if (n != null) {
            // check the data with Finder, and return it if the check method return true.
            if (f.check(n)) {
                return n.data;
            }

            // Check the data in the left node
            T left = PreOrderTraversalFinderHelper(n.leftChild, f);
            if (left != null) {
                return left;
            }

            // check the data in the right node
            T right = PreOrderTraversalFinderHelper(n.rightChild, f);
            if (right != null) {
                return right;
            }
        }

        // return null if no node is found
        return null;
    }

    /**
     * Traverser Interface
     *
     * @param <T> node data type
     */
    public interface Traverser<T> {

        /**
         * Method to visit the node
         *
         * @param node
         */
        public int visit(Node<T> node);
    }

    /**
     * Pre-order traversal helper, it will return the number of nodes that satisfy the Traverser's requirements
     * For example, if the visit method in the Traversal is to check whether the node's data (If node's data is integer)
     * is greater than 10, it will return the total number of nodes that their data is greater than 10.
     *
     * @param n
     * @param t
     * @return number of nodes that meets the Traverser's requirements
     */
    private int PreOrderTraversalCountHelper(Node<T> n, Traverser<T> t) {
        // Check if the node is null
        if (n != null) {
            // Count the total number of nodes that meets the traverser requirements
            return t.visit(n) + PreOrderTraversalCountHelper(n.leftChild, t) + PreOrderTraversalCountHelper(n.rightChild, t);
        } else {
            // return 0 if the node is null
            return 0;
        }
    }
}
