package com.github.donttouchit.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.donttouchit.actor.properties.Dye;

public class HeavyBall extends Ball {
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	private float R = 40;

	public HeavyBall(Level level, Dye dye) {
		super(level, dye);
		setWidth(R * 2);
		setHeight(R * 2);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(1.0f, 0, 0, 1.0f);
		shapeRenderer.circle((getDx() * 2 + 1) * R, (getDy() * 2 + 1) * R, R);
		shapeRenderer.end();

		batch.begin();
	}
}
