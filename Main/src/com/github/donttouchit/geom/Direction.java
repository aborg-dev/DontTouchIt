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

	public boolean isHorizontal() {
		return this == LEFT || this == RIGHT;
	}

	public boolean isVertical() {
		return this == TOP || this == BOTTOM;
	}

	public float angleTo(Direction direction) {
		if (this == Direction.NONE || direction == Direction.NONE) {
			throw new IllegalArgumentException("Angle to NONE direction is undefined");
		}
		int index1 = ordinal() - 1;
		int index2 = direction.ordinal() - 1;
		return ((index1 - index2 + 4) % 4) * 90;
	}

	public Direction plus(int rotations) {
		rotations = ((rotations % 4) + 4) % 4;
		int index = (ordinal() - 1 + rotations) % 4 + 1;
		return Direction.values()[index];
	}
}
