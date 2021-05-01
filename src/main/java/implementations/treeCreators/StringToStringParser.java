package implementations.treeCreators;

import interfaces.treeCreators.StringParser;

public class StringToStringParser implements StringParser {
    @Override
    public Comparable parseStringToValue(String string) {
        return string;
    }
}
