package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.donttouchit.DontTouchIt;
import com.github.donttouchit.game.Level;
import com.github.donttouchit.geom.GridPoint;
import com.github.donttouchit.screen.BasicScreen;

public class EditorScreen extends BasicScreen {
	private static final float LEFT_PADDING = 10;
	private static final float TOP_PADDING = 10;
	private static final float RIGHT_PADDING = 10;
	private static final float BOTTOM_PADDING = 10;

	private static final float PALETTE_WIDTH = 150;
	private static final float PALETTE_PADDING = 150;

	private Level editingLevel = null;
	private EditingArea editingArea = new EditingArea() {
		@Override
		public void put(int column, int row) {
		}

		@Override
		public void change(int column, int row) {
		}
	};

	private final Stage levelStage = new Stage();
	private final Stage controlsStage = new Stage();
	private final Palette palette = new Palette();

	public EditorScreen(DontTouchIt game) {
		super(game);

		controlsStage.addActor(editingArea);
		controlsStage.addActor(palette);
	}

	@Override
	public void resize(int width, int height) {
		levelStage.setViewport(width, height, true);
		controlsStage.setViewport(width, height, true);

		{
			float x = width - LEFT_PADDING - RIGHT_PADDING + PALETTE_PADDING - PALETTE_WIDTH;
			float y = BOTTOM_PADDING;
			palette.setPosition(x, y);
			palette.setSize(PALETTE_WIDTH, height - TOP_PADDING - BOTTOM_PADDING);
		}

		{
			float w = width - LEFT_PADDING - RIGHT_PADDING - PALETTE_PADDING - PALETTE_WIDTH;
			float h = height - TOP_PADDING - BOTTOM_PADDING;

			getEditingLevel().setBounds(new Vector2(w, h));

			Group group = editingLevel.getGroup();
			float x = LEFT_PADDING + (w - group.getWidth()) / 2;
			float y = BOTTOM_PADDING + (h - group.getHeight()) / 2;

			group.setPosition(x, y);
			editingArea.setSize(group.getWidth(), group.getHeight());
			editingArea.setScale(group.getScaleX(), group.getScaleY());
			editingArea.setPosition(x, y);
		}
	}

	@Override
	public void dispose() {
		levelStage.dispose();
		controlsStage.dispose();
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(controlsStage);
		Level level = getEditingLevel();
		if (editingLevel == null) {
			throw new IllegalStateException("Level is not set");
		}

		levelStage.clear();
		levelStage.addActor(level.getGroup());
	}

	@Override
	protected void draw() {
		super.draw();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		controlsStage.draw();
		levelStage.draw();
	}

	@Override
	protected void update(float delta) {
		super.update(delta);
		controlsStage.act();
		levelStage.act();
	}

	public void createLevel(int columns, int rows) {
		GridPoint enter = new GridPoint(0, 0);
		GridPoint exit = new GridPoint(columns - 1, rows - 1);
		Level level = new Level(columns, rows, enter, exit);
		setEditingLevel(level);
		editingArea.setDimension(columns, rows);
	}

	public Level getEditingLevel() {
		return editingLevel;
	}

	public void setEditingLevel(Level editingLevel) {
		this.editingLevel = editingLevel;
	}
}

