package implementations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeIteratorTest {

    @Test
    void mainTest() {
        //          10
        //        /   \
        //       5     15
        //     /   \     \
        //    2     8     25
        //  / \    / \    /
        // 1   3  6   9  20

        BinaryTreeNode<Integer> _10 = new BinaryTreeNode<Integer>(10);
        BinaryTreeNode<Integer> _5 = new BinaryTreeNode<Integer>(5);
        BinaryTreeNode<Integer> _2 = new BinaryTreeNode<Integer>(2);
        BinaryTreeNode<Integer> _1 = new BinaryTreeNode<Integer>(1);
        BinaryTreeNode<Integer> _3 = new BinaryTreeNode<Integer>(3);
        BinaryTreeNode<Integer> _8 = new BinaryTreeNode<Integer>(8);
        BinaryTreeNode<Integer> _6 = new BinaryTreeNode<Integer>(6);
        BinaryTreeNode<Integer> _9 = new BinaryTreeNode<Integer>(9);
        BinaryTreeNode<Integer> _15 = new BinaryTreeNode<Integer>(15);
        BinaryTreeNode<Integer> _25 = new BinaryTreeNode<Integer>(25);
        BinaryTreeNode<Integer> _20 = new BinaryTreeNode<Integer>(20);

        _10.setLeft(_5).setRight(_15);

        _5.setLeft(_2).setRight(_8);
        _2.setLeft(_1).setRight(_3);
        _8.setLeft(_6).setRight(_9);

        _15.setRight(_25);
        _25.setLeft(_20);

        BinaryTreeIterator<Integer> iterator = new BinaryTreeIterator<Integer>(_10);

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(5, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(6, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(8, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(9, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(15, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(20, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(25, iterator.next());
        assertFalse(iterator.hasNext());
    }
}