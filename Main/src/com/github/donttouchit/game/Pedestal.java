package com.github.donttouchit.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.donttouchit.game.properties.Dye;

public class Pedestal extends LevelObject {
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private final Dye dye;

	public static class Specification extends LevelObject.Specification {
		protected Dye dye;
	}

	public Pedestal(Dye dye, int column, int row) {
		super(column, row);
		this.dye = dye;
	}

	public Pedestal(Specification specification) {
		this(specification.dye, specification.column, specification.row);
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
