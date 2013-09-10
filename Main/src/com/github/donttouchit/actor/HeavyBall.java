package com.github.donttouchit.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.donttouchit.actor.properties.Dye;

public class HeavyBall extends Ball {
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private float R = 30;

	public HeavyBall(Level level, Dye dye) {
		super(level, dye);
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
		shapeRenderer.setColor(1.0f, 0, 0, 1.0f);
		shapeRenderer.circle(center.x, center.y, R);
		shapeRenderer.end();

		batch.begin();
	}
}
