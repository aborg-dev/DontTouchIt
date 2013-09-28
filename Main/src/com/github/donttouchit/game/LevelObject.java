package com.github.donttouchit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.GridPoint;

import java.lang.reflect.Constructor;

public abstract class LevelObject extends Actor implements Comparable<LevelObject> {
	private Dye dye;
	private Level level;
	private int column = 0, row = 0;

	public LevelObject(int column, int row, Dye dye) {
		setWidth(Level.CELL_SIZE);
		setHeight(Level.CELL_SIZE);
		setColumn(column);
		setRow(row);
		setDye(dye);
	}

	public static class Specification {
		protected int column;
		protected int row;
		protected Dye dye;

		public final LevelObject createLevelObject() {
			Class specClass = getClass();
			Constructor constructor = null;
			try {
				constructor = this.getClass().getEnclosingClass().getConstructor(specClass);
				return (LevelObject) constructor.newInstance(this);
			} catch (Exception e) {
				Gdx.app.error("Level loading", "Specification is invalid. Null is returned.");
				return null;
			}
		}
	}

	@Override
	public final int compareTo(LevelObject o) {
		return getDepth().compareTo(o.getDepth());
	}

	public abstract Specification getSpecification();

	public abstract Integer getDepth();

	public LevelObject(Specification specification) {
		this(specification.column, specification.row, specification.dye);
	}

	public void setBoardPosition(int column, int row) {
		setColumn(column);
		setRow(row);
	}

	public GridPoint getBoardPosition() {
		return new GridPoint(column, row);
	}

	public boolean isPassable(int column, int row) {
		return this.column != column || this.row != row;
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
		setX(this.column * Level.CELL_SIZE);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
		setY(this.row * Level.CELL_SIZE);
	}

	public Vector2 getCenter() {
		return new Vector2(0.5f * Level.CELL_SIZE, 0.5f * Level.CELL_SIZE);
	}

	public Dye getDye() {
		return dye;
	}

	public void setDye(Dye dye) {
		this.dye = dye;
	}

	public void changeParameter() {
	}
}
