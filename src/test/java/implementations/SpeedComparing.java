package implementations;

import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SpeedComparing {

    Random random = new Random();

    @Test
    void speedRandom(){
        long startTime, elapsedTime;
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            baseBinaryTree.insert(random.nextInt());
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Вставка в обычное бинарное дерево 100000 случайных чисел за " + elapsedTime);

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            scapegoatTree.insert(random.nextInt());
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Вставка в дерево козла отпущения 100000 случайных чисел за " + elapsedTime);

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            baseBinaryTree.contains(random.nextInt());
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Проверка на вхождение (поиск) в обычном бинарном дереве 100000 случайных чисел за " + elapsedTime);

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            scapegoatTree.contains(random.nextInt());
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Проверка на вхождение (поиск) в дереве козла отпущения 100000 случайных чисел за " + elapsedTime);
    }

    @Test
    void speedUnbalanced(){
        long startTime, elapsedTime;
        BinaryTree<Integer> baseBinaryTree = new BaseBinaryTree<>();
        BinaryTree<Integer> scapegoatTree = new ScapegoatTree<>();

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            baseBinaryTree.insert(i);
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Вставка в обычное бинарное дерево 10000 чисел в порядке возрастания за " + elapsedTime);

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            scapegoatTree.insert(i);
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Вставка в дерево козла отпущения 100000 чисел в порядке возрастания за " + elapsedTime);

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            assertTrue(baseBinaryTree.contains(i));
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Проверка на вхождение (поиск) в обычном бинарном дереве 10000 чисел в порядке возрастания за " + elapsedTime);

        startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            assertTrue(scapegoatTree.contains(i));
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Проверка на вхождение (поиск) в дереве козла отпущения 100000 чисел в порядке возрастания за " + elapsedTime);
    }
}
