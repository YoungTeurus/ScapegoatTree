package implementations.treeCreators;

import implementations.ScapegoatTree;
import interfaces.BinaryTree;

public class IntegerScapegoatTreeCreatorFromFile extends IntegerBinaryTreeCreatorFromFile {
    protected final BinaryTree<Integer> createTree(){
        return new ScapegoatTree<>();
    }
}
