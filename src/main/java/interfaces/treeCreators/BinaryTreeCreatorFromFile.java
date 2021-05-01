package interfaces.treeCreators;

import interfaces.BinaryTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public abstract class BinaryTreeCreatorFromFile<T extends Comparable<T>> {
    StringParser<T> stringParser;

    protected BinaryTreeCreatorFromFile(StringParser<T> parser){
        stringParser = parser;
    }

    public final BinaryTree<T> createTreeFromFile(String pathToFile){
        List<String> linesFromFile = readLinesFromFile(pathToFile);
        Vector<T> parsedValues = parseLines(linesFromFile);
        BinaryTree<T> binaryTree = createTreeFromValuesVector(parsedValues);
        return binaryTree;
    }

    protected final List<String> readLinesFromFile(String fileName){
        Vector<String> lines = new Vector<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();

            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("FileReaderToVector::readFile: файл с именем '" + fileName + "' не был найден!");
        }
        return lines;
    }

    protected final Vector<T> parseLines(List<String> lines){
        Vector<T> parsedValues = new Vector<>();
        for (String line : lines) {
            parsedValues.add(stringParser.parseStringToValue(line));
        }
        return parsedValues;
    }

    private BinaryTree<T> createTreeFromValuesVector(Vector<T> parsedValues){
        BinaryTree<T> binaryTree = createTree();
        for (T value : parsedValues) {
            binaryTree.insert(value);
        }
        return binaryTree;
    }

    protected abstract BinaryTree<T> createTree();
}
