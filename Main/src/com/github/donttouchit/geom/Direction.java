package com.github.donttouchit.geom;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
	NONE,
	TOP,
	RIGHT,
	BOTTOM,
	LEFT;

	private static final float[] deltaX = {0, 0, 1, 0, -1};
	private static final float[] deltaY = {0, 1, 0, -1, 0};

	public Vector2 getVector2() {
		return new Vector2(deltaX[ordinal()], deltaY[ordinal()]);
	}
}
