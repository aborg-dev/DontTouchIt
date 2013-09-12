package com.github.donttouchit.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Level extends Group {
	public static final float CELL_SIZE = 64;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	private List<LevelObject> levelObjects = new ArrayList<LevelObject>();
	private boolean[][] passable;
	private int columns, rows;

	private static final Texture wallTexture = new Texture("wall.png");

	public Level(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		passable = new boolean[columns][rows];
		for (int i = 0; i < columns; ++i) {
			Arrays.fill(passable[i], true);
		}
		setWidth(columns * CELL_SIZE);
		setHeight(rows * CELL_SIZE);

		addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				System.err.println("Level listener");
				int cx = (int)(x / Level.CELL_SIZE);
				int cy = (int)(y / Level.CELL_SIZE);
				System.err.println("Previous passable at " + cx + " " + cy + " is " + isPassable(cx, cy));
				setPassable(cx, cy, !isPassable(cx, cy));
			}
		});
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
		return passable[column][row];
	}

	public void setPassable(int column, int row, boolean passable) {
		this.passable[column][row] = passable;
	}

	public boolean isEmpty(int column, int row) {
		if (!isOnBoard(column, row)) {
			return false;
		}
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

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		for (int column = 0; column < getColumns(); ++column) {
			for (int row = 0; row < getRows(); ++row) {
				if (!isPassable(column, row)) {
					batch.draw(wallTexture, getX() + column * CELL_SIZE, getY() + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(0.0f, 0.0f, 1.0f, parentAlpha);
		for (int column = 0; column <= columns; ++column) {
			shapeRenderer.line(column * CELL_SIZE, 0, column * CELL_SIZE, rows * CELL_SIZE);
		}
		for (int row = 0; row <= rows; ++row) {
			shapeRenderer.line(0, row * CELL_SIZE, columns * CELL_SIZE, row * CELL_SIZE);
		}
		shapeRenderer.end();

		batch.begin();
		super.draw(batch, parentAlpha);
	}
	

	@Override
	protected void childrenChanged() {
		super.childrenChanged();
		levelObjects.clear();
		for (Actor actor : getChildren()) {
			if (actor instanceof LevelObject) {
				levelObjects.add((LevelObject)actor);
			}
		}
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}
}
