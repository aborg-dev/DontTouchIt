package com.github.donttouchit.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.Direction;

public class LightBall extends Ball {
	private static final float HEALTH_POINT_SIZE = 6;
	private static final float HEALTH_POINT_PADDING = 4;
	private int health = 3;

	public static class Specification extends Ball.Specification {

	}

	public LightBall(Dye dye, int column, int row) {
		super(dye, column, row);
	}

	public LightBall(Ball.Specification specification) {
		this(specification.dye, specification.column, specification.row);
	}

	@Override
	public void move(Direction moveDirection) {
		if (health == 0) return;
		super.move(moveDirection);
	}

	@Override
	protected void hitWall() {
		super.hitWall();
		health = Math.max(0, health - 1);
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

		float x = center.x - 1.5f * HEALTH_POINT_SIZE - HEALTH_POINT_PADDING;
		float y = center.y - 0.5f * HEALTH_POINT_SIZE;
		for (int i = 0; i < 3; ++i) {
			if (health >= i + 1) {
				shapeRenderer.setColor(Color.WHITE);
			} else {
				shapeRenderer.setColor(Color.BLACK);
			}
			float cx = x + i * (HEALTH_POINT_SIZE + HEALTH_POINT_PADDING);
			shapeRenderer.rect(cx, y, HEALTH_POINT_SIZE, HEALTH_POINT_SIZE);
		}

		shapeRenderer.end();

		batch.begin();
	}
}
