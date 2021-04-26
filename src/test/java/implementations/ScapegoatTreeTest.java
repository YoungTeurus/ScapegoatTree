package implementations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScapegoatTreeTest {

    @Test
    void goodBalanceInsert() {
        ScapegoatTree<Integer> tree = new ScapegoatTree<Integer>();

        tree.insert(10);
        tree.insert(5);
        tree.insert(8);
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(9);
        tree.insert(15);
        tree.insert(25);
        tree.insert(20);
    }

    @Test
    void badBalanceInsert(){
        ScapegoatTree<Integer> tree = new ScapegoatTree<Integer>();

        tree.insert(25);
        tree.insert(20);
        tree.insert(15);
        tree.insert(10);
        tree.insert(9);
        tree.insert(8);
        tree.insert(6);
        tree.insert(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
    }
}