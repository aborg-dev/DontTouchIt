package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.donttouchit.DontTouchIt;
import com.github.donttouchit.game.Level;
import com.github.donttouchit.game.LevelObject;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.GridPoint;
import com.github.donttouchit.screen.BasicScreen;
import com.github.donttouchit.utils.FontUtils;

public class EditorScreen extends BasicScreen {
	private static final float LEFT_PADDING = 20;
	private static final float TOP_PADDING = 20;
	private static final float RIGHT_PADDING = 20;
	private static final float BOTTOM_PADDING = 20;

	private static final float PALETTE_WIDTH = 150;
	private static final float PALETTE_PADDING = 20;

	private Level editingLevel = null;

	private final Stage levelStage = new Stage();
	private final Stage controlsStage = new Stage();
	private final Stage popupStage = new Stage();
	private final TextButton menu = new TextButton("M", FontUtils.style);

	private final Palette palette = new Palette() {
		@Override
		public void brushRequest(GridPoint p) {
			chooseBrush.setRequestBrushPosition(p);
			Gdx.input.setInputProcessor(popupStage);
			chooseBrush.setVisible(true);
		}
	};

	private EditingArea editingArea = new EditingArea() {
		private boolean placingWalls = false;

		@Override
		public void put(int column, int row, boolean moving) {
			LevelObject currentObject = getEditingLevel().getLevelObject(column, row);
			switch (palette.getCurrentBrush().getBrushType()) {
				case ERASER:
					if (!getEditingLevel().isPassable(column, row)) {
						getEditingLevel().setPassable(column, row, true);
					}
					if (currentObject != null) {
						getEditingLevel().removeLevelObject(currentObject);
					}
					break;
				case WALL:
					if (currentObject != null) {
						getEditingLevel().removeLevelObject(currentObject);
					}
					getEditingLevel().setPassable(column, row, !placingWalls);
					break;
				case LEVEL_OBJECT:
					if (!moving) {
						LevelObject newObject = palette.getCurrentBrush().createLevelObject();
						newObject.setBoardPosition(column, row);
						if (currentObject != null && currentObject.getClass() != newObject.getClass()) {
							getEditingLevel().removeLevelObject(currentObject);
							currentObject = null;
						}
						if (currentObject != null) {
							currentObject.changeParameter();
						} else {
							getEditingLevel().addLevelObject(newObject);
							getEditingLevel().setPassable(column, row, true);
						}
					}
					break;
			}
		}

		@Override
		public void change(int column, int row) {
			LevelObject currentObject = getEditingLevel().getLevelObject(column, row);
			if (currentObject != null) {
				chooseDye.setChangingObject(currentObject);
				Gdx.input.setInputProcessor(popupStage);
				chooseDye.setVisible(true);
			}
		}

		@Override
		public void start(int column, int row) {
			placingWalls = getEditingLevel().isPassable(column, row);
		}
	};

	private final ChooseBrush chooseBrush = new ChooseBrush() {
		@Override
		public void brushChosen(Brush brush) {
			setVisible(false);
			Gdx.input.setInputProcessor(controlsStage);
			palette.setPaletteBrush(getRequestBrushPosition(), brush);
		}
	};

	private final ChooseDye chooseDye = new ChooseDye() {
		@Override
		public void dyeChosen(Dye dye) {
			setVisible(false);
			Gdx.input.setInputProcessor(controlsStage);
			getChangingObject().setDye(dye);
		}
	};

	public EditorScreen(DontTouchIt game) {
		super(game);

		controlsStage.addActor(editingArea);
		controlsStage.addActor(palette);
		controlsStage.addActor(menu);

		menu.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGame().setScreen(getGame().getEditorMenuScreen());
			}
		});

		popupStage.addActor(chooseBrush);
		popupStage.addActor(chooseDye);
		chooseBrush.setVisible(false);
		chooseDye.setVisible(false);

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

		Vector2 v = palette.getMenuButtonPosition();
		menu.setPosition(v.x, v.y);
		menu.setSize(Level.CELL_SIZE, Level.CELL_SIZE);
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
		popupStage.draw();
	}

	@Override
	protected void update(float delta) {
		super.update(delta);
		controlsStage.act(delta);
		levelStage.act(delta);
		popupStage.act(delta);
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

