package implementations;

import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ScapegoatTreeTest {

    Random random = new Random();

    @Test
    void insert() {
        BinaryTree<Integer> newScapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            newScapegoatTree.insert(random.nextInt());
        }

        assertEquals(100, newScapegoatTree.size());
    }

    @Test
    void remove() {
    }

    @Test
    void findNodeByValue() {
    }

    @Test
    void findNodesLowerThan() {
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(i);
        }

        List<Integer> lowerValues = scapegoatTree.getValuesLowerThan(20);
        assertEquals(20, lowerValues.size());

        lowerValues = scapegoatTree.getValuesLowerThan(120);
        assertEquals(100, lowerValues.size());

        lowerValues = scapegoatTree.getValuesLowerThan(0);
        assertEquals(0, lowerValues.size());

        lowerValues = scapegoatTree.getValuesLowerThan(-100);
        assertEquals(0, lowerValues.size());
    }

    @Test
    void findNodesGreaterThan() {
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(i);
        }

        List<Integer> greaterValues = scapegoatTree.getValuesGreaterThan(80);
        assertEquals(19, greaterValues.size());

        greaterValues = scapegoatTree.getValuesGreaterThan(-1);
        assertEquals(100, greaterValues.size());

        greaterValues = scapegoatTree.getValuesGreaterThan(99);
        assertEquals(0, greaterValues.size());

        greaterValues = scapegoatTree.getValuesGreaterThan(200);
        assertEquals(0, greaterValues.size());
    }

    @Test
    void findNodesInRange() {
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(i);
        }

        List<Integer> rangeValues = scapegoatTree.getValuesInRange(30, 60);
        assertEquals(31, rangeValues.size());

        rangeValues = scapegoatTree.getValuesInRange(0, 99);
        assertEquals(100, rangeValues.size());

        rangeValues = scapegoatTree.getValuesInRange(-100, 200);
        assertEquals(100, rangeValues.size());

        rangeValues = scapegoatTree.getValuesInRange(150, 200);
        assertEquals(0, rangeValues.size());

        rangeValues = scapegoatTree.getValuesInRange(-50, -20);
        assertEquals(0, rangeValues.size());

        rangeValues = scapegoatTree.getValuesInRange(99, 0);
        assertEquals(0, rangeValues.size());

        rangeValues = scapegoatTree.getValuesInRange(10, 10);
        assertEquals(1, rangeValues.size());
    }

    @Test
    void findLowest() {
        // TODO: ловится ошибка!!!
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();
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
            scapegoatTree.insert(value);
        }

        assertEquals(theMinimalValue, scapegoatTree.findLowest());
    }

    @Test
    void findGreatest() {
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();
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
            scapegoatTree.insert(value);
        }

        assertEquals(theMaximumNumber, scapegoatTree.findGreatest());
    }

    @Test
    void isEmpty() {
    }

    @Test
    void iterator() {
        // Подсчитаем сумму элементов:
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();
        int sum = 0;
        int iteratorSum = 0;

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(i);
            sum += i;
        }

        for (Integer value :
                scapegoatTree) {
            iteratorSum += value;
        }

        assertEquals(sum, iteratorSum);
    }
}