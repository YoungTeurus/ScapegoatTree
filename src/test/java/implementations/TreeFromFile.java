package implementations;

import implementations.treeCreators.IntegerScapegoatTreeCreatorFromFile;
import implementations.treeCreators.StringScapegoatTreeCreatorFromFile;
import interfaces.BinaryTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeFromFile {
    @Test
    void words(){
        BinaryTree<String> binaryTree = new StringScapegoatTreeCreatorFromFile().createTreeFromFile("words.txt");
        System.out.println(binaryTree.toString());
        assertEquals(1000, binaryTree.size());

        System.out.println("Наименьший: " + binaryTree.findLowest());
        System.out.println("Наибольший: " + binaryTree.findGreatest());

        System.out.println("Больше чем 'java':");
        for (String value :
                binaryTree.getValuesGreaterThan("java")) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Меньше чем 'python':");
        for (String value :
                binaryTree.getValuesLowerThan("python")) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("В диапазоне от 'java' до 'python':");
        for (String value :
                binaryTree.getValuesInRange("java", "python")) {
            System.out.print(value + " ");
        }
        System.out.println();

    }

    @Test
    void numbers(){
        BinaryTree<Integer> binaryTree = new IntegerScapegoatTreeCreatorFromFile().createTreeFromFile("numbers.txt");
        System.out.println(binaryTree.toString());
        assertEquals(1000, binaryTree.size());

        System.out.println("Наименьший: " + binaryTree.findLowest());
        System.out.println("Наибольший: " + binaryTree.findGreatest());

        System.out.println("Больше чем '-100':");
        for (Integer value :
                binaryTree.getValuesGreaterThan(-100)) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Меньше чем '500':");
        for (Integer value :
                binaryTree.getValuesLowerThan(500)) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("В диапазоне от '-100' до '500':");
        for (Integer value :
                binaryTree.getValuesInRange(-100, 500)) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
