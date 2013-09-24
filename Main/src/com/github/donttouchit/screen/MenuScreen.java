package com.github.donttouchit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.donttouchit.DontTouchIt;
import com.github.donttouchit.utils.FontUtils;

public class MenuScreen extends BasicScreen {
	private static final float PADDING = 10;

	private Stage stage = new Stage();
	private VerticalGroup buttonGroup = new VerticalGroup();
	private Button playButton;
	private Button chooseLevelButton;
	private Button exitButton;
	private Button editorButton;

	public MenuScreen(DontTouchIt game) {
		super(game);
		TextButton.TextButtonStyle style = FontUtils.style;
		playButton = new TextButton("Play", style);
		chooseLevelButton = new TextButton("Choose Level", style);
		editorButton = new TextButton("Editor", style);
		exitButton = new TextButton("Exit", style);

		playButton.pad(PADDING);
		chooseLevelButton.pad(PADDING);
		editorButton.pad(PADDING);
		exitButton.pad(PADDING);

		stage.addActor(buttonGroup);
		buttonGroup.addActor(playButton);
		buttonGroup.addActor(chooseLevelButton);
		buttonGroup.addActor(editorButton);
		buttonGroup.addActor(exitButton);

		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
//				We should start from the last level, player has played.
//				getGame().setScreen(new GameScreen(getGame()));
			}
		});

		chooseLevelButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				getGame().setScreen(getGame().getChooseLevelScreen());
			}
		});

		editorButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGame().setScreen(getGame().getEditorMenuScreen());
			}
		});

		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		buttonGroup.pack();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		float x = (width - buttonGroup.getWidth()) / 2;
		float y = (height - buttonGroup.getHeight()) / 2;
		buttonGroup.setPosition(x, y);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(stage);
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
