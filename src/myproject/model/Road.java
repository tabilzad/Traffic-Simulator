package myproject.model;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * A road holds cars.
 */
public class Road implements Cars {

	private Set<Car> cars;
	private Timer time;
	private float endPosition;
	private Cars next;

	Road(Cars ca) {
		next = ca;
		cars = new HashSet<Car>();
		endPosition = (float) MP.roadLength.getValue();
		time = TimeServerLinked.getInstance();
	}

	public List<Car> getCars() {
		return new ArrayList<Car>(cars);
	}

	public double getLength() {
		return endPosition;
	}

	private double distanceToCarBack(double fromPosition) {
															
															
		double carBackPosition = Double.POSITIVE_INFINITY;
		for (Car c : cars) {
			if (c.getBackPosition() >= fromPosition
					&& c.getBackPosition() < carBackPosition)
				carBackPosition = c.getBackPosition();
		}
		return carBackPosition;
	}

	public double distance(double fromPosition) {
		double obstaclePosition = this.distanceToCarBack(fromPosition);
		if (obstaclePosition == Double.POSITIVE_INFINITY) {
			double distanceToEnd = Math.abs(endPosition - fromPosition);
			obstaclePosition = (next).distance(Math.abs(fromPosition
					- -this.endPosition));
			if (obstaclePosition == 0.0)
				return distanceToEnd;

		}

		return obstaclePosition - fromPosition;
	}

	public boolean receive(Car c, float frontPosition) {
		//add time
		cars.remove(c);
		if (frontPosition > endPosition) {
			return next.receive(c, frontPosition - endPosition);
		} else {
			c.setCurrentRoad(this);
			c.setFrontPosition(frontPosition);
			cars.add(c);
			time.enqueue(time.currentTime() + MP.time_step.getValue(), c);
			return true;
		}
	}
}
