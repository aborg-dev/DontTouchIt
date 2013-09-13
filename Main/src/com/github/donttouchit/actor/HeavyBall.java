package com.github.donttouchit.actor;

import com.github.donttouchit.actor.properties.Dye;

public class HeavyBall extends Ball {
	
	public HeavyBall(Level level, Dye dye) {
		super(level, dye);
	}

	public HeavyBall(Level level, Dye dye, int column, int row) {
		super(level, dye, column, row);
	}
}
