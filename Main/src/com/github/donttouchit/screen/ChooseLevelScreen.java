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
import com.github.donttouchit.utils.FileUtils;
import com.github.donttouchit.utils.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class ChooseLevelScreen extends BasicScreen {
	private Stage stage = new Stage();
	private VerticalGroup buttonGroup = new VerticalGroup();
	private List<Button> levelButtons = new ArrayList<Button>();
	private final TextButton back = new TextButton("Back", FontUtils.style);

	public ChooseLevelScreen(DontTouchIt game) {
		super(game);
		back.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGame().setScreen(getGame().getMenuScreen());
			}
		});
		stage.addActor(buttonGroup);
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
		buttonGroup.clearChildren();
		levelButtons.clear();
		ArrayList <String> levelFileNames = FileUtils.getLevelsList("./");
		for(final String filename: levelFileNames) {
			TextButton levelButton = new TextButton(filename, FontUtils.style);
			levelButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					try {
						getGame().getGameScreen().setLevel(FileUtils.loadLevel(filename));
						getGame().setScreen(getGame().getGameScreen());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			levelButtons.add(levelButton);
		}

		levelButtons.add(back);
		for (Button button : levelButtons) {
			button.pad(10);
			buttonGroup.addActor(button);
		}

		buttonGroup.pack();
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

