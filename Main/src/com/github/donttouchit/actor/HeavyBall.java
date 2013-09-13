package com.github.donttouchit.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.donttouchit.actor.properties.Dye;

public class HeavyBall extends Ball {
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private float R = 30;

	public HeavyBall(Level level, Dye dye) {
		super(level, dye);
	}

	public HeavyBall(Level level, Dye dye, int column, int row) {
		this(level, dye);
		setColumn(column);
		setRow(row);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);

		Vector2 center = getCenter();

		// Border
		float innerR = R;
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		if (isInHole()) {
			shapeRenderer.setColor(1.0f, 0.84f, 0.0f, 1.0f);
			innerR = R * 4 / 5;
		} else {
			shapeRenderer.setColor(0.0f, 0.0f, 0.0f, 1.0f);
		}
		shapeRenderer.circle(center.x, center.y, R);
		shapeRenderer.end();

		// Inner
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(dye.getColor());
		shapeRenderer.circle(center.x, center.y, innerR);
		shapeRenderer.end();

		batch.begin();
	}
}
