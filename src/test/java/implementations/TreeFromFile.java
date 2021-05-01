package implementations;

import implementations.treeCreators.IntegerScapegoatTreeCreatorFromFile;
import implementations.treeCreators.StringScapegoatTreeCreatorFromFile;
import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeFromFile {
    @Test
    void main(){
        // BinaryTree<Integer> binaryTree = new IntegerScapegoatTreeCreatorFromFile().createTreeFromFile("numbers.txt");
        BinaryTree<String> binaryTree = new StringScapegoatTreeCreatorFromFile().createTreeFromFile("words.txt");
        System.out.printf(binaryTree.toString());
        assertEquals(1000, binaryTree.size());
    }
}
