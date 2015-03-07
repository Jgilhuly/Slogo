package parser;

import java.util.List;


public class ConstantCase implements Cases {
    private TreeWrapper wrapper;
    private List<String> myInput;

    public ConstantCase (TreeWrapper wrapper, List<String> input) {
        this.wrapper = wrapper;
        myInput = input;
    }

    public void recurse (CommandTreeNode root) {
        // TODO Auto-generated method stub
        String value = myInput.get(wrapper.getIndex());
        CommandTreeNode temp =
                new CommandTreeNode("CONSTANT", "CONSTANT",
                                    Double.parseDouble(myInput.get(wrapper.getIndex())), null);
        root.add(temp);

        wrapper.printTestStatements(value, temp.getType(), root.getName());
        wrapper.incrementIndex();
    }

}
