package com.github.donttouchit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.github.donttouchit.actor.*;
import com.github.donttouchit.actor.properties.Dye;
import com.github.donttouchit.geom.Direction;

public class GameScreen extends BasicScreen {
	private Stage stage;
	private Level level;

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
		Ball redBall = new HeavyBall(level, Dye.RED, 1, 1);
		Ball blueBall = new HeavyBall(level, Dye.BLUE, 1, 5);
		Hole redHole = new Hole(level, Dye.RED, 3, 3);
		Hole blueHole = new Hole(level, Dye.BLUE, 7, 4);
		Board board = new Board(level);
		level.addLevelObject(board);
		level.addLevelObject(redHole);
		level.addLevelObject(blueHole);
		level.addLevelObject(redBall);
		level.addLevelObject(blueBall);
		stage.addActor(level.getGroup());
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
