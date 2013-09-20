package com.github.donttouchit.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.donttouchit.game.properties.Dye;

public class ImaginaryWall extends LevelObject implements ChangeListener {
	private static final float WALL_SIZE = 10;
	private static final float WALL_PADDING = 4;

	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private final Dye dye;
	private boolean opened = false;
	private boolean goingToClose = false;

	public static class Specification extends LevelObject.Specification {
		protected Dye dye;
	}

	public Specification getSpecification() {
		Specification specification = new Specification();
		specification.dye = dye;
		return specification;
	}

	public ImaginaryWall(Dye dye, int column, int row) {
		super(column, row);
		this.dye = dye;
	}

	public ImaginaryWall(Specification specification) {
		this(specification.dye, specification.column, specification.row);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (goingToClose && getLevel().isEmpty(getColumn(), getRow())) {
			goingToClose = false;
			opened = false;
		}
	}

	@Override
	public boolean accept(Dye dye, Object object) {
		return this.dye == dye && object instanceof String;
	}

	@Override
	public void changed(Object object) {
		String message = (String)object;
		if (message.equals("open wall")) {
			opened = true;
			goingToClose = false;
		} else if (message.equals("close wall")) {
			goingToClose = true;
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);

		Vector2 center = getCenter();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(dye.getColor());

		if (!opened) {
			shapeRenderer.rect(0, WALL_PADDING, Level.CELL_SIZE, WALL_SIZE);
			shapeRenderer.rect(0, center.y - WALL_SIZE / 2, Level.CELL_SIZE, WALL_SIZE);
			shapeRenderer.rect(0, Level.CELL_SIZE - WALL_SIZE - WALL_PADDING, Level.CELL_SIZE, WALL_SIZE);

			shapeRenderer.rect(WALL_PADDING, 0, WALL_SIZE, Level.CELL_SIZE);
			shapeRenderer.rect(center.x - WALL_SIZE / 2, 0, WALL_SIZE, Level.CELL_SIZE);
			shapeRenderer.rect(Level.CELL_SIZE - WALL_SIZE - WALL_PADDING, 0, WALL_SIZE, Level.CELL_SIZE);
		}

		shapeRenderer.end();

		batch.begin();
	}

	@Override
	public boolean isPassable(int column, int row) {
		if (column == getColumn() && row == getRow()) {
			return opened;
		} else {
			return true;
		}
	}
}
