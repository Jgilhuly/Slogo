package command;


import java.util.List;


public abstract class Command {
	protected static final int ORIGIN_X = 0;
	protected static final int ORIGIN_Y = 0;
	protected static final int ORIGIN_HEADING = 0;
    public abstract double calculateValue(List<Object> param);


}


