package myproject.model;

public abstract class ObjectFactory {// cannot be instantiated
	private ObjectFactory() {}
	static public Car createCar() {return new Car();}
	static public Emitter emit(Cars ca) {return new Emitter(ca);}
	static public Road createRoad(Cars ca) {return new Road(ca);}
	static public Lights createLight(Cars ca) {return new Lights(ca);}
	static public Sink sink() {return new Sink();}

}
