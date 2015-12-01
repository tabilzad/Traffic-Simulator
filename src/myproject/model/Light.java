package myproject.model;

import myproject.model.Lights.LColor;

/**
 * A light has a boolean state.
 */
public class Light implements Agent {


	public enum Direction {

		EASTWEST, NORTHSOUTH
	}
	
	
	private Timer time;

	Light() {
		time = TimeServerLinked.getInstance();
		enqueue();
	}
	private void enqueue() {
		time.enqueue(time.currentTime(), this);
	}
	
	 
	public void setLight(Lights l, Direction d) {// show horizontal
		if (d == Direction.EASTWEST) {
			EWLight = l;
			EWLight.setState(LColor.RED);
		} else {
			NSLight = l;
			NSLight.setState(LColor.GREEN);
		}
	}
	
	Lights EWLight;
	Lights NSLight;
	
	public void run() {
		
		
		switch (EWLight.getLightState()){
		
		case GREEN: NSLight.setState(LColor.RED); 
					time.enqueue(
					time.currentTime() + MP.green.getValue(), this);
					EWLight.setState(LColor.YELLOW); break;
		case YELLOW: time.enqueue(
					time.currentTime() + MP.yellow.getValue(), this);
					EWLight.setState(LColor.RED);
					NSLight.setState(LColor.GREEN);break;
		default:
			//System.out.println("In default switch");
			switch (NSLight.getLightState()){
			
			case GREEN: EWLight.setState(LColor.RED);
						time.enqueue(
						time.currentTime() + MP.green.getValue(), this);
						NSLight.setState(LColor.YELLOW);break;
			case YELLOW:time.enqueue(
						time.currentTime() + MP.yellow.getValue(), this);
						NSLight.setState(LColor.RED);
						EWLight.setState(LColor.GREEN);break;
			default:System.out.println("Look at Light switch controller");
				break;
			
			}
		}
		
		
	}
}
