package myproject.model;

public class Emitter implements Agent {

	private Cars next;
	private Timer time;

	Emitter(Cars ca) {
		next = ca;
		time = TimeServerLinked.getInstance();
		enqueue();
	}

	private void enqueue() {
		time.enqueue(time.currentTime() + MP.birthRate.getValue(), this);
													// later

	}

	public void run() {
		Car c = ObjectFactory.createCar();
		float fpos = c.getFrontPosition();
		if (next.receive(c, fpos) && next.distance(fpos) > fpos) {
			time.enqueue(time.currentTime() + MP.birthRate.getValue(),// 
					this);
		}
	
	}
}
