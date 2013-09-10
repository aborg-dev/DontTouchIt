package com.github.donttouchit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.github.donttouchit.actor.Ball;
import com.github.donttouchit.actor.CircleActor;
import com.github.donttouchit.actor.HeavyBall;
import com.github.donttouchit.actor.Level;
import com.github.donttouchit.actor.properties.Dye;
import com.github.donttouchit.geom.Direction;

/**
 * User: iiotep9huy
 * Date: 9/5/13
 * Time: 8:09 PM
 * Project: Don'tTouchIt
 */
public class GameScreen extends BasicScreen {

	private Stage stage;
	private Level level;
	private Ball ball;

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show() {
		System.err.println("Showing GameScreen");
		Gdx.input.setInputProcessor(stage);
	}

	public GameScreen() {
		stage = new Stage();
		level = new Level(5, 5);
		ball = new HeavyBall(level, Dye.RED);
		stage.addActor(level);

//		level.addActor(ball);

		final Ball cBall = ball;
		System.err.println("Adding input listner!");
		ball.addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				System.err.println("Tapped!");

			}

			@Override
			public void fling(InputEvent event, float velocityX, float velocityY, int button) {
				System.err.println("Flinged! " + velocityX + " " + velocityY);
				Direction direction;
				if (Math.abs(velocityX) > Math.abs(velocityY)) {
					direction = velocityX > 0 ? Direction.RIGHT : Direction.LEFT;
				} else {
					direction = velocityY > 0 ? Direction.TOP : Direction.BOTTOM;
				}
				ball.move(direction);
			}
		});

		stage.addActor(ball);


//		final CircleActor circleActor = new CircleActor(30);
//		circleActor.addListener(new ActorGestureListener() {
//			@Override
//			public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				circleActor.isHolded = true;
//			}
//
//			@Override
//			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//				circleActor.isHolded = false;
//			}
//
//			@Override
//			public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
//				circleActor.translate(deltaX, deltaY);
//				circleActor.velocity.set(deltaX * 30, deltaY * 30);
//			}
//		});
//
//		stage.addActor(circleActor);
	}

	@Override
	protected void draw() {
		super.draw();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.draw();
	}

	@Override
	protected void update(float delta) {
		super.update(delta);
		stage.act(delta);
	}
}
