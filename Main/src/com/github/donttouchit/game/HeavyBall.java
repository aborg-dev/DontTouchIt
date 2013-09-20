package com.github.donttouchit.game;

import com.github.donttouchit.game.properties.Dye;

public class HeavyBall extends Ball {

	public static class Specification extends Ball.Specification {

	}

	public HeavyBall(Dye dye, int column, int row) {
		super(dye, column, row);
	}

	public HeavyBall(Specification specification) {
		this(specification.dye, specification.column, specification.row);
	}

}
