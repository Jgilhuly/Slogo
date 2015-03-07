package parser;

import java.util.List;


public class VariableCase implements Cases {
    private TreeWrapper wrapper;
    private List<String> myInput;

    public VariableCase (TreeWrapper wrapper, List<String> input) {
        this.wrapper = wrapper;
        myInput = input;
    }

    public void recurse (CommandTreeNode root) {
        String value = myInput.get(wrapper.getIndex());
        CommandTreeNode temp = new CommandTreeNode("VARIABLE", value, 0, null);
        root.add(temp);

        wrapper.printTestStatements(value, temp.getType(), root.getName());
        wrapper.incrementIndex();
    }

}
