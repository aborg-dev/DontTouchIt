package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.donttouchit.DontTouchIt;
import com.github.donttouchit.game.Level;
import com.github.donttouchit.screen.BasicScreen;
import com.github.donttouchit.utils.FontUtils;
import com.github.donttouchit.utils.SkinUtils;

public class EditorCreateScreen extends BasicScreen {
	private static final float PADDING = 10;
	private static final Object[] widthItems = {2, 3, 4, 5, 6, 7, 8, 9, 10};
	private static final Object[] heightItems = {2, 3, 4, 5, 6, 7, 8};

	private final Stage stage = new Stage();
	private Level currentLevel = null;

	private SelectBox selectWidth;
	private SelectBox selectHeight;
	private TextButton createLevel;
	private TextButton back;

	public EditorCreateScreen(DontTouchIt game) {
		super(game);

		selectWidth = new SelectBox(widthItems, SkinUtils.skin);
		selectHeight = new SelectBox(heightItems, SkinUtils.skin);
		createLevel = new TextButton("Create", FontUtils.style);
		back = new TextButton("Back", FontUtils.style);

		selectWidth.setWidth(90);
		selectHeight.setWidth(90);
		createLevel.pack();
		back.pack();

		createLevel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				int columns = Integer.valueOf(selectWidth.getSelection());
				int rows = Integer.valueOf(selectHeight.getSelection());
				getGame().getEditorScreen().createLevel(columns, rows);
				getGame().setScreen(getGame().getEditorScreen());
			}
		});

		back.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGame().setScreen(getGame().getEditorMenuScreen());
			}
		});

		stage.addActor(selectWidth);
		stage.addActor(selectHeight);
		stage.addActor(createLevel);
		stage.addActor(back);
	}

	@Override
	public void show() {
		super.show();
		selectWidth.setSelection(0);
		selectHeight.setSelection(0);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		float cx = width / 2, cy = height / 2;

		selectWidth.setPosition(cx - selectWidth.getWidth() - 10, cy + 60);
		selectHeight.setPosition(cx + 10, cy + 60);

		createLevel.setPosition(cx - createLevel.getWidth() / 2, cy);
		back.setPosition(cx - back.getWidth() / 2, cy - 60);
	}

	@Override
	public void dispose() {
		stage.dispose();
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

