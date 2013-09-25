package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.donttouchit.DontTouchIt;
import com.github.donttouchit.game.Level;
import com.github.donttouchit.geom.GridPoint;
import com.github.donttouchit.screen.BasicScreen;

public class EditorScreen extends BasicScreen {
	private static final float LEFT_PADDING = 20;
	private static final float TOP_PADDING = 20;
	private static final float RIGHT_PADDING = 20;
	private static final float BOTTOM_PADDING = 20;

	private static final float PALETTE_WIDTH = 150;
	private static final float PALETTE_PADDING = 20;

	private static enum EditorMode {
		BACKGROUND,
		CONTENT
	}

	private EditorMode editorMode = EditorMode.BACKGROUND;
	private Level editingLevel = null;

	private final Stage levelStage = new Stage();
	private final Stage controlsStage = new Stage();
	private final Palette palette = new Palette(){};
	private EditingArea editingArea = new EditingArea() {
		private boolean placingWalls = false;

		@Override
		public void put(int column, int row) {
			if (editorMode == EditorMode.BACKGROUND) {
				getEditingLevel().setPassable(column, row, !placingWalls);
				Gdx.app.log("Level editor", "passability of " + column + " " + row + "has been changed");
			}
		}

		@Override
		public void change(int column, int row) {
		}

		@Override
		public void start(int column, int row) {
			placingWalls = getEditingLevel().isPassable(column, row);
		}
	};

	public EditorScreen(DontTouchIt game) {
		super(game);

		controlsStage.addActor(editingArea);
		controlsStage.addActor(palette);

		editingArea.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.err.println("editingArea clicked!");
			}
		});
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

			getEditingLevel().setBounds(LEFT_PADDING, BOTTOM_PADDING, w, h);
			Group group = editingLevel.getGroup();

			editingArea.setSize(group.getWidth(), group.getHeight());
			editingArea.setScale(group.getScaleX(), group.getScaleY());
			editingArea.setPosition(group.getX(), group.getY());

			System.err.println("Editing area at " + editingArea.getX() + " " + editingArea.getY());
			System.err.println("Editing area is " + editingArea.getWidth() + " " + editingArea.getHeight());
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
		if (getEditingLevel() == null) {
			throw new IllegalStateException("Level is not set");
		}
		levelStage.clear();
		levelStage.addActor(getEditingLevel().getGroup());
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

