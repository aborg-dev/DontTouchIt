package com.github.donttouchit.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.github.donttouchit.geom.Direction;


public class Arrow extends LevelObject implements ActionListener {
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

	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	public Arrow(Level level, int column, int row, Direction direction) {
		super(level, column, row);
		if (direction == Direction.NONE) {
			throw new IllegalArgumentException("Direction can not be NONE");
		}
		this.direction = direction;

		final Arrow thisArrow = this;
		addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				super.tap(event, x, y, count, button);
				thisArrow.direction = thisArrow.direction.plus(1);
			}
		});
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
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

		shapeRenderer.triangle(angles[0], angles[1], angles[2], angles[3], angles[4], angles[5]);
		shapeRenderer.end();

		batch.begin();
	}

	@Override
	public void ballEntered(Ball ball, GridPoint2 cell) {
		if (getColumn() == cell.x && getRow() == cell.y) {
			ball.changeDirection(direction);
		}
	}

	@Override
	public void ballLeaved(Ball ball, GridPoint2 cell) {
		if (getColumn() == cell.x && getRow() == cell.y) {
		}
	}

	@Override
	public boolean isPassable(int column, int row) {
		return true;
	}
}

