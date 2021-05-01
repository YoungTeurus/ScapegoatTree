package implementations.treeCreators;

import interfaces.treeCreators.StringParser;

public class StringToStringParser implements StringParser<String> {
    @Override
    public String parseStringToValue(String string) {
        return string;
    }
}
