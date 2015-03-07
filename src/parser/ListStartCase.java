package parser;

import java.util.List;


public class ListStartCase implements Cases {
    private int bracketCount = 0;
    private TreeWrapper wrapper;
    private List<String> myInput;

    public ListStartCase (TreeWrapper wrapper, List<String> input) {
        this.wrapper = wrapper;
        myInput = input;
    }

    public void recurse (CommandTreeNode root) {
        String value = myInput.get(wrapper.getIndex());
        wrapper.incrementListStartIndex();
        CommandTreeNode temp = new CommandTreeNode("BRACKET", value + "-"
                                                              + bracketCount++, 0, null);

        root.add(temp);

        wrapper.printTestStatements(value + "-" + (bracketCount - 1),
                                    temp.getType(), root.getName());

        wrapper.incrementIndex();
        while (!myInput.get(wrapper.getIndex()).equals("]")) {
            wrapper.recurse(temp);
        }
        wrapper.incrementListEndIndex();
        wrapper.incrementIndex();
    }

}
