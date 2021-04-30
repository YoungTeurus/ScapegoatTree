package implementations;

import interfaces.BinaryTree;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

class BaseBinaryTree<T extends Comparable<T>> implements BinaryTree<T> {
    protected BinaryTreeNode<T> head;

    BaseBinaryTree(){
        head = null;
    }
    BaseBinaryTree(BinaryTreeNode<T> head){
        this.head = head;
    }

    public void insert(T value) {
        BinaryTreeNode<T> tempNode = new BinaryTreeNode<>(value);
        insert(tempNode);
    }

    public final void insert(BinaryTreeNode<T> nodeToInsert) {
        if(nodeToInsert == null){
            return;
        }

        Stack<BinaryTreeNode<T>> insertStack = new Stack<>();

        if (isEmpty()){
            head = nodeToInsert;
        } else {

            // TODO: Разбить метод на подметоды! Или как-то вынести из if-else
            // Базовый алгоритм вставки в бинарное дерево:
            T valueOfNodeToInsert = nodeToInsert.getValue();
            T valueOfCurrentNode;

            BinaryTreeNode<T> currentNode = head;

            insertStack.push(currentNode);
            while(true){
                valueOfCurrentNode = currentNode.getValue();
                if (valueOfNodeToInsert.compareTo(valueOfCurrentNode) <= 0){
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
                insertStack.push(currentNode);
            }
        }

        actionAfterInsert(nodeToInsert, insertStack);
    }

    // Метод-хук для переопределения в классах-потомках.
    protected void actionAfterInsert(BinaryTreeNode<T> insertedNode, Stack<BinaryTreeNode<T>> insertStack) {

    }

    public void remove(T value) {
        BinaryTreeNode<T> nodeToRemove = findNodeByValue(value);
        throw new NotImplementedException();
    }

    public BinaryTreeNode<T> findNodeByValue(T value) {
        // Stack<BinaryTreeNode<T>> insertStack = new Stack<>();
//
        // if (isEmpty()){
        //     head = nodeToInsert;
        // } else {
//
        //     // Базовый алгоритм вставки в бинарное дерево:
        //     BinaryTreeNode<T> currentNode = head;
//
        //     insertStack.push(currentNode);
        //     while(true){
        //         if(nodeToInsert.equals(currentNode)){
        //             return;  // Текущая реализация НЕ ДОПУСКАЕТ наличия двух элементов с одинаковым значением!
        //         } else if (nodeToInsert.lowerThan(currentNode)){
        //             // LOWER:
        //             if(currentNode.hasLeft()){
        //                 currentNode = currentNode.getLeft();
        //             } else{
        //                 currentNode.setLeft(nodeToInsert);
        //                 break;
        //             }
        //         } else {
        //             // GREATER:
        //             if(currentNode.hasRight()){
        //                 currentNode = currentNode.getRight();
        //             } else{
        //                 currentNode.setRight(nodeToInsert);
        //                 break;
        //             }
        //         }
        //         insertStack.push(currentNode);
        //     }
        // }
        return null;
    }

    private BinaryTreeNode<T> getNextNodeCloserToValue(@NotNull BinaryTreeNode<T> currentNode, T targetValue){
        T valueOfCurrentNode = currentNode.getValue();
        if(valueOfCurrentNode.compareTo(targetValue) > 0){
            return currentNode.getRight();
        } else {
            return currentNode.getLeft();
        }
    }

    public List<T> getValuesLowerThan(T value) {
        Vector<T> sortedValues = createVectorOfSortedValues();

        // Быстрые проверки на граничные ситуации, чтобы сократить время работы.
        if ( sortedValues.get(0).compareTo(value) > 0 ) {
            // Наименьший элемент больше value, значит ни один элемент не меньше value:
            return Collections.emptyList();
        } else if ( sortedValues.get(sortedValues.size() - 1).compareTo(value) < 0 ){
            // Наибольший элемент меньше value, значит все элементы меньше value:
            return sortedValues;
        }

        int indexOfFirstNodeGreaterThanOrEqualsValue = 0;

        for (T _value : sortedValues) {
            if(_value.compareTo(value) >= 0){  // _value >= value
                break;
            }
            indexOfFirstNodeGreaterThanOrEqualsValue++;
        }

        return sortedValues.subList(0, indexOfFirstNodeGreaterThanOrEqualsValue);
    }

    public List<T> getValuesGreaterThan(T value) {
        Vector<T> sortedValues = createVectorOfSortedValues();

        // Быстрые проверки на граничные ситуации, чтобы сократить время работы.
        if ( sortedValues.get(0).compareTo(value) > 0 ) {
            // Наименьший элемент больше value, значит все элементы больше value:
            return sortedValues;
        } else if ( sortedValues.get(sortedValues.size() - 1).compareTo(value) < 0 ){
            // Наибольший элемент меньше value, значит ни один элемент не больше value:
            return Collections.emptyList();
        }

        int indexOfFirstNodeGreaterThanValue = 0;

        for (T _value : sortedValues) {
            if(_value.compareTo(value) > 0){  // _value >= value
                break;
            }
            indexOfFirstNodeGreaterThanValue++;
        }

        return sortedValues.subList(indexOfFirstNodeGreaterThanValue, sortedValues.size());
    }

    public List<T> getValuesInRange(T from, T to) {
        if(from.compareTo(to) > 0){  // from > to
            return Collections.emptyList();
        }

        Vector<T> sortedValues = createVectorOfSortedValues();

        // Быстрые проверки на граничные ситуации, чтобы сократить время работы.
        if ( sortedValues.get(0).compareTo(to) > 0 ) {
            // Наименьший элемент больше to, значит ни один элемент не войдёт в интервал:
            return Collections.emptyList();
        } else if ( sortedValues.get(sortedValues.size() - 1).compareTo(from) < 0 ){
            // Наибольший элемент меньше from, значит ни один элемент не войдёт в интервал:
            return Collections.emptyList();
        } else if ( sortedValues.get(0).compareTo(from) >= 0 && sortedValues.get(sortedValues.size() - 1).compareTo(to) <= 0){
            // Наименьший элемент больше from и наибольший элемент меньше to, значит все значения войдут в интервал:
            return sortedValues;
        }

        int indexOfFirstNodeLowerThanOrEqualsValue = 0;
        int indexOfFirstNodeGreaterThanOrEqualsValue = 0;

        for (T _value : sortedValues) {
            if(_value.compareTo(from) < 0){    // _value < from
                indexOfFirstNodeLowerThanOrEqualsValue++;
                indexOfFirstNodeGreaterThanOrEqualsValue++;
                continue;
            }
            if(_value.compareTo(to) > 0){      // _value >= to
                break;
            }
            indexOfFirstNodeGreaterThanOrEqualsValue++;
        }

        return sortedValues.subList(indexOfFirstNodeLowerThanOrEqualsValue, indexOfFirstNodeGreaterThanOrEqualsValue);
    }

    public T findLowest() {
        Iterator<T> iterator = iterator();
        if(iterator.hasNext()){
            return iterator.next();
        }
        return null;
    }

    public T findGreatest() {
        BinaryTreeNode<T> theMostRightNode = getTheMostRightNode();
        if(theMostRightNode == null){
            return null;
        }
        return theMostRightNode.getValue();
    }

    private BinaryTreeNode<T> getTheMostRightNode(){
        if(isEmpty()){
            return null;
        }
        BinaryTreeNode<T> currentNode = head;
        while (currentNode.hasRight()){
            currentNode = currentNode.getRight();
        }
        return currentNode;
    }

    @Override
    public int size() {
        if(isEmpty()){
            return 0;
        }
        return head.getSize();
    }

    public boolean isEmpty() {
        return head == null;
    }

    @NotNull
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<>(head);
    }

    protected void rebalance(){
        Vector<T> sortedValues = createVectorOfSortedValues();
        makeEmpty();
        insertFromSortedVector(sortedValues, 0, sortedValues.size() - 1);
    }

    private Vector<T> createVectorOfSortedValues(){
        int sizeOfTree = size();

        Vector<T> sortedValues = new Vector<>(sizeOfTree);
        for (T value : this) {
            sortedValues.add(value);
        }

        return sortedValues;
    }

    private void makeEmpty(){
        head = null;
    }

    private void insertFromSortedVector(Vector<T> sortedValues, int startIndex, int endIndex){
        if(endIndex < startIndex){
            return;
        }
        int centerIndex = (endIndex - startIndex)/2 + startIndex;
        T centerValue = sortedValues.get(centerIndex);

        insert(centerValue);

        insertFromSortedVector(sortedValues, startIndex, centerIndex - 1);
        insertFromSortedVector(sortedValues, centerIndex + 1, endIndex);
    }
}
