package parser;

import java.util.List;

public class CommentCase implements Cases	{
	private TreeWrapper wrapper;
	private List<String> myInput;
	public CommentCase(TreeWrapper wrapper,List<String> input) {
		this.wrapper = wrapper;
		myInput = input;
	}
	@Override
	public void recurse(CommandTreeNode root) {
		// TODO Auto-generated method stub
		
	}
}
