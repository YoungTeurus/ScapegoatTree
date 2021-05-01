package interfaces;

import implementations.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface BinaryTree<T extends Comparable<T>> extends Iterable<T> {

    void insert(T value);

    void remove(T value);

    boolean contains(T value);
    List<T> getValuesLowerThan(T value);
    List<T> getValuesGreaterThan(T value);
    List<T> getValuesInRange(T from, T to);
    T findLowest();
    T findGreatest();

    int size();

    boolean isEmpty();
}
