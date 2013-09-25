package com.github.donttouchit.screen.editor;

import com.github.donttouchit.game.LevelObject;

public class Brush {
	private BrushType brushType;
	private LevelObject.Specification specification;

	public Brush(BrushType brushType, LevelObject.Specification specification) {
		if (brushType == BrushType.LEVEL_OBJECT && specification == null) {
			throw new IllegalArgumentException("LEVEL_OBJECT brush with null specification");
		}
		setBrushType(brushType);
		setSpecification(specification);
	}

	public BrushType getBrushType() {
		return brushType;
	}

	public void setBrushType(BrushType brushType) {
		this.brushType = brushType;
	}

	public LevelObject.Specification getSpecification() {
		return specification;
	}

	public void setSpecification(LevelObject.Specification specification) {
		this.specification = specification;
	}
}
