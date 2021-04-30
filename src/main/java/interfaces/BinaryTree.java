package interfaces;

import implementations.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface BinaryTree<T extends Comparable<T>> extends Iterable<T> {

    void insert(T value);

    void remove(T value);

    BinaryTreeNode<T> findNodeByValue(T value);
    List<T> findNodesLowerThan(T value);
    List<T> findNodesGreaterThan(T value);
    List<T> findNodesInRange(T from, T to);
    T findLowest();
    T findGreatest();

    int size();

    boolean isEmpty();
}
