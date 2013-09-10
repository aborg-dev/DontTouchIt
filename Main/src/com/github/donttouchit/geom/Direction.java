package com.github.donttouchit.geom;

import com.badlogic.gdx.math.Vector2;
import java.awt.Point;

public enum Direction {
	NONE,
	TOP,
	RIGHT,
	BOTTOM,
	LEFT;

	private static final int[] deltaX = {0, 0, 1, 0, -1};
	private static final int[] deltaY = {0, 1, 0, -1, 0};

	public Vector2 getVector2() {
		return new Vector2(deltaX[ordinal()], deltaY[ordinal()]);
	}

	public Point getPoint() {
		return new Point(deltaX[ordinal()], deltaY[ordinal()]);
	}

}
