package interfaces.treeCreators;

import interfaces.BinaryTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public abstract class BinaryTreeCreatorFromFile {
    StringParser stringParser;

    protected BinaryTreeCreatorFromFile(StringParser stringParser){
        this.stringParser = stringParser;
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

    protected Vector<Comparable> parseLines(List<String> lines){
        Vector<Comparable> parsedValues = new Vector<>();
        for (String line : lines) {
            parsedValues.add(stringParser.parseStringToValue(line));
        }
        return parsedValues;
    }
}
