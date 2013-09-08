package com.github.donttouchit.actor;

import com.badlogic.gdx.scenes.scene2d.Group;

public final class Level extends Group {
	private boolean[][] passability;

	public Level(int width, int height) {
		passability = new boolean[width][height];
	}
}
