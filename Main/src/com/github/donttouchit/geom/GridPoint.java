package com.github.donttouchit.geom;

public class GridPoint implements Comparable<GridPoint> {
	public int x;
	public int y;

	public GridPoint() {
	}

	public GridPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GridPoint(GridPoint point) {
		this.x = point.x;
		this.y = point.y;
	}

	public GridPoint set(GridPoint point) {
		this.x = point.x;
		this.y = point.y;
		return this;
	}

	public GridPoint set(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public void add(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public void add(GridPoint p) {
		this.x += p.x;
		this.y += p.y;
	}

	public void subtract(int x, int y) {
		this.x -= x;
		this.y -= y;
	}

	public void subtract(GridPoint p) {
		this.x -= p.x;
		this.y -= p.y;
	}

	public void scale(int factor) {
		x *= factor;
		y *= factor;
	}

	@Override
	public int compareTo(GridPoint o) {
		if (x != o.x) return ((Integer)x).compareTo(o.x);
		return ((Integer)y).compareTo(o.y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof GridPoint)) return false;

		GridPoint gridPoint = (GridPoint) o;

		if (x != gridPoint.x) return false;
		if (y != gridPoint.y) return false;

		return true;
	}
}
