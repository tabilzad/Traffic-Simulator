package myproject.model;
import java.util.Observable;

public final class TimeServerLinked extends Observable implements Timer {
	private static TimeServerLinked time = null;
	private static final class Node {
		final double waketime;
		final Agent agent;
		Node next;

		public Node(double waketime, Agent agent, Node next) {
			this.waketime = waketime;
			this.agent = agent;
			this.next = next;
		}
	}
	private double currentTime;
	private int size;
	private Node head;
	//private static TimeServerLinked time = new TimeServerLinked();

	/*
	 * Invariant: head != null
	 * Invariant: head.agent == null
	 * Invariant: (size == 0) iff (head.next == null)
	 */
	public TimeServerLinked() {
		size = 0;
		head = new Node(0, null, null);
	}
	
	
	public static TimeServerLinked getInstance(){//singleton
		 if(time == null)
		 {
			 time = new TimeServerLinked();
		 } 
			 
		return time;
	  }

	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node node = head.next;
		String sep = "";
		while (node != null) {
			sb.append(sep).append("(").append(node.waketime).append(",")
			.append(node.agent).append(")");
			node = node.next;
			sep = ";";
		}
		sb.append("]");
		return (sb.toString());
	}

	public double currentTime() {
		return currentTime;
	}

	public void enqueue(double waketime, Agent agent)
			throws IllegalArgumentException
	{
		if (waketime < currentTime)
			throw new IllegalArgumentException();
		Node prevElement = head;
		while ((prevElement.next != null) &&
				(prevElement.next.waketime <= waketime)) {
			prevElement = prevElement.next;
		}
		Node newElement = new Node(waketime, agent, prevElement.next);
		prevElement.next = newElement;
		size++;
	}

	Agent dequeue()
	{
		if (size < 1)
			throw new java.util.NoSuchElementException();
		Agent rval = head.next.agent;
		head.next = head.next.next;
		size--;
		return rval;
	}

	int size() {
		return size;
	}

	boolean empty() {
		return size() == 0;
	}

	public void run(double duration) {
		double endtime = currentTime + duration;
		
		while ((!empty()) && (head.next.waketime <= endtime)) {
			if ((currentTime - head.next.waketime) < 1e-09) {
				
				super.setChanged();
				super.notifyObservers();
				
			}
			currentTime = head.next.waketime;
			dequeue().run();//fast
			
		
		}
		currentTime = endtime;
	}
}