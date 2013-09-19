package com.github.donttouchit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.github.donttouchit.game.properties.Dye;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Level implements ActionListener {
	private List<LevelObject> levelObjects = new ArrayList<LevelObject>();
	private Group group = new Group();
	private boolean[][] passable;
	private int columns, rows;
	private boolean inAction = false;
	private int enterPosition;
	private int exitPosition;
	public static final float CELL_SIZE = 64;

	public Level(int columns, int rows, int enterPosition, int exitPosition) {
		this.columns = columns;
		this.rows = rows;
		this.enterPosition = enterPosition;
		this.exitPosition = exitPosition;
		group.setWidth(getColumns() * CELL_SIZE);
		group.setHeight(getRows() * CELL_SIZE);
		group.setOrigin(group.getWidth() / 2, group.getHeight() / 2);

		if (Gdx.graphics.getWidth() < group.getWidth() || Gdx.graphics.getHeight() < group.getHeight()) {
			float xAspect = (Gdx.graphics.getWidth() - CELL_SIZE / 2) / group.getWidth();
			float yAspect = (Gdx.graphics.getHeight() - CELL_SIZE / 2) / group.getHeight();
			float aspect = Math.min(xAspect, yAspect);
			System.err.println(aspect);
			group.setScale(aspect);
		}

		passable = new boolean[columns][rows];
		for (int i = 0; i < columns; ++i) {
			Arrays.fill(passable[i], true);
		}
	}

	public void addLevelObject(LevelObject levelObject) {
		group.addActor(levelObject);
		levelObjects.add(levelObject);
	}

	public Group getGroup() {
		return group;
	}

	public boolean isOnBoard(int column, int row) {
		if (column < 0 || row < 0) {
			return false;
		}
		if (column >= columns || row >= rows) {
			return false;
		}
		return true;
	}

	public boolean isPassable(int column, int row) {
		if (!isOnBoard(column, row)) {
			return false;
		}
		return passable[column][row];
	}

	public void setPassable(int column, int row, boolean passable) {
		if (!isOnBoard(column, row)) {
			throw new IllegalArgumentException("This cell is out of board, so it's always impassable");
		}
		this.passable[column][row] = passable;
	}

	public boolean isEmpty(int column, int row) {
		if (!isPassable(column, row)) {
			return false;
		}

		for (LevelObject levelObject : levelObjects) {
			if (!levelObject.isPassable(column, row)) {
				return false;
			}
		}
		return true;
	}

	public Dye getDye(int column, int row) {
		for (LevelObject levelObject : levelObjects) {
			if (levelObject.getBoardPosition().equals(new Point(column, row))) {
				if (levelObject instanceof Pedestal) {
					return ((Pedestal) levelObject).getDye();
				}
			}
		}
		return null;
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public boolean startAction(LevelObject levelObject) {
		if (inAction) return false;
		beforeAction(levelObject);
		inAction = true;
		return true;
	}

	public void stopAction(LevelObject levelObject) {
		if (inAction) {
			inAction = false;
			afterAction(levelObject);
		}
	}

	public void change(Dye dye, Object object) {
		for (LevelObject levelObject : levelObjects) {
			if (levelObject instanceof ChangeListener) {
				ChangeListener changeListener = (ChangeListener)levelObject;
				if (changeListener.accept(dye, object)) {
					changeListener.changed(object);
				}
			}
		}
	}

	private void beforeAction(LevelObject levelObject) {

	}

	private void afterAction(LevelObject levelObject) {

	}

	@Override
	public void ballEntered(Ball ball, GridPoint2 cell) {
		for (LevelObject levelObject : levelObjects) {
			if (levelObject instanceof ActionListener) {
				((ActionListener)levelObject).ballEntered(ball, cell);
			}
		}
	}

	@Override
	public void ballLeft(Ball ball, GridPoint2 cell) {
		for (LevelObject levelObject : levelObjects) {
			if (levelObject instanceof ActionListener) {
				((ActionListener)levelObject).ballLeft(ball, cell);
			}
		}
	}
}
