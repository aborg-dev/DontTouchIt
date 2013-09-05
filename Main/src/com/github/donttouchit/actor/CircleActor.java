package com.github.donttouchit.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

	public CircleActor(float R) {
		this.R = R;
		shapeRenderer = new ShapeRenderer();
		setWidth(2 * R);
		setHeight(2 * R);
		setPosition(100, 100);
//		setBounds(0, 0, 2 * R, 2 * R);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
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
