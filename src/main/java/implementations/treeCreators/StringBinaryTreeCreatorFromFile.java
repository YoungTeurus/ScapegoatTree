package implementations.treeCreators;

import interfaces.BinaryTree;
import interfaces.treeCreators.BinaryTreeCreatorFromFile;

import java.util.List;
import java.util.Vector;

public abstract class StringBinaryTreeCreatorFromFile extends BinaryTreeCreatorFromFile<String> {
    protected StringBinaryTreeCreatorFromFile(){
        super(new StringToStringParser());
    }

    protected abstract BinaryTree<String> createTree();
}
