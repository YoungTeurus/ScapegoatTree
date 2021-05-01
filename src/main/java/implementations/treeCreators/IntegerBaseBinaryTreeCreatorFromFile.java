package implementations.treeCreators;

import implementations.BaseBinaryTree;
import interfaces.BinaryTree;

public class IntegerBaseBinaryTreeCreatorFromFile extends IntegerBinaryTreeCreatorFromFile {
    protected final BinaryTree<Integer> createTree(){
        return new BaseBinaryTree<>();
    }
}
