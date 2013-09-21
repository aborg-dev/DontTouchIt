package com.github.donttouchit.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.github.donttouchit.game.Level;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class FileUtils {

	private static Json json = new Json();

	public static ArrayList<String> getLevelsList(String directory) {
		ArrayList<String> levelsFilenames = new ArrayList<String>();
		FileHandle[] files = Gdx.files.external(directory).list(".lvl");
		for(FileHandle file: files) {
			levelsFilenames.add(file.name());
		}
		return levelsFilenames;
	}

	public static void saveLevel(Level level, String filename) {
		Gdx.files.external(filename).writeString(json.toJson(level.getSpecification()), false);
	}

	public static Level loadLevel(String filename) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		return new Level(json.fromJson(Level.Specification.class, Gdx.files.external(filename).readString()));
	}
}
