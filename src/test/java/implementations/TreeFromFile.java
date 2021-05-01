package implementations;

import implementations.treeCreators.IntegerBaseBinaryTreeCreatorFromFile;
import implementations.treeCreators.StringScapegoatTreeCreatorFromFile;
import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

public class TreeFromFile {
    @Test
    void main(){
        // BinaryTree<Integer> binaryTree = new IntegerBaseBinaryTreeCreatorFromFile().createTreeFromFile("test.txt");
        BinaryTree<String> binaryTree = new StringScapegoatTreeCreatorFromFile().createTreeFromFile("test.txt");
        System.out.printf(binaryTree.toString());
    }
}
