package implementations;

import interfaces.BinaryTree;

import java.lang.reflect.Array;
import java.util.*;

public class ScapegoatTree<T extends Comparable<T>> implements BinaryTree<T> {
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

    private void rebuildTree(){
        int sizeOfTree = head.getSize();

        T[] sortedValues = (T[]) new Object[sizeOfTree];

        int i = 0;
        for (T value : this) {
            sortedValues[i++] = value;
        }

        head = null;

        insertFromSortedArray(sortedValues, 0, sizeOfTree - 1);
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

    public Iterator<T> iterator() {
        return new BinaryTreeIterator<T>(head);
    }

    public boolean isEmpty(){
        return head == null;
    }
}
