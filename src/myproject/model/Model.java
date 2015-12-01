package myproject.model;

import myproject.model.swing.SwingAnimatorBuilder;
import myproject.util.Animator;
import myproject.model.Light.Direction;
import myproject.model.Timer;
import myproject.model.TimeServerLinked;

/**
 * An example to model for a simple visualization. The model contains roads
 * organized in a matrix. See {@link #Model(AnimatorBuilder, int, int)}.
 */
public final class Model {

	private Animator animator;
	private AnimatorBuilder builder;
	private boolean disposed;
	public Timer time;
	private boolean alternating;

	public Model() {
		builder = new SwingAnimatorBuilder();
		time = TimeServerLinked.getInstance();
		alternating = MP.alternating.getboolValue();
		makeSquareGrid(MP.grid.getGridSize());
	
		animator = builder.getAnimator();
	
		time.addObserver(animator);

	}

	private void makeSquareGrid(int[] dimension) {// from notes 2

		Light[][] eastWest = new Light[dimension[0]][dimension[1]];
		for (int i = 0; i < dimension[0]; i++) {
			for (int j = 0; j < dimension[1]; j++) {
				eastWest[i][j] = new Light();
			
			}
		}
		Light[][] northSouth = rotate(eastWest);
		createSegments(dimension[0], dimension[1], eastWest, northSouth);
	}

	private void createSegments(int row, int col, Light[][] eastWest,
			Light[][] northSouth) {

		if (alternating) {
			alternating = !alternating;
			for (int i = 0; i < row; i++) {
				if (alternating) {
		
					makeFlippedSegment(eastWest[i], Direction.EASTWEST, i, builder);
				} else {
					makeSegment(eastWest[i], Direction.EASTWEST, i, builder);
				}
				alternating = !alternating;
			}
			alternating = false;
			for (int i = 0; i < col; i++) {
				if (alternating) {
					makeFlippedSegment(northSouth[i], Direction.NORTHSOUTH, i, builder);
				} else {
					makeSegment(northSouth[i], Direction.NORTHSOUTH, i, builder);
				}
		
				alternating = !alternating;
			}
		} else {
			for (int i = 0; i < row; i++) {
				makeSegment(eastWest[i], Direction.EASTWEST, i, builder);
			}
			for (int i = 0; i < col; i++) {
				makeSegment(northSouth[i], Direction.NORTHSOUTH, i, builder);
				
			}
		}
	}

	public Emitter makeSegment(Light[] light, Direction d, int index,//add roads and lights
			AnimatorBuilder builder) {
		Cars next = ObjectFactory.createRoad(ObjectFactory.sink());
		if (d == Direction.EASTWEST) {
			builder.addHorizontalRoad((Road) next, index, light.length, false);
		} else {
			builder.addVerticalRoad((Road) next, light.length, index, false);
		}
		for (int i = light.length - 1; i >= 0; i--) {// from notes
			Lights l = ObjectFactory.createLight(next);
			light[i].setLight(l, d);
			next = ObjectFactory.createRoad(l);
			if (d == Direction.EASTWEST) {
				builder.addHorizontalRoad((Road) next, index, i, false);
				builder.addLight(l, index, i);
			} else {
				builder.addVerticalRoad((Road) next, i, index, false);
				builder.addLight(l, i, index);
			}
		}
		return ObjectFactory.emit(next);
	}

	private Emitter makeFlippedSegment(Light[] light, Direction dir, int index,
			AnimatorBuilder builder) {

		Cars next = ObjectFactory.createRoad(ObjectFactory.sink());
		for (int i = 0; i < light.length; i++) {
			Lights l = ObjectFactory.createLight(next);
			light[i].setLight(l, dir);
			if (dir == Direction.EASTWEST) {
				builder.addLight(l, index, i);
				builder.addHorizontalRoad((Road) next, index, i, true);
			} else {
				builder.addLight(l, i, index);
				builder.addVerticalRoad((Road) next, i, index, true);
			}
			next = ObjectFactory.createRoad(l);
		}
		if (dir == Direction.EASTWEST) {
			builder.addHorizontalRoad((Road) next, index, light.length, true);
		} else {
			builder.addVerticalRoad((Road) next, light.length, index, true);
		}
		//return new Emitter(next);
		return ObjectFactory.emit(next);
	}

	
	private Light[][] rotate(Light[][] orig) {// from notes, should work
		Light[][] value = new Light[orig[0].length][orig.length];
		for (int i = 0; i < orig[0].length; i++) {
			for (int j = 0; j < orig.length; j++) {
				value[i][j] = orig[j][i];
			}
		}
		return value;
	}


	public void dispose() {
		animator.dispose();
		disposed = true;
	}

	
	public void run() {

		if (disposed) {
			throw new IllegalStateException();
		}
		// System.out.println (MP.sim_time.getValue());
		time.run(MP.sim_time.getValue()); // MP.sim_time.getValue()

	}


}
