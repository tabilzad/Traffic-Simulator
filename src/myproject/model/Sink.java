package myproject.model;

public final class Sink implements Cars { //add receive

	public double distance(double fromPosition) {//from Notes 11/13
		return Double.POSITIVE_INFINITY;
	}

	public boolean receive(Car c, float d) {
		if (d == Double.POSITIVE_INFINITY)return true;
		return false;
	}
}
