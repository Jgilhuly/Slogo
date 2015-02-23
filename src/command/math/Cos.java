package command.math;

import command.Command;


public class Cos extends Command{
  
    private double myParam;

    public Cos(double param){
        myParam = param;
    }

	@Override
	public double calculateValue(Object param) {
		return Math.cos((double)param);
	}

}
