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
				put((int)(x / Level.CELL_SIZE), (int)(y / Level.CELL_SIZE), false);
			}

			@Override
			public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
				start((int)(x / Level.CELL_SIZE), (int)(y / Level.CELL_SIZE));
			}

			@Override
			public boolean longPress(Actor actor, float x, float y) {
				change((int) (x / Level.CELL_SIZE), (int) (y / Level.CELL_SIZE));
				return true;
			}

			@Override
			public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
				if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
					put((int)(x / Level.CELL_SIZE), (int)(y / Level.CELL_SIZE), true);
				}
			}
		});
	}

	public void setDimension(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
	}

	public abstract void start(int column, int row);

	public abstract void put(int column, int row, boolean moving);

	public abstract void change(int column, int row);
}
