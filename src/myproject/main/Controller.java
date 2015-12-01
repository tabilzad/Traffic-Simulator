package myproject.main;

import myproject.ui.UI;
import myproject.ui.UIError;
import myproject.ui.UIForm;
import myproject.ui.UIFormBuilder;
import myproject.ui.UIFormTest;
import myproject.ui.UIMenu;
import myproject.ui.UIMenuBuilder;
import myproject.model.MP;
import myproject.model.Model;

//same as from hw3
public class Controller {
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int SETTINGS = 3;
	
	private UIMenu[] menus;
	private int state;

	private UIForm getValueForm;
	private UIForm getDoubleValueForm;
	private UIFormTest numberTest;
	private UIFormTest stringTest;
	private UIForm setSingleDouble;
	 private UIForm setIntdim;
	 private UIForm setBool;
	 private UIForm setRange;
	 
	 
	private UIFormTest intTest;

	private UI ui;

	Controller(UI ui) {
		this.ui = ui;

		menus = new UIMenu[4];
		state = START;
		addSTART(START);
		addSETTINGS(SETTINGS);
		addEXIT(EXIT);
		GridForm();
		RangeForm();
		boolForm();
		numberTest = input -> {
			try {
				Integer.parseInt(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		};
		stringTest = input -> ! "".equals(input.trim());
		UIFormBuilder f = new UIFormBuilder();
		
		UIFormBuilder singleDouble = new UIFormBuilder();
		singleDouble.add("value:", this.numberTest);
		setSingleDouble = singleDouble.toUIForm("Enter value: ");
		
	}
	
	private void boolForm() {
		UIFormBuilder f = new UIFormBuilder();
		f.add("1 for alternating and 0 for standard", numberTest);
		setBool = f.toUIForm("Pattern: ");
	}
	private void GridForm() {
		UIFormBuilder f = new UIFormBuilder();
		f.add("Enter the number of rows", numberTest);
		f.add("Enter the number of columns", numberTest);
		setIntdim = f.toUIForm("Grid Simulation Size");
	}
	private void RangeForm() {
		UIFormBuilder f = new UIFormBuilder();
		f.add("Enter minimum", numberTest);
		f.add("Enter maximum", numberTest);
		setRange = f.toUIForm("Range: ");
	}
	
	
	void run() {
		try {
			while (state != EXITED) {
				ui.processMenu(menus[state]);
			}
		} catch (UIError e) {
			ui.displayError("UI closed");
		}
	}

	private void addSTART(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default",
				() -> ui.displayError("doh!"));
		m.add("Run Simulation",
				() -> {
					Controller.this.state = START;
			       Model mod = new Model();
			        mod.run();
			        mod.dispose();
					
				});
		m.add("Change simulation parameters",
				() -> {
					
					 Controller.this.state = SETTINGS;
					
				});
	
		m.add("Exit",
				() -> state = EXIT);


		menus[stateNum] = m.toUIMenu("Traffic Simulation");
	}
	private void addSETTINGS(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default",
				() -> ui.displayError("doh!"));
		m.add("Show current values",
				() -> {
				
					StringBuilder b = MP.currentVals();
			        Controller.this.ui.displayMessage(b.toString());
					
				});
		m.add("Simulation time steps",
				() -> {
					
					String[] result = ui.processForm(setSingleDouble);
			        MP.setTimeStep(Double.parseDouble(result[0]));
					
				});
		m.add("Simulation runtime",
				() -> {
					
					String[] result = ui.processForm(setSingleDouble);
			        MP.setSimTime(Double.parseDouble(result[0]));
					
				});
	
		m.add("Grid size",
				() -> {
					
					String[] result = ui.processForm(setIntdim);
					int[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setGridSize(size);
					
					
				});
		m.add("Set traffic pattern",
				() -> {
					String[] result = ui.processForm(setBool);
					MP.setBool(result[0]);
					
				});
		m.add("Car entry rate",
				() -> {
					
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setBirthRate(size);
					
				});
		m.add("Road segment length",
				() -> {
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setRoadLength(size);
					
				});
		m.add("Intersection length",
				() -> {
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setIntersectionLength(size);
					
				});
		m.add("Car length",
				() -> {
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setCarLength(size);
					
				});
		m.add("Car max velocity",
				() -> {
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setCarVelocity(size);
					
				});
		m.add("Car stop distance",
				() -> {
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setCarStop(size);
					
				});
		m.add("Car brake distance",
				() -> {
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setCarBrake(size);
					
				});
		m.add("Traffic light green time",
				() -> {
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setGreen(size);
					
				});
		m.add("Traffic light yellow time",
				() -> {
					String[] result = ui.processForm(setRange);
					double[] size = { Integer.parseInt(result[0]),
							Integer.parseInt(result[1]) };
					MP.setYellow(size);
					
				});
		m.add("Return to the main menu",
				() -> state = START);
					
		m.add("Reset simulation and return to the main menu",
				() -> {
					//default parameters
					MP.speed.setValue(new double [] {40.0, 60.0});
					MP.carLength.setValue(new double [] {5, 10.0});
					MP.green.setValue(new double [] {10.0, 11.0});
					MP.yellow.setValue(new double [] {4.0, 5.0});
					MP.roadLength.setValue(new double [] {200.0, 500.0});
					MP.interLength.setValue(new double [] {1.0, 3.0});
					MP.sim_time.setValue(1000.0);
					MP.grid.setValue(new int[] {3, 3});
					MP.time_step.setValue(0.1);
					MP.brake.setValue(new double [] {9.0, 10.0});
					MP.stop.setValue(new double [] {0.5, 5.0});
					MP.alternating.setValue(false);
					MP.birthRate.setValue(new double [] {10.0, 35.0});
					state = START;
				});
	
		m.add("Exit",
				() -> state = EXIT);



		menus[stateNum] = m.toUIMenu("Traffic Simulation eo");
	}
	
	private void addEXIT(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default", () -> {});
		m.add("Yes",
				() -> state = EXITED);
		m.add("No",
				() -> state = START);

		menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
	}
}
