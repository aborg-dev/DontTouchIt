package com.github.donttouchit.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.GridPoint;

public class PressurePlate extends LevelObject implements ActionListener {
	private final Dye dye;
	private static final float PLATE_WIDTH = 40;
	private static final float PLATE_HEIGHT = 40;

	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private boolean pressed = false;

	public static class Specification extends LevelObject.Specification {
		protected Dye dye;
	}

	public Specification getSpecification() {
		Specification specification = new Specification();
		specification.dye = dye;
		specification.column = getColumn();
		specification.row = getRow();
		return specification;
	}

	public PressurePlate(Dye dye, int column, int row) {
		super(column, row);
		this.dye = dye;
	}

	public PressurePlate(Specification specification) {
		this(specification.dye, specification.column, specification.row);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);

		Vector2 center = getCenter();
		Rectangle rect = new Rectangle(-PLATE_WIDTH / 2, -PLATE_HEIGHT / 2, PLATE_WIDTH, PLATE_HEIGHT);
		rect.x += center.x;
		rect.y += center.y;

		// Border
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(dye.getColor());
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

		// Inner
		if (pressed) {
			shapeRenderer.setColor(Color.LIGHT_GRAY);
		} else {
			shapeRenderer.setColor(Color.GRAY);
		}
		shapeRenderer.rect(rect.x + 2, rect.y + 2, rect.width - 4, rect.height - 4);
		shapeRenderer.end();

		batch.begin();
	}

	@Override
	public void ballEntered(Ball ball, GridPoint cell) {
		if (getColumn() == cell.x && getRow() == cell.y) {
			pressed = true;
			getLevel().change(dye, "open wall");
		}
	}

	@Override
	public void ballLeft(Ball ball, GridPoint cell) {
		if (getColumn() == cell.x && getRow() == cell.y) {
			pressed = false;
			getLevel().change(dye, "close wall");
		}
	}

	@Override
	public boolean isPassable(int column, int row) {
		return true;
	}
}
