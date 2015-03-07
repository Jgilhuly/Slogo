package parser;

public interface TreeWrapper {
    public void incrementIndex ();

    public void incrementListStartIndex ();

    public void incrementListEndIndex ();

    public int getIndex ();

    public void recurse (CommandTreeNode root);

    public void printTestStatements (String value, String type, String name);
}
