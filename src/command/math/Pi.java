package command.math;

import java.util.List;
import command.Command;

public class Pi extends Command {

        public double calculateValue(List<Object> param) {
                return Math.PI;
        }
}
