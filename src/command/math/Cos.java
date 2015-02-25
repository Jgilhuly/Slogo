package command.math;

import java.util.List;
import parser.CommandTreeNode;
import command.Command;


public class Cos extends Command{
  
    private double myParam;

    public Cos(double param){
        myParam = param;


    }
    @Override
    public double calculateValue (List<CommandTreeNode> list) {
        return Math.cos((double)param);
    }

}
