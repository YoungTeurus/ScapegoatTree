package implementations;

import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class BaseBinaryTreeTest {

    Random random = new Random();

    @Test
    void insert() {
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(random.nextInt());
        }

        assertEquals(100, baseBinaryTree.size());
    }

    @Test
    void rebalance(){
        BaseBinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();

        for (int i = 0; i < 100; i++) {
            baseBinaryTree.insert(random.nextInt());
        }

        assertEquals(100, baseBinaryTree.size());
        baseBinaryTree.rebalance();
        assertEquals(100, baseBinaryTree.size());
    }

    @Test
    void remove() {
    }

    @Test
    void findNodeByValue() {
    }

    @Test
    void findNodesLowerThan() {
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();

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
    void findNodesGreaterThan() {
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();

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
    void findNodesInRange() {
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();

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
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();
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
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();
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
    }

    @Test
    void iterator() {
        // Подсчитаем сумму элементов:
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();
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
}