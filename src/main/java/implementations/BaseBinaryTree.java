package implementations;

import interfaces.BinaryTree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

class BaseBinaryTree<T extends Comparable<T>> implements BinaryTree<T> {
    protected BinaryTreeNode<T> head;

    BaseBinaryTree(){
        head = null;
    }
    BaseBinaryTree(BinaryTreeNode<T> head){
        this.head = head;
    }

    @Override
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
            T valueOfNodeToInsert = nodeToInsert.getValue();

            insertStack = getSearchStackForValue(valueOfNodeToInsert);
            BinaryTreeNode<T> nodeToAppend = insertStack.peek();
            T valueOfNodeToAppend = nodeToAppend.getValue();
            if(valueOfNodeToInsert.compareTo(valueOfNodeToAppend) <= 0){
                nodeToAppend.setLeft(nodeToInsert);
            } else {
                nodeToAppend.setRight(nodeToInsert);
            }
        }

        actionAfterInsert(nodeToInsert, insertStack);
    }

    // Метод-хук для переопределения в классах-потомках.
    protected void actionAfterInsert(BinaryTreeNode<T> insertedNode, Stack<BinaryTreeNode<T>> insertStack) {

    }

    @Override
    public boolean contains(T value) {
        return findNodeByValue(value) != null;
    }

    @Override
    public void remove(T valueToRemove) {
        Stack<BinaryTreeNode<T>> searchStack = getSearchStackForValue(valueToRemove);
        if (!isStackTopNodeValueEqualsTo(searchStack, valueToRemove)){
            return;
        }

        BinaryTreeNode<T> nodeToRemove = searchStack.pop();
        BinaryTreeNode<T> parentNodeOfNodeToRemove;

        boolean isNodeToRemoveIsHead = searchStack.size() == 0;

        if(isNodeToRemoveIsHead){
            parentNodeOfNodeToRemove = null;
        } else {
            parentNodeOfNodeToRemove = searchStack.pop();
        }

        remove(parentNodeOfNodeToRemove, nodeToRemove);

        actionAfterRemove();
    }

    protected void actionAfterRemove(){

    }

    private void remove(BinaryTreeNode<T> parentNode, BinaryTreeNode<T> nodeToRemove){
        boolean isNodeToRemoveIsHead = parentNode == null;

        if(!nodeToRemove.hasLeft()){
            BinaryTreeNode<T> rightChildOfNodeToRemove = nodeToRemove.getRight();
            if(!isNodeToRemoveIsHead){
                parentNode.replaceChild(nodeToRemove, rightChildOfNodeToRemove);
            } else{
                head = rightChildOfNodeToRemove;
            }
        } else if (!nodeToRemove.hasRight()){
            BinaryTreeNode<T> leftChildOfNodeToRemove = nodeToRemove.getLeft();
            if(!isNodeToRemoveIsHead){
                parentNode.replaceChild(nodeToRemove, leftChildOfNodeToRemove);
            } else{
                head = leftChildOfNodeToRemove;
            }
        } else{
            BinaryTreeNode<T>.NodeWithParent firstGreaterChildOfNodeToRemoveWithParent =
                    nodeToRemove.getLowestFirstGreaterChildWithParent();
            BinaryTreeNode<T> firstGreaterChildNode = firstGreaterChildOfNodeToRemoveWithParent.node;
            BinaryTreeNode<T> firstGreaterChildParentNode = firstGreaterChildOfNodeToRemoveWithParent.parent;
            T valueOfFirstGreaterChild = firstGreaterChildNode.getValue();

            remove(firstGreaterChildParentNode, firstGreaterChildNode);

            nodeToRemove.setValue(valueOfFirstGreaterChild);
        }
    }

    private boolean isStackTopNodeValueEqualsTo(Stack<BinaryTreeNode<T>> searchStack, T value){
        if(searchStack.isEmpty()){
            return false;
        }
        BinaryTreeNode<T> stackTop = searchStack.peek();
        T valueOfStackTop = stackTop.getValue();
        return valueOfStackTop.equals(value);
    }

    @Override
    public BinaryTreeNode<T> findNodeByValue(T valueToFind) {
        Stack<BinaryTreeNode<T>> searchStack = getSearchStackForValue(valueToFind);

        if(isStackTopNodeValueEqualsTo(searchStack, valueToFind)){
            return searchStack.peek();
        }
        return null;
    }

    /**
     * Метод осуществляет поиск элемента со значением valueToFind по дереву, ведя trace поиска.
     * Если элемент был найден, он будет находится на вершине стека.
     * Если элемент не был найден, на вершине стека будет тот элемент, в который можно вставить valueToFind.
     * @param valueToFind Значение для поиска.
     * @return Стек trace поиска.
     */
    // TODO: вернуть модификатор доступа private!
    Stack<BinaryTreeNode<T>> getSearchStackForValue(@NotNull T valueToFind){
        Stack<BinaryTreeNode<T>> searchStack = new Stack<>();

        if(isEmpty()){
            return searchStack;
        }

        BinaryTreeNode<T> currentNode = head;
        T valueOfCurrentNode;

        while(true){
            searchStack.push(currentNode);
            valueOfCurrentNode = currentNode.getValue();
            if (valueToFind.compareTo(valueOfCurrentNode) == 0){
                break;
            } else if (valueToFind.compareTo(valueOfCurrentNode) < 0){
                // LOWER:
                if(currentNode.hasLeft()){
                    currentNode = currentNode.getLeft();
                } else{
                    break;
                }
            } else {
                // GREATER:
                if(currentNode.hasRight()){
                    currentNode = currentNode.getRight();
                } else{
                    break;
                }
            }
        }
        return searchStack;
    }

    @Override
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

    @Override
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

    @Override
    public List<T> getValuesInRange(@NotNull T from, @NotNull T to) {
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

    @Override
    public T findLowest() {
        Iterator<T> iterator = iterator();
        if(iterator.hasNext()){
            return iterator.next();
        }
        return null;
    }

    @Override
    public T findGreatest() {
        BinaryTreeNode<T> theMostRightNode = getTheMostRightNode();
        if(theMostRightNode == null){
            return null;
        }
        return theMostRightNode.getValue();
    }

    @Nullable
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

    @Override
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
