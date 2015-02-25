package command.math;

import java.util.List;
import command.Command;

public class Atan extends Command{
    public double calculateValue(List<Object> param) {
        return (double) Math.atan((double) param.get(0));
    }
}
