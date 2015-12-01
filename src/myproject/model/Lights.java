package myproject.model;
public class Lights implements Cars {
	
	public enum LColor {
		GREEN, YELLOW, RED
	}

	private LColor state;
	private Cars nextRoad;

	public Lights(Cars ca) {
		nextRoad = ca;
	}

	public LColor getLightState() {
		return state;
	}

	public void setState(LColor c) {
		state = c;
	}

	public double distance(double fromPosition) {
		if (state == LColor.GREEN || state == LColor.YELLOW) {
			return nextRoad.distance(fromPosition);
		} else {
			return 0;
		}
	}

	public boolean receive(Car c, float frontPosition) {
		if (state == LColor.GREEN || state == LColor.YELLOW) {
			return nextRoad.receive(c, frontPosition);
		} else {
			return false;
		}
	}


}