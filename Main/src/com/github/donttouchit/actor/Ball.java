package com.github.donttouchit.actor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.github.donttouchit.actor.properties.Dye;
import com.github.donttouchit.geom.Direction;

import java.awt.*;

public abstract class Ball extends LevelObject {
	private float dx = 0, dy = 0;
	private float speedInCells = 3.0f;
	private Direction moveDirection = Direction.NONE;
	private Dye dye;

	public Ball(Level level, Dye dye) {
		super(level);
		this.dye = dye;

		addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				System.err.println("Tapped!");
			}

			@Override
			public void fling(InputEvent event, float velocityX, float velocityY, int button) {
				System.err.println("Flinged! " + velocityX + " " + velocityY);
				Direction direction;
				if (Math.abs(velocityX) > Math.abs(velocityY)) {
					direction = velocityX > 0 ? Direction.RIGHT : Direction.LEFT;
				} else {
					direction = velocityY > 0 ? Direction.TOP : Direction.BOTTOM;
				}
				move(direction);
			}
		});
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (moveDirection != Direction.NONE) {
			Vector2 dir = moveDirection.getVector2();
			dir.scl(speedInCells * delta);

			dx += dir.x;
			dy += dir.y;

			if (Math.abs(dx) >= 1 || Math.abs(dy) >= 1) {
				if (Math.abs(dx) >= 1) {
					setColumn(getColumn() + (int)Math.signum(dx));
					dx -= Math.signum(dx);
				}
				if (Math.abs(dy) >= 1) {
					setRow(getRow() + (int)Math.signum(dy));
					dy -= Math.signum(dy);
				}

				if (!mayMove(getMoveDirection())) {
					stop();
				}
			}
		}
	}

	protected boolean mayMove(Direction direction) {
		if (direction == Direction.NONE) {
			return true;
		}
		Point p = direction.getPoint();
		p.translate(getColumn(), getRow());
		return level.isEmpty(p.x, p.y);
	}

	public Vector2 getCenter() {
		return new Vector2((dx + 0.5f) * Level.CELL_SIZE, (dy + 0.5f) * Level.CELL_SIZE);
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

	public void stop() {
		moveDirection = Direction.NONE;
		dx = 0;
		dy = 0;
	}

	public void move(Direction moveDirection) {
		if (this.moveDirection == Direction.NONE && mayMove(moveDirection)) {
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
