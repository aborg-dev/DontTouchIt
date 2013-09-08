package com.github.donttouchit.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * User: iiotep9huy
 * Date: 9/5/13
 * Time: 8:22 PM
 * Project: Don'tTouchIt
 */
public class CircleActor extends Actor {
	ShapeRenderer shapeRenderer;
	public float R;
	public Vector2 velocity;
	public boolean isHolded = false;

	public CircleActor(float R) {
		this.R = R;
		velocity = new Vector2(0, 0);
		shapeRenderer = new ShapeRenderer();
		setWidth(2 * R);
		setHeight(2 * R);
		setPosition(100, 100);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (!isHolded) {
			translate(velocity.x * delta, velocity.y * delta);
		}
		velocity.scl(0.993f);
		if (getX() < 0 || getX() + 2 * R > Gdx.graphics.getWidth()) {

			if (getX() < 0) {
				setX(0);
			}

			if (getX() + 2 * R > Gdx.graphics.getWidth()) {
				setX(Gdx.graphics.getWidth() - 2 * R);
			}

			velocity.x = -velocity.x;
		}

		if (getY() < 0 || getY() + 2 * R > Gdx.graphics.getHeight()) {

			if (getY() < 0) {
				setY(0);
			}

			if (getY() + 2 * R > Gdx.graphics.getHeight()) {
				setY(Gdx.graphics.getHeight() - 2 * R);
			}

			velocity.y = -velocity.y;
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(1.0f, 0, 0, 1.0f);
		shapeRenderer.circle(R, R, R);
		shapeRenderer.end();

		batch.begin();
	}
}
