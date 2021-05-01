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
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(random.nextInt());
        }

        assertEquals(100, scapegoatTree.size());
    }

    @Test
    void unbalancedInsert() {
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(i);
        }

        assertEquals(100, scapegoatTree.size());
    }

    @Test
    void remove() {
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();
        Vector<Integer> addedElements = new Vector<>();
        Vector<Integer> removedElements = new Vector<>();

        while(addedElements.size() < 1000){
            int generatedValue = Math.abs(random.nextInt()) % 20001 - 10000;
            if(addedElements.contains(generatedValue)){
                continue;
            }
            addedElements.add(generatedValue);
        }

        for (Integer value : addedElements) {
            scapegoatTree.insert(value);
        }

        // Первоначальная проверка вставки:
        for (Integer value : addedElements) {
            assertTrue(scapegoatTree.contains(value));
        }

        // Удаляем по одному элементу:
        for(int i = 0; i < 100; i++){
            Integer valueToRemove = addedElements.get(i);
            removedElements.add(valueToRemove);
            scapegoatTree.remove(valueToRemove);
            for (Integer value : addedElements) {
                if(removedElements.contains(value)){
                    assertFalse(scapegoatTree.contains(value));
                } else {
                    assertTrue(scapegoatTree.contains(value));
                }
            }
        }

        assertEquals(900, scapegoatTree.size());
    }

    @Test
    void contains(){
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 10; i++) {
            scapegoatTree.insert(i);
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(scapegoatTree.contains(i));
        }
        for (int i = -99; i < 0; i++) {
            assertFalse(scapegoatTree.contains(i));
        }
        for (int i = 10; i < 100; i++) {
            assertFalse(scapegoatTree.contains(i));
        }
    }

    @Test
    void getValuesLowerThan() {
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>(0.5);

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
    void getValuesGreaterThan() {
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
    void getValuesInRange() {
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