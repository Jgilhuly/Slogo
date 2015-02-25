package command.math;

import java.util.List;
import command.Command;

public class Log extends Command {

        public double calculateValue(List<Object> param) {
                return Math.log((double) param.get(0));
        }
}
