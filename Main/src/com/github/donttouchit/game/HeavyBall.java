package com.github.donttouchit.game;

import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.screen.editor.Brush;

public class HeavyBall extends Ball {

	public static class Specification extends Ball.Specification {
	}

	static {
		Specification specification = new Specification();
		specification.dye = Dye.GREEN;
		Brush.registerBrush(specification);
	}

	@Override
	public Specification getSpecification() {
		Specification specification = new Specification();
		specification.dye = getDye();
		specification.column = getColumn();
		specification.row = getRow();
		return specification;
	}

	public HeavyBall(Dye dye, int column, int row) {
		super(dye, column, row);
	}

	public HeavyBall(Specification specification) {
		this(specification.dye, specification.column, specification.row);
	}

}
