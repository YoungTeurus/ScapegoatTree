package implementations;

import interfaces.TreeNode;

public class BinaryTreeNode<T> implements TreeNode<T>, Comparable<BinaryTreeNode<T>> {
    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(){
        value = null;
        left = null;
        right = null;
    }

    public BinaryTreeNode(T value){
        this.value = value;
        left = null;
        right = null;
    }

    public BinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right){
        this.value = value;
        this.left = left;
        this.right = right;
    }


    public int compareTo(BinaryTreeNode<T> other){
        T otherValue = other.value;

        if(!(value instanceof Comparable) || !(otherValue instanceof Comparable)){
            throw new RuntimeException("BinaryTreeNode::compare : Can't compare elements without realization of Comparable.");
        }

        //noinspection unchecked
        return ((Comparable<T>)value).compareTo(otherValue);
    }
    public boolean equals(BinaryTreeNode<T> other){
        return compareTo(other) == 0;
    }
    public boolean lowerThan(BinaryTreeNode<T> other){
        return compareTo(other) < 0;
    }
    public boolean greaterThan(BinaryTreeNode<T> other){
        return compareTo(other) > 0;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public boolean hasLeft(){
        return left != null;
    }
    public boolean hasRight(){
        return right != null;
    }
    public boolean isLeaf(){
        return !(hasLeft() || hasRight());
    }

    public BinaryTreeNode<T> setLeft(BinaryTreeNode<T> node) {
        left = node;
        return this;
    }
    public BinaryTreeNode<T> setRight(BinaryTreeNode<T> node) {
        right = node;
        return this;
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "node{" +
                "v=" + value +
                ", l=" + ((left != null) ? left.value : "null") +
                ", r=" + ((right != null) ? right.value : "null") +
                '}';
    }
}
