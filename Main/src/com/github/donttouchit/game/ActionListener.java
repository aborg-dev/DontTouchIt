package com.github.donttouchit.game;

import com.github.donttouchit.geom.GridPoint;

public interface ActionListener {
	void ballEntered(Ball ball, GridPoint cell);
	void ballLeft(Ball ball, GridPoint cell);
}
