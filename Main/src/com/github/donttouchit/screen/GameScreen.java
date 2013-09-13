package com.github.donttouchit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.github.donttouchit.actor.Ball;
import com.github.donttouchit.actor.Board;
import com.github.donttouchit.actor.HeavyBall;
import com.github.donttouchit.actor.Level;
import com.github.donttouchit.actor.properties.Dye;
import com.github.donttouchit.geom.Direction;

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
		level = new Level(10, 10);
		ball = new HeavyBall(level, Dye.RED);
		Board board = new Board(level);
		level.addLevelObject(board);
		level.addLevelObject(ball);
		stage.addActor(level.getGroup());

//		final Ball cBall = ball;
//		System.err.println("Adding input listener!");
//		ball.addListener(new ActorGestureListener() {
//			@Override
//			public void tap(InputEvent event, float x, float y, int count, int button) {
//				System.err.println("Tapped!");
//			}
//
//			@Override
//			public void fling(InputEvent event, float velocityX, float velocityY, int button) {
//				System.err.println("Flinged! " + velocityX + " " + velocityY);
//				Direction direction;
//				if (Math.abs(velocityX) > Math.abs(velocityY)) {
//					direction = velocityX > 0 ? Direction.RIGHT : Direction.LEFT;
//				} else {
//					direction = velocityY > 0 ? Direction.TOP : Direction.BOTTOM;
//				}
//				ball.move(direction);
//			}
//		});
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

		float x = (Gdx.graphics.getWidth() - level.getGroup().getWidth()) / 2;
		float y = (Gdx.graphics.getHeight() - level.getGroup().getHeight()) / 2;
		level.getGroup().setPosition(x, y);
	}
}
