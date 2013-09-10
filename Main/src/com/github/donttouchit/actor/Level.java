package com.github.donttouchit.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;

public final class Level extends Group {
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private boolean[][] isPassable;
	public static final float cellSize = 80;

	public Level(int width, int height) {
		isPassable = new boolean[width][height];
	}

	public boolean isPassable(int column, int row) {
		return isPassable[column][row];
	}

	public void setPassable(int column, int row, boolean isPassable) {
		this.isPassable[column][row] = isPassable;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		System.err.println("Calling level draw");
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(0, 0, 0);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(0.0f, 0.0f, 1.0f, 1.0f);
		for (int row = 0; row < Gdx.graphics.getHeight() / cellSize; ++row) {
			shapeRenderer.line(0, row * cellSize, Gdx.graphics.getWidth(), row * cellSize);
		}
		for (int col = 0; col < Gdx.graphics.getWidth() / cellSize; ++col) {
			shapeRenderer.line(col * cellSize, 0, col * cellSize, Gdx.graphics.getHeight());
		}
		shapeRenderer.end();

		batch.begin();
	}
}
