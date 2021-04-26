package implementations;

import interfaces.BinaryTree;
import interfaces.TreeIterator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class ScapegoatTree<T> implements BinaryTree<T> {
    static double DEFAULT_BALANCE_COEFFICIENT = 0.6667f;

    BinaryTreeNode<T> head;
    double balanceCoefficient = DEFAULT_BALANCE_COEFFICIENT;
    int currentNumberOfNodes = 0;

    ScapegoatTree(){
        head = null;
    }
    ScapegoatTree(BinaryTreeNode<T> node){
        head = node;
    }
    ScapegoatTree(double balanceCoefficient){
        this.balanceCoefficient = balanceCoefficient;
        head = null;
    }
    ScapegoatTree(double balanceCoefficient, BinaryTreeNode<T> node){
        this.balanceCoefficient = balanceCoefficient;
        head = node;
    }

    public void insert(T value) {
        BinaryTreeNode<T> tempNode = new BinaryTreeNode<T>(value);
        insert(tempNode);
    }

    public void insert(BinaryTreeNode<T> nodeToInsert) {
        if(nodeToInsert == null){
            return;
        }

        currentNumberOfNodes++;

        if (isEmpty()){
            head = nodeToInsert;
            return;
        }

        Stack<BinaryTreeNode<T>> nodeStack = new Stack<BinaryTreeNode<T>>();

        // TODO: Разбить метод на подметоды!
        // Базовый алгоритм вставки в бинарное дерево:
        BinaryTreeNode<T> currentNode = head;
        nodeStack.push(currentNode);

        while(true){
            if(nodeToInsert.equals(currentNode)){
                return;  // Текущая реализация НЕ ДОПУСКАЕТ наличия двух элементов с одинаковым значением!
            } else if (nodeToInsert.lowerThan(currentNode)){
                // LOWER:
                if(currentNode.hasLeft()){
                    currentNode = currentNode.getLeft();
                } else{
                    currentNode.setLeft(nodeToInsert);
                    break;
                }
            } else {
                // GREATER:
                if(currentNode.hasRight()){
                    currentNode = currentNode.getRight();
                } else{
                    currentNode.setRight(nodeToInsert);
                    break;
                }
            }

            nodeStack.push(currentNode);
        }

        double currentMaxDepth = calculateCurrentMaxDepth();

        if (nodeStack.size() > currentMaxDepth){
            // Поиск "козла отпущения", оптимизация получения размеров узла (мы ищем размер только sibling-а):
            BinaryTreeNode<T> currentScapegoat = nodeToInsert;
            BinaryTreeNode<T> currentScapegoatParent = nodeStack.peek();
            int currentScapegoatSize = currentScapegoat.getSize(),
                currentScapegoatParentSize = currentScapegoatParent.getOtherChildSize(currentScapegoat) + currentScapegoatSize + 1;
            double criticalScapegoatSize;

            do{
                currentScapegoat = nodeStack.pop();
                currentScapegoatParent = nodeStack.peek();

                currentScapegoatSize = currentScapegoatParentSize;
                currentScapegoatParentSize = currentScapegoatParent.getOtherChildSize(currentScapegoat) + currentScapegoatSize + 1;
                criticalScapegoatSize = balanceCoefficient * currentScapegoatParentSize;
            } while(currentScapegoatSize <= criticalScapegoatSize);

            // Перестройка дерева:
            rebuildTreeRootedAt(currentScapegoatParent);
        }
    }

    private double calculateCurrentMaxDepth(){
        double num = Math.log(currentNumberOfNodes);
        double den = Math.log(1/balanceCoefficient);
        double result = num/den;
        return result;
    }

    private class ValuesArrayAndRootIndexCombo {
        T[] values;
        int indexOfRoot;
    }

    private void rebuildTreeRootedAt(BinaryTreeNode<T> root){
        ScapegoatTree<T> newTree = new ScapegoatTree<T>(balanceCoefficient);

        T valueOfRoot = root.getValue();
        ValuesArrayAndRootIndexCombo sortedNodesAndIndexOfRoot = getArrayOfSortedValuesAndIndexOfValueInIt(valueOfRoot);

        newTree.insertFromSortedArrayRootedAt(sortedNodesAndIndexOfRoot);

        head = newTree.head;
    }

    private ValuesArrayAndRootIndexCombo getArrayOfSortedValuesAndIndexOfValueInIt(T value){
        ValuesArrayAndRootIndexCombo valuesArrayAndRootIndexCombo = new ValuesArrayAndRootIndexCombo();

        //noinspection unchecked
        valuesArrayAndRootIndexCombo.values = (T[]) Array.newInstance(Object.class, currentNumberOfNodes);

        TreeIterator<T> treeIterator = iterator();

        int index = 0;
        while(treeIterator.hasNext()){
            T currentValue = treeIterator.getNext();
            valuesArrayAndRootIndexCombo.values[index] = currentValue;
            if(value.equals(currentValue)){
                valuesArrayAndRootIndexCombo.indexOfRoot = index;
            }
            index++;
        }

        return valuesArrayAndRootIndexCombo;
    }

    private void insertFromSortedArrayRootedAt(ValuesArrayAndRootIndexCombo sortedValuesArrayAndRootIndexCombo){
        T[] sortedValues = sortedValuesArrayAndRootIndexCombo.values;
        int indexOfRoot = sortedValuesArrayAndRootIndexCombo.indexOfRoot;

        int sizeOfNodesList = sortedValues.length;

        insert(sortedValues[indexOfRoot]);

        insertFromSortedArray(sortedValues, 0, indexOfRoot - 1);
        insertFromSortedArray(sortedValues, indexOfRoot + 1, sizeOfNodesList - 1);
    }

    private void insertFromSortedArray(T[] sortedValues, int startIndex, int endIndex){
        if(endIndex < startIndex){
            return;
        }
        int centerIndex = (endIndex - startIndex)/2 + startIndex;
        T centerValue = sortedValues[centerIndex];

        noRebuildInsert(centerValue);

        insertFromSortedArray(sortedValues, startIndex, centerIndex - 1);
        insertFromSortedArray(sortedValues, centerIndex + 1, endIndex);
    }

    private void noRebuildInsert(T value){
        BinaryTreeNode<T> tempNode = new BinaryTreeNode<T>(value);
        noRebuildInsert(tempNode);
    }

    private void noRebuildInsert(BinaryTreeNode<T> nodeToInsert){
        if(nodeToInsert == null){
            return;
        }

        if (isEmpty()){
            head = nodeToInsert;
            return;
        }

        // TODO: Разбить метод на подметоды!
        // Базовый алгоритм вставки в бинарное дерево:
        BinaryTreeNode<T> currentNode = head;

        while(true){
            if(nodeToInsert.equals(currentNode)){
                return;  // Текущая реализация НЕ ДОПУСКАЕТ наличия двух элементов с одинаковым значением!
            } else if (nodeToInsert.lowerThan(currentNode)){
                // LOWER:
                if(currentNode.hasLeft()){
                    currentNode = currentNode.getLeft();
                } else{
                    currentNode.setLeft(nodeToInsert);
                    break;
                }
            } else {
                // GREATER:
                if(currentNode.hasRight()){
                    currentNode = currentNode.getRight();
                } else{
                    currentNode.setRight(nodeToInsert);
                    break;
                }
            }
        }
    }

    public BinaryTreeNode<T> findNodeByValue(T value) {
        return null;
    }

    public ArrayList<T> findNodesLowerThan(T value) {
        return null;
    }

    public ArrayList<T> findNodesGreaterThan(T value) {
        return null;
    }

    public ArrayList<T> findNodesInRange(T from, T to) {
        return null;
    }

    public BinaryTreeNode<T> findLowest() {
        return null;
    }

    public BinaryTreeNode<T> findGreatest() {
        return null;
    }

    public TreeIterator<T> iterator() {
        return new BinaryTreeIterator<T>(head);
    }

    public boolean isEmpty(){
        return head == null;
    }
}
