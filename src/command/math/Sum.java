package command.math;

import java.util.List;

import parser.CommandTreeNode;
import command.Command;

public class Sum extends Command {

	@Override
	public double calculateValue(List<CommandTreeNode> list) {
		return list.get(0).getValue() + list.get(1).getValue();
	}



}
