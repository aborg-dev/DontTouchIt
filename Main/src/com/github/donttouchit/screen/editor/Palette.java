package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public abstract class Palette extends Actor {
	private List<BrushType> brushTypes = new ArrayList<BrushType>();
	private int currentBrush = 0;

	public Palette() {
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	public BrushType getBrush() {
		return null;
	}
}
