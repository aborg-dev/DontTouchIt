package com.github.donttouchit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.github.donttouchit.game.HeavyBall;
import com.github.donttouchit.game.Level;
import com.github.donttouchit.game.LevelObject;
import com.github.donttouchit.game.Pedestal;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.GridPoint;
import com.github.donttouchit.screen.MenuScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DontTouchIt extends Game {
	@Override
	public void create() {
		List<LevelObject> levelObjects = new ArrayList<LevelObject>();
		levelObjects.add(new HeavyBall(Dye.RED, 1, 1));
		levelObjects.add(new Pedestal(Dye.RED, 2, 2));

		int columns = 6, rows = 6;
		GridPoint enter = new GridPoint(0, 0);
		GridPoint exit = new GridPoint(5, 5);
		boolean[][] passable = new boolean[6][6];
		for (boolean[] array : passable) {
			Arrays.fill(array, true);
		}

		Level level = new Level(columns, rows, enter, exit);
		Json json = new Json(JsonWriter.OutputType.json);
		Level.Specification specification = json.fromJson(Level.Specification.class, json.prettyPrint(level.getSpecification()));

		System.err.println(json.prettyPrint(specification));

		setScreen(new MenuScreen(this));
	}
}
