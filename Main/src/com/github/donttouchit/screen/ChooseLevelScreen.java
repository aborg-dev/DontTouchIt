package com.github.donttouchit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

	public ChooseLevelScreen(DontTouchIt game) {
		super(game);

		TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
		style.font = FontUtils.menuFont;
		style.downFontColor = Color.ORANGE;
		style.fontColor = Color.YELLOW;

		ArrayList<String> levelsFilenames = FileUtils.getLevelsList("./");
		for(final String filename: levelsFilenames) {
			TextButton levelButton = new TextButton(filename, style);
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

		/**
		 * Add buttons to the list
		 */
		for (Button button : levelButtons) {
			button.pad(100);
			buttonGroup.addActor(button);
		}

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

