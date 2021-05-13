package implementations.treeCreators;

import implementations.ScapegoatTree;
import interfaces.BinaryTree;
import interfaces.treeCreators.BinaryTreeCreatorFromFile;
import interfaces.treeCreators.StringParser;

public class ScapegoatTreeCreator<T extends Comparable<T>> extends BinaryTreeCreatorFromFile<T> {

    public ScapegoatTreeCreator(StringParser<T> stringParser){
        super(stringParser);
    }

    @Override
    protected BinaryTree<T> createTree() {
        return new ScapegoatTree<T>();
    }
}
