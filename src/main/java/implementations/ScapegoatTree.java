package implementations;

import interfaces.BinaryTree;
import interfaces.TreeIterator;

import java.util.ArrayList;
import java.util.Stack;

public class ScapegoatTree<T> implements BinaryTree<T> {
    static float DEFAULT_BALANCE_COEFFICIENT = 0.6667f;

    BinaryTreeNode<T> head;
    float balanceCoefficient = DEFAULT_BALANCE_COEFFICIENT;

    ScapegoatTree(){
        head = null;
    }
    ScapegoatTree(BinaryTreeNode<T> node){
        head = node;
    }
    ScapegoatTree(int balanceCoefficient){
        this.balanceCoefficient = balanceCoefficient;
        head = null;
    }
    ScapegoatTree(int balanceCoefficient, BinaryTreeNode<T> node){
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

        return;
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
        return null;
    }

    public boolean isEmpty(){
        return head == null;
    }
}
