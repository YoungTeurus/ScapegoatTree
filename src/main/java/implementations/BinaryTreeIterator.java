package implementations;

import interfaces.TreeIterator;

import java.util.Stack;

public class BinaryTreeIterator<T> implements TreeIterator<T> {

    Stack<BinaryTreeNode<T>> nodeStack;

    BinaryTreeIterator(BinaryTreeNode<T> treeTop){
        nodeStack = new Stack<BinaryTreeNode<T>>();

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

    public T getNext() {
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
}
