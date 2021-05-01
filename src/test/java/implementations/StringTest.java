package implementations;

import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringTest {
    Random random = new Random();

    @Test
    void insert() {
        BinaryTree<String> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                stringBuilder.append(random.nextInt(10));
            }
            scapegoatTree.insert(stringBuilder.toString());
        }

        assertEquals(100, scapegoatTree.size());
    }

    @Test
    void remove() {
        BinaryTree<String> scapegoatTree = new ScapegoatTree<>();
        Vector<String> addedElements = new Vector<>();
        Vector<String> removedElements = new Vector<>();

        while(addedElements.size() < 1000){
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                stringBuilder.append(random.nextInt(10));
            }
            String generatedString = stringBuilder.toString();
            if(addedElements.contains(generatedString)){
                continue;
            }
            addedElements.add(generatedString);
        }

        for (String value : addedElements) {
            scapegoatTree.insert(value);
        }

        // Первоначальная проверка вставки:
        for (String value : addedElements) {
            assertTrue(scapegoatTree.contains(value));
        }

        // Удаляем по одному элементу:
        for(int i = 0; i < 100; i++){
            String valueToRemove = addedElements.get(i);
            removedElements.add(valueToRemove);
            scapegoatTree.remove(valueToRemove);
            for (String value : addedElements) {
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
        BinaryTree<String> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 10; i++) {
            scapegoatTree.insert(Integer.toString(i));
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(scapegoatTree.contains(Integer.toString(i)));
        }
        for (int i = -99; i < 0; i++) {
            assertFalse(scapegoatTree.contains(Integer.toString(i)));
        }
        for (int i = 10; i < 100; i++) {
            assertFalse(scapegoatTree.contains(Integer.toString(i)));
        }
    }

    @Test
    void getValuesLowerThan() {
        BinaryTree<String> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(Integer.toString(i));
        }

        List<String> lowerValues = scapegoatTree.getValuesLowerThan(Integer.toString(20));

        lowerValues = scapegoatTree.getValuesLowerThan(Integer.toString(120));

        lowerValues = scapegoatTree.getValuesLowerThan(Integer.toString(0));

        lowerValues = scapegoatTree.getValuesLowerThan(Integer.toString(-100));

    }

    @Test
    void getValuesGreaterThan() {
        BinaryTree<String> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(Integer.toString(i));
        }

        List<String> greaterValues = scapegoatTree.getValuesGreaterThan(Integer.toString(80));

        greaterValues = scapegoatTree.getValuesGreaterThan(Integer.toString(-1));

        greaterValues = scapegoatTree.getValuesGreaterThan(Integer.toString(99));

        greaterValues = scapegoatTree.getValuesGreaterThan(Integer.toString(200));
    }

    @Test
    void getValuesInRange() {
        BinaryTree<String> scapegoatTree = new ScapegoatTree<>();

        for (int i = 0; i < 100; i++) {
            scapegoatTree.insert(Integer.toString(i));
        }

        List<String> rangeValues = scapegoatTree.getValuesInRange(Integer.toString(30), Integer.toString(60));

        rangeValues = scapegoatTree.getValuesInRange(Integer.toString(0), Integer.toString(99));

        rangeValues = scapegoatTree.getValuesInRange(Integer.toString(-100), Integer.toString(200));

        rangeValues = scapegoatTree.getValuesInRange(Integer.toString(150), Integer.toString(200));

        rangeValues = scapegoatTree.getValuesInRange(Integer.toString(-50), Integer.toString(-20));

        rangeValues = scapegoatTree.getValuesInRange(Integer.toString(99), Integer.toString(0));

        rangeValues = scapegoatTree.getValuesInRange(Integer.toString(10), Integer.toString(10));
    }

    @Test
    void findLowest() {
        BinaryTree<String> scapegoatTree = new ScapegoatTree<>();

        for(int i = 0; i < 100; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                stringBuilder.append(random.nextInt(10));
            }
            scapegoatTree.insert(stringBuilder.toString());
        }

        String lowest = scapegoatTree.findLowest();
    }

    @Test
    void findGreatest() {
        BinaryTree<String> scapegoatTree = new ScapegoatTree<>();

        for(int i = 0; i < 100; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                stringBuilder.append(random.nextInt(10));
            }
            scapegoatTree.insert(stringBuilder.toString());
        }

        String greatest = scapegoatTree.findGreatest();
    }
}
