package com.github.donttouchit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.github.donttouchit.DontTouchIt;
import com.github.donttouchit.game.*;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.Direction;
import com.github.donttouchit.geom.GridPoint;

public class GameScreen extends BasicScreen {
	private Stage stage;
	private Level level;
	private ImageButton restart;

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		restart.setPosition(10, Gdx.graphics.getHeight() - 10 - restart.getHeight());
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

	public GameScreen(DontTouchIt game) {
		super(game);
		stage = new Stage();
		level = new Level(10, 10, new GridPoint(0, 6), new GridPoint(6, 6));

		SpriteDrawable sprite = new SpriteDrawable(new Sprite(new Texture("restart.png")));
		restart = new ImageButton(sprite);
		restart.pack();
		restart.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Gdx.app.log("Restart button", "Clicked");
			}
		});

		Board board = new Board(level);

		PressurePlate plate = new PressurePlate(level, Dye.GREEN, 3, 0);
		ImaginaryWall wall = new ImaginaryWall(level, Dye.GREEN, 4, 0, true);

		Arrow greenArrowOne = new Arrow(level, Dye.GREEN, 2, 2, Direction.LEFT, 1);
		Arrow greenArrowTwo = new Arrow(level, Dye.GREEN, 4, 2, Direction.BOTTOM, 2);

		Pedestal redPedestal = new Pedestal(level, Dye.RED, 3, 3);
		Pedestal bluePedestal = new Pedestal(level, Dye.BLUE, 7, 4);

		Ball redBall = new HeavyBall(level, Dye.RED, 1, 1);
		Ball blueBall = new LightBall(level, Dye.BLUE, 1, 5);

//		level.addLevelObject(board);
//
//		level.addLevelObject(plate);
//		level.addLevelObject(wall);
//
//		level.addLevelObject(arrow);
//
//		level.addLevelObject(redPedestal);
//		level.addLevelObject(bluePedestal);
//
//		level.addLevelObject(redBall);
//		level.addLevelObject(blueBall);

		stage.addActor(level.getGroup());
		stage.addActor(restart);
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
