package interfaces;

import implementations.BinaryTreeNode;

import java.util.ArrayList;

public interface BinaryTree<T> {

    void insert(T value);
    void insert(BinaryTreeNode<T> node);

    BinaryTreeNode<T> findNodeByValue(T value);
    ArrayList<T> findNodesLowerThan(T value);
    ArrayList<T> findNodesGreaterThan(T value);
    ArrayList<T> findNodesInRange(T from, T to);
    BinaryTreeNode<T> findLowest();
    BinaryTreeNode<T> findGreatest();

    TreeIterator<T> iterator();

    boolean isEmpty();
}
