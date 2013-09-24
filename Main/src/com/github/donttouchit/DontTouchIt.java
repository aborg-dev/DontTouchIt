package com.github.donttouchit;

import com.badlogic.gdx.Game;
import com.github.donttouchit.game.*;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.Direction;
import com.github.donttouchit.geom.GridPoint;
import com.github.donttouchit.screen.ChooseLevelScreen;
import com.github.donttouchit.screen.GameScreen;
import com.github.donttouchit.screen.MenuScreen;
import com.github.donttouchit.screen.editor.EditorCreateScreen;
import com.github.donttouchit.screen.editor.EditorMenuScreen;
import com.github.donttouchit.screen.editor.EditorScreen;
import com.github.donttouchit.utils.FileUtils;

public class DontTouchIt extends Game {
	private MenuScreen menuScreen;
	private ChooseLevelScreen chooseLevelScreen;
	private GameScreen gameScreen;

	private EditorScreen editorScreen;
	private EditorMenuScreen editorMenuScreen;
	private EditorCreateScreen editorCreateScreen;

	public void createDefaultLevel() {
		Level level = new Level(12, 8, new GridPoint(0, 6), new GridPoint(6, 6));


		level.addLevelObject(new PressurePlate(Dye.GREEN, 3, 0));
		level.addLevelObject(new ImaginaryWall(Dye.GREEN, 4, 0));

		level.addLevelObject(new Arrow(Dye.GREEN, 2, 2, Direction.LEFT, 1));
		level.addLevelObject(new Arrow(Dye.GREEN, 4, 2, Direction.BOTTOM, 2));

		level.addLevelObject(new Pedestal(Dye.RED, 3, 3));
		level.addLevelObject(new Pedestal(Dye.BLUE, 7, 4));

		level.addLevelObject(new HeavyBall(Dye.RED, 1, 1));
		level.addLevelObject(new LightBall(Dye.BLUE, 1, 5));

		FileUtils.saveLevel(level, "./test.lvl");
	}

	@Override
	public void create() {
		createDefaultLevel();
		menuScreen = new MenuScreen(this);
		chooseLevelScreen = new ChooseLevelScreen(this);
		gameScreen = new GameScreen(this);

		editorScreen = new EditorScreen(this);
		editorMenuScreen = new EditorMenuScreen(this);
		editorCreateScreen = new EditorCreateScreen(this);
		setScreen(menuScreen);
	}

	public MenuScreen getMenuScreen() {
		return menuScreen;
	}

	public ChooseLevelScreen getChooseLevelScreen() {
		return chooseLevelScreen;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public EditorScreen getEditorScreen() {
		return editorScreen;
	}

	public EditorMenuScreen getEditorMenuScreen() {
		return editorMenuScreen;
	}

	public EditorCreateScreen getEditorCreateScreen() {
		return editorCreateScreen;
	}
}
