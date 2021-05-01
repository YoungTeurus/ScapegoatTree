package implementations;

import java.util.Stack;

public class ScapegoatTree<T extends Comparable<T>> extends BaseBinaryTree<T> {
    static double DEFAULT_BALANCE_COEFFICIENT = 0.6667f;

    double balanceCoefficient = DEFAULT_BALANCE_COEFFICIENT;
    int maxNumberOfNodes = 0;
    int currentNumberOfNodes = 0;

    ScapegoatTree(){
        super();
    }
    ScapegoatTree(BinaryTreeNode<T> head){
        super(head);
        maxNumberOfNodes = currentNumberOfNodes = head.getSize();
    }
    ScapegoatTree(double balanceCoefficient){
        super();
        checkAndSetBalanceCoefficient(balanceCoefficient);
    }
    ScapegoatTree(double balanceCoefficient, BinaryTreeNode<T> head){
        super(head);
        checkAndSetBalanceCoefficient(balanceCoefficient);
        maxNumberOfNodes = currentNumberOfNodes = head.getSize();
    }

    private void checkAndSetBalanceCoefficient(double balanceCoefficient){
        if(balanceCoefficient < 0.5 || balanceCoefficient >= 1){
            throw new RuntimeException("ScapegoatTree::checkAndSetBalanceCoefficient: balanceCoefficient не находится в интервале [0.5;1)!");
        }
        this.balanceCoefficient = balanceCoefficient;
    }

    @Override
    protected final void actionAfterInsert(BinaryTreeNode<T> insertedNode, Stack<BinaryTreeNode<T>> insertStack) {
        currentNumberOfNodes++;
        maxNumberOfNodes = Math.max(maxNumberOfNodes, currentNumberOfNodes);
        checkDepthOfInsertStackAndRebalanceTreeIfNeeded(insertedNode, insertStack);
    }

    private void checkDepthOfInsertStackAndRebalanceTreeIfNeeded(BinaryTreeNode<T> insertedNode, Stack<BinaryTreeNode<T>> insertStack){
        double currentMaxDepth = calculateCurrentMaxDepth();

        if(insertStack.size() > currentMaxDepth){
            findScapegoatNodeAndRebuildIt(insertedNode, insertStack);
        }
    }

    private double calculateCurrentMaxDepth(){
        double num = Math.log(currentNumberOfNodes);
        double den = Math.log(1/balanceCoefficient);
        return num/den;
    }

    private void findScapegoatNodeAndRebuildIt(BinaryTreeNode<T> insertedNode, Stack<BinaryTreeNode<T>> insertStack){
        BinaryTreeNode<T> currentScapegoat = insertedNode;
        BinaryTreeNode<T> currentScapegoatParent = insertStack.peek();
        int currentScapegoatSize = currentScapegoat.getSize(),
            currentScapegoatParentSize = currentScapegoatParent.getOtherChildSize(currentScapegoat) + currentScapegoatSize + 1;
        double criticalScapegoatSize = balanceCoefficient * currentScapegoatParentSize;

        while(currentScapegoatSize <= criticalScapegoatSize){
            currentScapegoat = insertStack.pop();
            currentScapegoatParent = insertStack.peek();

            currentScapegoatSize = currentScapegoatParentSize;
            currentScapegoatParentSize = currentScapegoatParent.getOtherChildSize(currentScapegoat) + currentScapegoatSize + 1;
            criticalScapegoatSize = balanceCoefficient * currentScapegoatParentSize;
        }

        // Будем балансировать не сам найденный scapegoat, а его родителя (он также подходит под условие scapegoat).
        // Если это не сделать, мы никогда не будем менять head дерева, даже когда это нужно.
        currentScapegoat = insertStack.pop();
        if(insertStack.size() > 0){
            currentScapegoatParent = insertStack.peek();
        } else{
            currentScapegoatParent = null;
        }

        rebuildNode(currentScapegoat, currentScapegoatParent);
    }

    private void rebuildNode(BinaryTreeNode<T> headNode, BinaryTreeNode<T> parentNode){
        BaseBinaryTree<T> tempTree = new BaseBinaryTree<>(headNode);

        tempTree.rebalance();
        maxNumberOfNodes = currentNumberOfNodes;

        BinaryTreeNode<T> newHeadNode = tempTree.head;

        if(parentNode == null){
            head = newHeadNode;
        } else {
            parentNode.replaceChild(headNode, newHeadNode);
        }
    }

    @Override
    protected void actionAfterRemove() {
        currentNumberOfNodes--;

        compareCurrentNumberOfNodesToMaxNodesAndRebalanceIfNeeded();
    }

    private void compareCurrentNumberOfNodesToMaxNodesAndRebalanceIfNeeded(){
        double criticalNumberOfNodes = balanceCoefficient * maxNumberOfNodes;
        if(currentNumberOfNodes <= criticalNumberOfNodes){
            rebalance();
        }
    }

    @Override
    public int size() {
        return currentNumberOfNodes;
    }
}
