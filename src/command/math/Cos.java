package command.math;

import java.util.List;
import command.Command;

public class Cos extends Command{
    public double calculateValue(List<Object> param) {
        return (double) Math.cos((double) param.get(0));
    }
}
