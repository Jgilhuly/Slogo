package command.math;

import java.util.List;
import command.Command;

public class MinusCommand extends Command {

        public double calculateValue(List<Object> param) {
                return - (double) param.get(0);
        }
}
