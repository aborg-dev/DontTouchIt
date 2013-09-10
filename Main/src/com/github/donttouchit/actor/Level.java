package com.github.donttouchit.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.List;

public final class Level extends Group {
	public static final float CELL_SIZE = 80;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	private List<LevelObject> levelObjects = new ArrayList<LevelObject>();
	private boolean[][] passable;
	private int columns, rows;

	public Level(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		passable = new boolean[columns][rows];
		setWidth(columns * CELL_SIZE);
		setHeight(rows * CELL_SIZE);
	}

	public boolean isPassable(int column, int row) {
		return passable[column][row];
	}

	public void setPassable(int column, int row, boolean passable) {
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

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
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
}
