package com.github.donttouchit.game;

import com.badlogic.gdx.math.GridPoint2;

public interface ActionListener {
	void ballEntered(Ball ball, GridPoint2 cell);
	void ballLeft(Ball ball, GridPoint2 cell);
}
