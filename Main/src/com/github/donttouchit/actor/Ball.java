package com.github.donttouchit.actor;

import com.badlogic.gdx.math.Vector2;
import com.github.donttouchit.actor.properties.Dye;
import com.github.donttouchit.geom.Direction;

public abstract class Ball extends LevelObject {
	private float dx = 0, dy = 0;
	private float speedInCells = 1.0f;
	private Direction moveDirection = Direction.NONE;
	private Dye dye;

	public Ball(Level level, Dye dye) {
		super(level);
		this.dye = dye;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (moveDirection != Direction.NONE) {
//			System.err.println(getX() + " " + getY());
			Vector2 dir = moveDirection.getVector2();
			dir.scl(speedInCells * delta);
			dx = Math.min(dx + dir.x, 1);
			dy = Math.min(dy + dir.y, 1);
			if (Math.abs((int)dx) == 1 || Math.abs((int)dy) == 1) {
				setColumn(getColumn() + (int)dx);
				setRow(getRow() + (int)dy);
//				System.err.println((int)dx + " " + (int)dy);
				dx = 0;
				dy = 0;
				moveDirection = Direction.NONE;
			}
		}
	}

	public float getSpeedInCells() {
		return speedInCells;
	}

	public void setSpeedInCells(float speedInCells) {
		this.speedInCells = speedInCells;
	}

	public Dye getDye() {
		return dye;
	}

	public void setDye(Dye dye) {
		this.dye = dye;
	}

	public Direction getMoveDirection() {
		return moveDirection;
	}

	public void move(Direction moveDirection) {
		if (this.moveDirection == Direction.NONE) {
			this.moveDirection = moveDirection;
		}
	}

	public float getDx() {
		return dx;
	}

	public float getDy() {
		return dy;
	}
}
