package implementations.treeCreators;

import interfaces.BinaryTree;
import interfaces.treeCreators.BinaryTreeCreatorFromFile;

import java.util.List;
import java.util.Vector;

public abstract class IntegerBinaryTreeCreatorFromFile extends BinaryTreeCreatorFromFile {
    protected IntegerBinaryTreeCreatorFromFile(){
        super(new StringToIntegerParser());
    }

    public final BinaryTree<Integer> createTreeFromFile(String pathToFile){
        List<String> linesFromFile = readLinesFromFile(pathToFile);
        Vector<Comparable> parsedValues = parseLines(linesFromFile);
        BinaryTree<Integer> binaryTree = createTreeFromValuesVector(parsedValues);
        return binaryTree;
    }

    private BinaryTree<Integer> createTreeFromValuesVector(Vector<Comparable> parsedValues){
        BinaryTree<Integer> binaryTree = createTree();
        for (Comparable value : parsedValues) {
            binaryTree.insert((Integer)value);
        }
        return binaryTree;
    }

    protected abstract BinaryTree<Integer> createTree();
}
