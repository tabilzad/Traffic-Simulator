package myproject.model;


/**
 * A car remembers its position from the beginning of its road. Cars have random
 * velocity and random movement pattern: when reaching the end of a road, the
 * dot either resets its position to the beginning of the road, or reverses its
 * direction.
 */
public class Car implements Agent {

	private float frontPos;
	private Road currentRoad;

	private double speed;
	private double brakeDistance;
	private double stopDistance;
	private double carlength;
	private java.awt.Color color = new java.awt.Color((int) Math.ceil(Math
			.random() * 255), (int) Math.ceil(Math.random() * 255),
			(int) Math.ceil(Math.random() * 255));

	Car() {
		speed = MP.speed.getValue();
		brakeDistance = MP.brake.getValue();
		stopDistance = MP.stop.getValue();
		carlength = MP.carLength.getValue();
		// color = getColor();
	}

	public java.awt.Color getColor() {
		return color;
	}

	Road getCurrentRoad() {
		return currentRoad;
	}

	void setCurrentRoad(Road road) {// for Road
		currentRoad = road;
	}

	public float getFrontPosition() {// for Road
		return frontPos;// used in SwingAnimator
	}

	void setFrontPosition(float fp) {
		frontPos = fp;
	}

	double getBackPosition() {
		return frontPos - carlength;
	}

	public double getLength() {// used in SwingAnimator
		return carlength;
	}

	
	public void run() { 
		
		// find closest obstacle
		// calculate newVelocity
		// front pos here
		
		
		double velocity = 0;
		double obstacle = getCurrentRoad().distance(getFrontPosition()+carlength);

		if (obstacle == 0){
			obstacle = Double.POSITIVE_INFINITY;
			// speed*MP.time_step.getValue() + this.frontPos;
		}
		
		
		velocity = (speed / (brakeDistance - stopDistance))
				* (obstacle - stopDistance);

		velocity = Math.max(0.0, velocity);
		velocity = Math.min(speed, velocity);
		frontPos = (float) (frontPos + velocity * MP.time_step.getValue());

	
		getCurrentRoad().receive(this, frontPos);
		
		
	}

}