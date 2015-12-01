package myproject.model.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import myproject.model.AnimatorBuilder;
import myproject.model.Car;
import myproject.model.Lights;
import myproject.model.Lights.LColor;
import myproject.model.Road;
import myproject.util.Animator;

/**
 * A class for building Animators.
 */
public class TextAnimatorBuilder implements AnimatorBuilder {
	TextAnimator animator;
	public TextAnimatorBuilder() {
		this.animator = new TextAnimator();
	}
	public Animator getAnimator() {
		if (animator == null) { throw new IllegalStateException(); }
		Animator returnValue = animator;
		animator = null;
		return returnValue;
	}
	public void addLight(Lights d, int i, int j) {
		animator.addLight(d,i,j);
	}
	public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
		animator.addRoad(l,i,j);
	}
	public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
		animator.addRoad(l,i,j);
	}


	/** Class for drawing the Model. */
	private static class TextAnimator implements Animator {

		/** Triple of a model element <code>x</code> and coordinates. */
		private static class Element<T> {
			T x;
			int i;
			int j;
			Element(T x, int i, int j) {
				this.x = x;
				this.i = i;
				this.j = j;
			}
		}

		private List<Element<Road>> roadElements;
		private List<Element<Lights>> lightElements;
		TextAnimator() {
			roadElements = new ArrayList<Element<Road>>();
			lightElements = new ArrayList<Element<Lights>>();
		}
		void addLight(Lights x, int i, int j) {
			lightElements.add(new Element<Lights>(x,i,j));
		}
		void addRoad(Road x, int i, int j) {
			roadElements.add(new Element<Road>(x,i,j));
		}

		public void dispose() { }

		public void update(Observable o, Object arg) {
			for (Element<Lights> e : lightElements) {
				System.out.print("Light at (" + e.i + "," + e.j + "): ");
				if (e.x.getLightState() == LColor.GREEN) {
					System.out.println("Blue");//why is this blue?
				} else {
					System.out.println("Yellow");
				}
			}
			for (Element<Road> e : roadElements) {
				for (Car d : e.x.getCars()) {
					System.out.print("Road at (" + e.i + "," + e.j + "): ");
					System.out.println("Car at " + d.getFrontPosition());
				}
			}
		}
	}
}
