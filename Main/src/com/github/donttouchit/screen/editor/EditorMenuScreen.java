package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.donttouchit.DontTouchIt;
import com.github.donttouchit.screen.BasicScreen;
import com.github.donttouchit.utils.FileUtils;
import com.github.donttouchit.utils.FontUtils;

public class EditorMenuScreen extends BasicScreen {
	private static final float PADDING = 10;
	private final Stage stage = new Stage();

	private VerticalGroup buttonGroup = new VerticalGroup();
	private TextButton resume;
	private TextButton create;
	private TextButton save;
	private TextButton load;
	private TextButton back;

	public EditorMenuScreen(DontTouchIt game) {
		super(game);

		resume = new TextButton("Resume", FontUtils.style);
		create = new TextButton("New Level", FontUtils.style);
		save = new TextButton("Save", FontUtils.style);
		load = new TextButton("Load", FontUtils.style);
		back = new TextButton("Back", FontUtils.style);

		resume.pad(PADDING);
		create.pad(PADDING);
		save.pad(PADDING);
		load.pad(PADDING);
		back.pad(PADDING);

		resume.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGame().setScreen(getGame().getEditorScreen());
			}
		});

		create.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGame().setScreen(getGame().getEditorCreateScreen());
			}
		});

		save.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				FileUtils.saveLevel(getGame().getEditorScreen().getEditingLevel(), "test.lvl");
				System.err.println("Level should be saved");
			}
		});

		back.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGame().setScreen(getGame().getMenuScreen());
			}
		});

		stage.addActor(buttonGroup);
	}

	@Override
	public void show() {
		super.show();
		buttonGroup.clearChildren();

		if (getGame().getEditorScreen().getEditingLevel() != null) {
			buttonGroup.addActor(resume);
		}
		buttonGroup.addActor(create);
		if (getGame().getEditorScreen().getEditingLevel() != null) {
			buttonGroup.addActor(save);
		}
		buttonGroup.addActor(load);
		buttonGroup.addActor(back);
		buttonGroup.pack();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		buttonGroup.setPosition((width - buttonGroup.getWidth()) / 2, (height - buttonGroup.getHeight()) / 2);
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

