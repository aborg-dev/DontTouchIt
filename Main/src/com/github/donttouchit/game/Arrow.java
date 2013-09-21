package com.github.donttouchit.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.Direction;
import com.github.donttouchit.geom.GridPoint;


public class Arrow extends LevelObject implements ActionListener, ChangeListener {
	private static final float ARROW_LENGTH = 20;
	private static final float ARROW_HEIGHT = 10;
	private static final float[] defaultAngles = {
			ARROW_LENGTH / 2,
			-ARROW_HEIGHT,
			ARROW_LENGTH / 2,
			ARROW_HEIGHT,
			ARROW_LENGTH + ARROW_HEIGHT,
			0
	};

	private final float[] angles = new float[6];
	private Direction direction;
	private final int rotationSpeed;
	private final Dye dye;

	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	public static class Specification extends LevelObject.Specification {
		protected Direction direction;
		protected int rotationSpeed;
		protected Dye dye;
	}

	public Specification getSpecification() {
		Specification specification = new Specification();
		specification.direction = direction;
		specification.rotationSpeed = rotationSpeed;
		specification.dye = dye;
		specification.column = getColumn();
		specification.row = getRow();
		return specification;
	}

	public Arrow(Dye dye, int column, int row, Direction direction, int rotationSpeed) {
		super(column, row);
		if (direction == Direction.NONE) {
			throw new IllegalArgumentException("Direction can not be NONE");
		}

		this.direction = direction;
		this.rotationSpeed = rotationSpeed;
		this.dye = dye;

		final Arrow thisArrow = this;
		addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				super.tap(event, x, y, count, button);
				getLevel().change(thisArrow.dye, "turn");
//				thisArrow.direction = thisArrow.direction.plus(1);
			}
		});
	}

	public Arrow(Specification specification) {
		this(specification.dye, specification.column, specification.row, specification.direction, specification.rotationSpeed);
	}

	private void buildAngles() {
		Polygon polygon = new Polygon(defaultAngles);
		polygon.translate(getCenter().x, getCenter().y);
		polygon.setRotation(Direction.RIGHT.angleTo(direction));
		System.arraycopy(polygon.getTransformedVertices(), 0, angles, 0, 6);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);

		Vector2 center = getCenter();
		Rectangle rect = new Rectangle(0, 0, 0, 0);

		if (direction.isHorizontal()) {
			rect = new Rectangle(-ARROW_LENGTH / 2, -ARROW_HEIGHT / 2, ARROW_LENGTH, ARROW_HEIGHT);
		} else if (direction.isVertical()) {
			rect = new Rectangle(-ARROW_HEIGHT / 2, -ARROW_LENGTH / 2, ARROW_HEIGHT, ARROW_LENGTH);
		}
		rect.x += center.x;
		rect.y += center.y;

		buildAngles();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(dye.getColor());
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

		shapeRenderer.triangle(angles[0], angles[1], angles[2], angles[3], angles[4], angles[5]);
		shapeRenderer.end();

		batch.begin();
	}

	@Override
	public void ballEntered(Ball ball, GridPoint cell) {
		if (getColumn() == cell.x && getRow() == cell.y) {
			ball.changeDirection(direction);
		}
	}

	@Override
	public void ballLeft(Ball ball, GridPoint cell) {
		if (getColumn() == cell.x && getRow() == cell.y) {
		}
	}

	@Override
	public boolean isPassable(int column, int row) {
		return true;
	}

	@Override
	public boolean accept(Dye dye, Object object) {
		return this.dye == dye && object instanceof String;
	}

	@Override
	public void changed(Object object) {
		if (((String)object).equals("turn")) {
			direction = direction.plus(rotationSpeed);
		}
	}
}

