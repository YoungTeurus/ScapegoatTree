package implementations;

import interfaces.TreeNode;
import org.jetbrains.annotations.NotNull;


public class BinaryTreeNode<T extends Comparable<T>> implements TreeNode<T>, Comparable<BinaryTreeNode<T>> {
    class NodeWithParent{
        BinaryTreeNode<T> parent;
        BinaryTreeNode<T> node;

        NodeWithParent(BinaryTreeNode<T> parent, BinaryTreeNode<T> node){
            this.parent = parent;
            this.node = node;
        }
    }

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


    public int compareTo(@NotNull BinaryTreeNode<T> other){
        T otherValue = other.value;

        if(value == null || otherValue == null){
            throw new RuntimeException("BinaryTreeNode::compare : Can't compare elements without realization of Comparable.");
        }

        return value.compareTo(otherValue);
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    NodeWithParent getLowestFirstGreaterChildWithParent(){
        if(!hasRight()){
            throw new RuntimeException("BinaryTreeNode::getFirstGreaterChild: у node нет правого потомка!");
        }
        BinaryTreeNode<T> rightChild = getRight();
        NodeWithParent nodeWithParent = rightChild.getNodeWithLowestValueAndItsParent();

        // Если parent == null, значит сам rightChild является наименьшим.
        if(nodeWithParent.parent == null){
            nodeWithParent.parent = this;
        }
        return nodeWithParent;
    }

    private NodeWithParent getNodeWithLowestValueAndItsParent(){
        BinaryTreeNode<T> currentNode = this;
        BinaryTreeNode<T> parent = null;
        while(currentNode.hasLeft()){
            parent = currentNode;
            currentNode = currentNode.getLeft();
        }
        return new NodeWithParent(parent, currentNode);
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
    void replaceChild(BinaryTreeNode<T> oldChild, BinaryTreeNode<T> newChild){
        if(left == oldChild){
            setLeft(newChild);
        } else if (right == oldChild){
            setRight(newChild);
        } else{
            throw new RuntimeException("BinaryTreeNode::replaceChild: oldChild isn't present in node's childs!");
        }
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public int getSize(){
        int nodeSize = 1;
        if(hasLeft()){
            nodeSize += left.getSize();
        }
        if(hasRight()){
            nodeSize += right.getSize();
        }
        return nodeSize;
    }
    // Метод для оптимизации производительности: на момент выполнения этого метода мы знаем размер одного из потомков Node.
    int getOtherChildSize(BinaryTreeNode<T> firstChild){
        if(firstChild == left && hasRight()){
            return right.getSize();
        } else if (firstChild == right && hasLeft()){
            return left.getSize();
        }
        return 0;
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
