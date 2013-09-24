package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.github.donttouchit.game.Level;

public abstract class EditingArea extends Actor {
	private int columns;
	private int rows;

	public EditingArea() {
		addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				put((int)(x / Level.CELL_SIZE), (int)(y / Level.CELL_SIZE));
			}

			@Override
			public boolean longPress(Actor actor, float x, float y) {
				change((int)(x / Level.CELL_SIZE), (int)(y / Level.CELL_SIZE));
				return true;
			}
		});
	}

	public void setDimension(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
	}

	public abstract void put(int column, int row);

	public abstract void change(int column, int row);
}
