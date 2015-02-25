package command.math;

import java.util.List;
import java.util.Random;
import command.Command;

public class RandomCommand extends Command {

        public double calculateValue(List<Object> param) {
            Random r = new Random();
            return r.nextInt((int) param.get(0));
        }
}
