package implementations.treeCreators;

import implementations.ScapegoatTree;
import interfaces.BinaryTree;

public class StringScapegoatTreeCreatorFromFile extends StringBinaryTreeCreatorFromFile {
    @Override
    protected BinaryTree<String> createTree() {
        return new ScapegoatTree<>();
    }
}
