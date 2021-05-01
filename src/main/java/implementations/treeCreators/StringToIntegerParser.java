package implementations.treeCreators;

import interfaces.treeCreators.StringParser;

public class StringToIntegerParser implements StringParser<Integer> {
    @Override
    public Integer parseStringToValue(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException e){
            throw new RuntimeException("IntegerBaseBinaryTreeCreatorFromFile::parseLines : файл содержит строку, которую невозможно привести к Integer: '" + string + "'!");
        }
    }
}
