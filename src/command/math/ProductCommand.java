package command.math;

import java.util.List;
import command.Command;

public class ProductCommand extends Command {

        public double calculateValue(List<Object> param) {
            return (double) Math.multiplyExact((long) param.get(0), (long) param.get(1));
        }
}
