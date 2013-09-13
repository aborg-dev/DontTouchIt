package com.github.donttouchit.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.donttouchit.actor.properties.Dye;

public class Hole extends LevelObject {
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private Dye dye;

	public Hole(Level level, Dye dye) {
		super(level);
		this.dye = dye;
	}

	public Hole(Level level, Dye dye, int column, int row) {
		this(level, dye);
		setColumn(column);
		setRow(row);
	}

	@Override
	public boolean isPassable(int column, int row) {
		return true;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(dye.getColor());
		shapeRenderer.rect(getX() + 1, getY() + 1, Level.CELL_SIZE - 1, Level.CELL_SIZE - 1);
		shapeRenderer.end();

		batch.begin();
	}

	public Dye getDye() {
		return dye;
	}
}
