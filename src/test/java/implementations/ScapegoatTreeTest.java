package implementations;

import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.Random;

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
}