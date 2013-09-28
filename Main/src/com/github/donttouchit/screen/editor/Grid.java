package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.donttouchit.geom.GridPoint;

public class Grid extends Actor {
	protected float cellWidth = 64;
	protected float cellHeight = 64;
	protected int widthInCells = 2;
	protected int heightInCells = 6;

	protected float getHorizontalPadding() {
		return (getWidth() - cellWidth * widthInCells) / (widthInCells + 1);
	}

	protected float getVerticalPadding() {
		return (getHeight() - cellWidth * heightInCells) / (heightInCells + 1);
	}

	protected Vector2 gridPointToPoint(GridPoint p) {
		float hPadding = getHorizontalPadding();
		float vPadding = getVerticalPadding();
		float x = p.x * (cellWidth + hPadding) + hPadding;
		float y = getHeight() - ((p.y + 1) * (cellHeight + vPadding));
		return new Vector2(x, y);
	}

	protected GridPoint pointToGridPoint(float x, float y) {
		y = getHeight() - y;
		float hPadding = getHorizontalPadding();
		float vPadding = getVerticalPadding();

		int cx = (int)(x / (cellWidth + hPadding)), cy = (int)(y / (cellHeight + vPadding));
		x %= (cellWidth + hPadding);
		y %= (cellHeight + vPadding);
		if (x < hPadding || y < vPadding) {
			return null;
		}
		if (cx < 0 || cx >= widthInCells || cy < 0 || cy >= heightInCells) {
			return null;
		}

		return new GridPoint(cx, cy);
	}

	protected final GridPoint indexToGridPoint(int index) {
		return new GridPoint(index % widthInCells, index / widthInCells);
	}

	protected final int gridPointToIndex(GridPoint p) {
		return p.y * widthInCells + p.x;
	}

	protected final Vector2 indexToPoint(int index) {
		if (index < 0 || index >= widthInCells * heightInCells) {
			throw new IndexOutOfBoundsException("This index is not on the grid");
		}
		return gridPointToPoint(indexToGridPoint(index));
	}
}
