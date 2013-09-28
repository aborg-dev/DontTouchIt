package com.github.donttouchit.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.screen.editor.Brush;

public class Pedestal extends LevelObject {
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();

	public static class Specification extends LevelObject.Specification {
	}

	static {
		Specification specification = new Specification();
		specification.dye = Dye.GREEN;
		Brush.registerBrush(specification);
	}

	@Override
	public Specification getSpecification() {
		Specification specification = new Specification();
		specification.dye = getDye();
		specification.column = getColumn();
		specification.row = getRow();
		return specification;
	}

	@Override
	public Integer getDepth() {
		return 1;
	}

	public Pedestal(Dye dye, int column, int row) {
		super(column, row, dye);
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
		shapeRenderer.setColor(getDye().getColor());
		shapeRenderer.rect(getX() + 1, getY() + 1, Level.CELL_SIZE - 1, Level.CELL_SIZE - 1);
		shapeRenderer.end();

		batch.begin();
	}
}
