package com.github.donttouchit.actor.properties;

import com.badlogic.gdx.graphics.Color;

public enum Dye {
	RED,
	GREEN,
	BLUE;

	private static final Color[] color = {
		new Color(1, 0, 0, 1),
		new Color(0, 1, 0, 1),
		new Color(0, 0, 1, 1)
	};

	public Color getColor() {
		return color[ordinal()];
	}
}
