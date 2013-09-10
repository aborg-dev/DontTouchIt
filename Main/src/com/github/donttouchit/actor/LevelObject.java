package com.github.donttouchit.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class LevelObject extends Actor {
	private Level level;
	private int column = 0, row = 0;

	public LevelObject(Level level) {
		this.level = level;
	}

	public void setBoardPosition(int column, int row) {
		setColumn(column);
		setRow(row);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
		setX(this.column * level.cellSize);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
		setY(this.row * level.cellSize);
	}
}
