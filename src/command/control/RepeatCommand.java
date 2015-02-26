package command.control;

import java.util.List;
import command.Command;

public class RepeatCommand extends Command{
    
    public double calculateValue(List<Object> param) {
        int numTimes = 0;
        return (double) param.get(0) + (double) param.get(1);
}
}
