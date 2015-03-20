// This entire file is part of my masterpiece.
// Kei Yoshikoshi

package parser;

/**
 * Interface that defines certain methods for users to access. Used to pass in
 * TreeGenerator with limited method access
 * 
 * @author Kei Yoshikoshi
 *
 */
public interface TreeWrapper {
	public void incrementIndex();

	public void incrementListStartIndex();

	public void incrementListEndIndex();

	public int getIndex();

	public void recurse(CommandTreeNode root);

}
