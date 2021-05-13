package implementations;

import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    Random random = new Random();

    @Test
    void insert() {
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(random.nextInt());
        }

        assertEquals(100, baseBinaryTree.size());
    }

    @Test
    void unbalancedInsert() {
        BinaryTree<Integer> scapegoatTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(i);
        }

        assertEquals(100, scapegoatTree.size());
    }

    @Test
    void rebalance(){
        SimpleBinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(random.nextInt());
        }

        assertEquals(100, baseBinaryTree.size());
        baseBinaryTree.rebalance();
        assertEquals(100, baseBinaryTree.size());
    }

    @Test
    void remove() {
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();
        Vector<Integer> addedElements = new Vector<>();
        Vector<Integer> removedElements = new Vector<>();

        while(addedElements.size() < 1000){
            int generatedValue = Math.abs(random.nextInt());
            if(addedElements.contains(generatedValue)){
                continue;
            }
            addedElements.add(generatedValue);
        }

        for (Integer value : addedElements) {
            baseBinaryTree.insert(value);
        }

        // Первоначальная проверка вставки:
        for (Integer value : addedElements) {
            assertTrue(baseBinaryTree.contains(value));
        }

        // Удаляем по одному элементу:
        for(int i = 0; i < 100; i++){
            Integer valueToRemove = addedElements.get(i);
            removedElements.add(valueToRemove);
            baseBinaryTree.remove(valueToRemove);
            for (Integer value : addedElements) {
                if(removedElements.contains(value)){
                    assertFalse(baseBinaryTree.contains(value));
                } else {
                    assertTrue(baseBinaryTree.contains(value));
                }
            }
        }

        assertEquals(900, baseBinaryTree.size());
    }

    @Test
    void contains(){
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 10; i++) {
            baseBinaryTree.insert(i);
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(baseBinaryTree.contains(i));
        }
        for (int i = -99; i < 0; i++) {
            assertFalse(baseBinaryTree.contains(i));
        }
        for (int i = 10; i < 100; i++) {
            assertFalse(baseBinaryTree.contains(i));
        }
    }

    @Test
    void getValuesLowerThan() {
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(i);
        }

        List<Integer> lowerValues = baseBinaryTree.getValuesLowerThan(20);
        assertEquals(20, lowerValues.size());

        lowerValues = baseBinaryTree.getValuesLowerThan(120);
        assertEquals(100, lowerValues.size());

        lowerValues = baseBinaryTree.getValuesLowerThan(0);
        assertEquals(0, lowerValues.size());

        lowerValues = baseBinaryTree.getValuesLowerThan(-100);
        assertEquals(0, lowerValues.size());
    }

    @Test
    void getValuesGreaterThan() {
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(i);
        }

        List<Integer> greaterValues = baseBinaryTree.getValuesGreaterThan(80);
        assertEquals(19, greaterValues.size());

        greaterValues = baseBinaryTree.getValuesGreaterThan(-1);
        assertEquals(100, greaterValues.size());

        greaterValues = baseBinaryTree.getValuesGreaterThan(99);
        assertEquals(0, greaterValues.size());

        greaterValues = baseBinaryTree.getValuesGreaterThan(200);
        assertEquals(0, greaterValues.size());
    }

    @Test
    void getValuesInRange() {
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(i);
        }

        List<Integer> rangeValues = baseBinaryTree.getValuesInRange(30, 60);
        assertEquals(31, rangeValues.size());

        rangeValues = baseBinaryTree.getValuesInRange(0, 99);
        assertEquals(100, rangeValues.size());

        rangeValues = baseBinaryTree.getValuesInRange(-100, 200);
        assertEquals(100, rangeValues.size());

        rangeValues = baseBinaryTree.getValuesInRange(150, 200);
        assertEquals(0, rangeValues.size());

        rangeValues = baseBinaryTree.getValuesInRange(-50, -20);
        assertEquals(0, rangeValues.size());

        rangeValues = baseBinaryTree.getValuesInRange(99, 0);
        assertEquals(0, rangeValues.size());

        rangeValues = baseBinaryTree.getValuesInRange(10, 10);
        assertEquals(1, rangeValues.size());
    }

    @Test
    void findLowest() {
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();
        Integer theMinimalValue = -100;

        // Заполняем вектор значениями больше -100, причём -100 стоит посередине.
        Vector<Integer> randomValues = new Vector<>(100);

        for(int i = 0; i < 100; i++){
            if (i == 50){
                randomValues.add(theMinimalValue);
                continue;
            }
            randomValues.add(theMinimalValue + 1 + Math.abs(random.nextInt()) % 200);
        }

        for (Integer value : randomValues) {
            baseBinaryTree.insert(value);
        }

        assertEquals(theMinimalValue, baseBinaryTree.findLowest());
    }

    @Test
    void findGreatest() {
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();
        Integer theMaximumNumber = 100;

        // Заполняем вектор значениями больше -100, причём -100 стоит посередине.
        Vector<Integer> randomValues = new Vector<>(100);

        for(int i = 0; i < 100; i++){
            if (i == 50){
                randomValues.add(theMaximumNumber);
                continue;
            }
            randomValues.add(theMaximumNumber - 1 - Math.abs(random.nextInt()) % 200);
        }

        for (Integer value : randomValues) {
            baseBinaryTree.insert(value);
        }

        assertEquals(theMaximumNumber, baseBinaryTree.findGreatest());
    }

    @Test
    void isEmpty() {
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(i);
        }

        assertEquals(100, baseBinaryTree.size());
        assertFalse(baseBinaryTree.isEmpty());

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.remove(i);
        }

        assertEquals(0, baseBinaryTree.size());
        assertTrue(baseBinaryTree.isEmpty());
    }

    @Test
    void iterator() {
        // Подсчитаем сумму элементов:
        BinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();
        int sum = 0;
        int iteratorSum = 0;

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(i);
            sum += i;
        }

        for (Integer value :
                baseBinaryTree) {
            iteratorSum += value;
        }

        assertEquals(sum, iteratorSum);
    }

    @Test
    void getSearchStackForValue(){
        SimpleBinaryTree<Integer> baseBinaryTree = new SimpleBinaryTree<>();

        for (int i = 0; i < 10; i++) {
            baseBinaryTree.insert(i);
        }

        Stack<BinaryTreeNode<Integer>> stack = baseBinaryTree.getSearchStackForValue(3);
        assertEquals(4, stack.size());
        stack = baseBinaryTree.getSearchStackForValue(-15);
        assertEquals(1, stack.size());
        stack = baseBinaryTree.getSearchStackForValue(0);
        assertEquals(1, stack.size());
    }
}