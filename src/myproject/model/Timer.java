package myproject.model;

public interface Timer {
	public double currentTime();
	public void enqueue(double waketime, Agent thing);
	  public void run(double duration);
	  
	  public void addObserver(java.util.Observer o);
	  public void deleteObserver(java.util.Observer o);
	  
}
