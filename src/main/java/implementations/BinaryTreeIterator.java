package implementations;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTreeIterator<T extends Comparable<T>> implements Iterator<T> {

    Stack<BinaryTreeNode<T>> nodeStack;

    BinaryTreeIterator(BinaryTreeNode<T> treeTop){
        nodeStack = new Stack<>();

        pushNodeAndLeftBranchToStack(treeTop);
    }

    private void pushNodeAndLeftBranchToStack(BinaryTreeNode<T> node){
        if(node == null){
            return;
        }
        nodeStack.push(node);

        if(node.hasLeft()){
            BinaryTreeNode<T> leftChild = node.getLeft();
            pushNodeAndLeftBranchToStack(leftChild);
        }
    }

    public T next() {
        BinaryTreeNode<T> nextNode = getNextNode();
        return nextNode.getValue();
    }

    BinaryTreeNode<T> getNextNode(){
        if(!hasNext()){
            throw new RuntimeException("BinaryTreeIterator::getNext : iterator has no next node.");
        }

        BinaryTreeNode<T> currentNode = nodeStack.pop();
        BinaryTreeNode<T> rightChildOfCurrentNode = currentNode.getRight();

        pushNodeAndLeftBranchToStack(rightChildOfCurrentNode);

        return currentNode;
    }

    public boolean hasNext() {
        return !(nodeStack.isEmpty());
    }

    public void remove() {
        throw new NotImplementedException();
    }
}
