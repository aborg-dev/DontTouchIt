package com.github.donttouchit.actor;

public class LevelObject {
	private Level level;
	private boolean visible = false;
	private int x = 0, y = 0;

	public LevelObject(Level level) {
		this.level = level;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
