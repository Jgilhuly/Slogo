package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import util.PatternMapper;
import errors.CommandNotFoundException;


public class CommandCase implements Cases {
    private Map<String, String[]> parametersMap;
    private static final int PARAM_INDEX = 0;
    private static final int TYPE_INDEX = 1;
    private boolean makingUserInstruction;
    private List<String> methodList; 
    
    private List<Entry<String, Pattern>> languagePatternList;
    private TreeWrapper wrapper;
    private List<String> myInput;
    private CommandTreeNode myRoot;

    public CommandCase (TreeWrapper wrapper, List<String> input) {
        this.wrapper = wrapper;
        methodList = new ArrayList<String>();
        parametersMap = PatternMapper.createParametersMap();
        myInput = input;
    }

    public void initiate (String language) {
        languagePatternList = PatternMapper.makePatterns(language);
        String value = useResourceBundle(myInput.get(wrapper.getIndex()));
        makingUserInstruction = value.equals("MakeUserInstruction");

        myRoot = new CommandTreeNode(obtainSubCommand(value), value, 0, null);
        int numParams = obtainNumParams(value);
        wrapper.printTestStatements(value, obtainSubCommand(value), null);
        wrapper.incrementIndex();
        for (int i = 0; i < numParams; i++) {
            wrapper.recurse(myRoot);
        }
    }

    public CommandTreeNode getRoot () {
        return myRoot;
    }

    /**
     * helper method to handle the case when the node is a command
     *
     * @param root
     */
    public void recurse (CommandTreeNode root) {
        String value = useResourceBundle(myInput.get(wrapper.getIndex()));
        wrapper.incrementIndex();
        if (makingUserInstruction) {
            makingUserInstruction = false;
            makingInstructionsCase(root, value);
            return;
        }
        int numParams = obtainNumParams(value);

        CommandTreeNode temp = new CommandTreeNode(obtainSubCommand(value),
                                                   value, 0, null);
        root.add(temp);

        wrapper.printTestStatements(value, temp.getType(), root.getName());

        for (int i = 0; i < numParams; i++) {
            wrapper.recurse(temp);
        }
    }

    private void makingInstructionsCase (CommandTreeNode root, String value) {
        CommandTreeNode temp = new CommandTreeNode(obtainSubCommand(value),
                                                   value, 0, null);
        root.add(temp);
        wrapper.printTestStatements(value, temp.getType(), root.getName());
        wrapper.recurse(root);
    }

    private String useResourceBundle (String input) {
        for (Entry<String, Pattern> p : languagePatternList) {
            if (p.getValue().matcher(input).matches())
                return p.getKey();
        }
        // if making procedure, return method call
        if (makingUserInstruction) {
            methodList.add(input);
            return input;
        }
        // if none found
        throw new CommandNotFoundException(input);
    }

    /**
     * Obtains the number of parameters given the key
     * 
     * @param key
     * @return
     */
    protected int obtainNumParams (String key) {
        return Integer.parseInt(parametersMap.get(key)[PARAM_INDEX]);
    }

    /**
     * Obtains the subcommand type given the key
     * 
     * @param key
     * @return
     */
    protected String obtainSubCommand (String key) {
        try {
            return "COMMAND." + parametersMap.get(key)[TYPE_INDEX];
        }
        catch (NullPointerException e) {
            return "COMMAND.USER";
        }

    }

    public List<String> getMethodsList () {
        return methodList;
    }

}
