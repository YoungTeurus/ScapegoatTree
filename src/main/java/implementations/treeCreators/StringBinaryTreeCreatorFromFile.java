package implementations.treeCreators;

import interfaces.BinaryTree;
import interfaces.treeCreators.BinaryTreeCreatorFromFile;

import java.util.List;
import java.util.Vector;

public abstract class StringBinaryTreeCreatorFromFile extends BinaryTreeCreatorFromFile {
    protected StringBinaryTreeCreatorFromFile(){
        super(new StringToStringParser());
    }

    public final BinaryTree<String> createTreeFromFile(String pathToFile){
        List<String> linesFromFile = readLinesFromFile(pathToFile);
        Vector<Comparable> parsedValues = parseLines(linesFromFile);
        BinaryTree<String> binaryTree = createTreeFromValuesVector(parsedValues);
        return binaryTree;
    }

    private BinaryTree<String> createTreeFromValuesVector(Vector<Comparable> parsedValues){
        BinaryTree<String> binaryTree = createTree();
        for (Comparable value : parsedValues) {
            binaryTree.insert((String)value);
        }
        return binaryTree;
    }

    protected abstract BinaryTree<String> createTree();
}
