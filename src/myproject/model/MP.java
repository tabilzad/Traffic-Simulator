package myproject.model;
/**
 * Static class for model parameters.
 */

public enum MP {
	//updatable parameters
	speed(40.0, 60.0),
	carLength(5, 10.0), 
	green(10.0, 11.0), 
	yellow(4.0, 5.0),
	roadLength(200.0, 500.0),
	interLength(1.0, 3.0),
	sim_time(1000.0),
	grid(3, 3),
	time_step(0.1),
	brake(9.0, 10.0),
	stop(0.5, 5.0),
	alternating(false),
	birthRate(10.0, 35.0);
	
	private double init = 0.0;
	private double val;
	private String descr;
	public static int road_draw_len = 200;
	public static int car_draw_len = 15;
	private boolean pattern;
	private double[] Limits = {0, 100 };
	private int[] grid_size = { 2, 2 };
	
	//booleans
	MP(boolean alter) {
		pattern = alter;
		val=init;//

	}
	//single double
	MP(double value) {
		val = value;

	
	}
	//size
	MP(int row, int col) {
		grid_size[0] = row;
		grid_size[1] = col;
		val = init;

	
	}
	//range doubles
	MP(double min, double max) {
		Limits[0] = min;
		Limits[1] = max;
		val = init;

	}
	
	public boolean getboolValue() {
		return pattern;
	}

	public String getdescription() {
		return descr;
	}
	
	public int[] getGridSize() {
		return grid_size;
	}
	public double[] getLimit() {
		return Limits;
	}
	public double getValue() {// move later to be seen by
		// more classes
		if (val == init)
		{
			return Limits[0] + (int) (Math.random() * ((Limits[1] - Limits[0]) + 1));
		}else
		return val;
	}
	
	public void setValue(double value) {
		val = value;
	}
	
	static public void setTimeStep(double time) {
		MP.time_step.setValue(time);
	}
	static public void setSimTime(double simtime) {
		MP.sim_time.setValue(simtime);
	}
	static public void setGridSize(int[] dimension) {
		MP.grid.setValue(dimension);
	}
	static public void setBirthRate(double[] rate) {
		MP.birthRate.setValue(rate);
	}
	static public void setRoadLength(double[] length) {
		MP.roadLength.setValue(length);
	}
	static public void setIntersectionLength(double[] length) {
		MP.interLength.setValue(length);
	}
	static public void setCarLength(double[] length) {
		MP.carLength.setValue(length);
	}
	static public void setCarVelocity(double[] speed) {
		MP.speed.setValue(speed);
	}
	static public void setCarBrake(double[] brake) {
		MP.brake.setValue(brake);
	}
	static public void setCarStop(double[] stop) {
		MP.stop.setValue(stop);
	}
	static public void setGreen(double[] time) {
		MP.green.setValue(time);
	}
	static public void setYellow(double[] time) {
		MP.yellow.setValue(time);
	}
	static public void setBool(String pattern) {
		int pat = Integer.parseInt(pattern);
		if (pat == 1)
			MP.alternating.setValue(true);
		else
			MP.alternating.setValue(false);
		
	}

	public void setValue(double[] lim) {
		Limits = lim;
	}

	public void setValue(boolean patterns) {
		pattern = patterns;
	}

	public void setValue(int[] dimension) {
		grid_size = dimension;
	}
	
	public String toString() {
		return descr;
	
	}
	public static StringBuilder currentVals()
	  {
	   
	    StringBuilder b = new StringBuilder();

	    b.append("Simulation time step (seconds)\t[" + time_step.getValue() + "]" + "\n");
	    b.append("Simulation run time (seconds)\t[" + sim_time.getValue()  + "]" + "\n");
	    b.append("Grid size (number of roads)\t[row=" + grid.getGridSize()[0] + ",column=" +grid.getGridSize()[1] + "]" + "\n");
	    b.append("Traffic pattern\t[" + alternating.getboolValue() + "]" + "\n");
	    b.append("Car entry rate (seconds/car)\t[min=" + birthRate.getLimit()[0] + ",max=" + birthRate.getLimit()[1] + "]" + "\n");
	    b.append("Road segment length (meters)\t[min=" + roadLength.getLimit()[0] + ",max=" + roadLength.getLimit()[1] + "]" + "\n");
	    b.append("Intersection length (meters)\t[min=" + interLength.getLimit()[0] + ",max=" + interLength.getLimit()[1] + "]" + "\n");
	    b.append("Car length (meters)\t[min=" + carLength.getValue() + ",max=" + carLength.getValue() + "]" + "\n");
	    b.append("Car maximum velocity (meters/second)\t[min=" + speed.getLimit()[0] + ",max=" + speed.getLimit()[1]  + "]" + "\n");
	    b.append("Car stop distance (meters)\t[min=" + stop.getLimit()[0] + ",max=" + stop.getLimit()[1] + "]" + "\n");
	    b.append("Car brake distance (meters)\t[min=" + brake.getLimit()[0] + ",max=" + brake.getLimit()[1]  + "]" + "\n");
	    b.append("Traffic light green time (seconds)\t[min=" + green.getLimit()[0] + ",max=" + green.getLimit()[1] + "]" + "\n");
	    b.append("Traffic light yellow time (seconds)\t[min=" + yellow.getLimit()[0] + ",max=" + yellow.getLimit()[1] + "]" + "\n");
	    return b;
	  }
	
}

	