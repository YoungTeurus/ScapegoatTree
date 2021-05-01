package implementations.treeCreators;

import interfaces.BinaryTree;
import interfaces.treeCreators.BinaryTreeCreatorFromFile;

import java.util.List;
import java.util.Vector;

public abstract class IntegerBinaryTreeCreatorFromFile extends BinaryTreeCreatorFromFile<Integer> {
    protected IntegerBinaryTreeCreatorFromFile(){
        super(new StringToIntegerParser());
    }

    protected abstract BinaryTree<Integer> createTree();
}
