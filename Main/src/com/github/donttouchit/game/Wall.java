package com.github.donttouchit.game;

/**
 * User: iiotep9huy
 * Date: 9/10/13
 * Time: 2:42 PM
 * Project: Don'tTouchIt
 */
public class Wall extends LevelObject {
	public Wall(Level level, int column, int row) {
		super(level, column, row);
		setHeight(Level.CELL_SIZE);
		setWidth(Level.CELL_SIZE);
	}
}
