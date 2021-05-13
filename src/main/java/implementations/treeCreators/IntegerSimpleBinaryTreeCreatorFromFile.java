package implementations.treeCreators;

import implementations.BaseBinaryTree;
import implementations.SimpleBinaryTree;
import interfaces.BinaryTree;

public class IntegerSimpleBinaryTreeCreatorFromFile extends IntegerBinaryTreeCreatorFromFile {
    protected final BinaryTree<Integer> createTree(){
        return new SimpleBinaryTree<>();
    }
}
