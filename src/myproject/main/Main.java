package myproject.main;
import myproject.ui.UI;
import myproject.ui.TextUI;

/**
 * A static class to demonstrate the visualization aspect of
 * simulation.
 */
public class Main {
	//private Main() {}
	public static void main(String[] args) {

		UI ui = new TextUI();
		Controller control = new Controller(ui);
		control.run();
	
	}
}

