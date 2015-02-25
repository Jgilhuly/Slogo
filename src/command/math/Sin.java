package command.math;

import java.util.List;
import command.Command;

public class Sin extends Command{
    public double calculateValue(List<Object> param) {
        return (double) Math.sin((double) param.get(0));
    }
}