package com.github.donttouchit.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class Board extends LevelObject {
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private static final Texture wallTexture = new Texture("wall.png");
	private static final Texture floorTexture = new Texture("floor.png");

	public Board(Level level) {
		super(level, 0, 0);
		setWidth(getLevel().getColumns() * Level.CELL_SIZE);
		setHeight(getLevel().getRows() * Level.CELL_SIZE);

		addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				System.err.println("Level listener");
				int cx = (int) (x / Level.CELL_SIZE);
				int cy = (int) (y / Level.CELL_SIZE);
				System.err.println("Previous passable at " + cx + " " + cy + " is " + isPassable(cx, cy));
				getLevel().setPassable(cx, cy, !getLevel().isPassable(cx, cy));
			}
		});
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		for (int column = 0; column < getLevel().getColumns(); ++column) {
			for (int row = 0; row < getLevel().getRows(); ++row) {
				float x = getX() + column * Level.CELL_SIZE;
				float y = getY() + row * Level.CELL_SIZE;
				if (getLevel().isPassable(column, row)) {
					batch.draw(floorTexture, x, y, Level.CELL_SIZE, Level.CELL_SIZE);
				} else {
					batch.draw(wallTexture, x, y, Level.CELL_SIZE, Level.CELL_SIZE);
				}
			}
		}
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(0.0f, 0.0f, 1.0f, parentAlpha);
		for (int column = 0; column <= getLevel().getColumns(); ++column) {
//			shapeRenderer.line(column * Level.CELL_SIZE, 0, column * Level.CELL_SIZE, getLevel().getRows() * Level.CELL_SIZE);
		}
		for (int row = 0; row <= getLevel().getRows(); ++row) {
//			shapeRenderer.line(0, row * Level.CELL_SIZE, getLevel().getColumns() * Level.CELL_SIZE, row * Level.CELL_SIZE);
		}
		shapeRenderer.end();

		batch.begin();
		super.draw(batch, parentAlpha);
	}

	@Override
	public boolean isPassable(int column, int row) {
		return true;
	}
}
