package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.github.donttouchit.geom.GridPoint;

public abstract class Palette extends Grid {
	private static final int MAX_BRUSHES_IN_WIDTH = 2;
	private static final int MAX_BRUSHES_IN_HEIGHT = 6;
	private static final float BRUSH_SIZE = 64;

	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private final Brush eraser = new Brush(BrushType.ERASER, null);

	private Brush[][] brushes = new Brush[MAX_BRUSHES_IN_WIDTH][MAX_BRUSHES_IN_HEIGHT];
	private GridPoint currentBrush = new GridPoint(0, 0);

	public Palette() {
		for (int x = 0; x < MAX_BRUSHES_IN_WIDTH; ++x) {
			for (int y = 0; y < MAX_BRUSHES_IN_HEIGHT; ++y) {
				brushes[x][y] = null;
			}
		}
		brushes[0][0] = eraser;

		addListener(new ActorGestureListener() {
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button) {
				GridPoint p = pointToGridPoint(x, y);
				if (p != null) {
					if (p.y == 0) {
						if (p.x == 0) {
							selectBrush(0, 0);
						}
					} else {
						if (brushes[p.x][p.y] == null) {
							p.subtract(0, 1);
							brushRequest(p);
						} else {
							selectBrush(p.x, p.y);
						}
					}
				}
			}

			@Override
			public boolean longPress(Actor actor, float x, float y) {
				GridPoint p = pointToGridPoint(x, y);
				if (p != null && p.y > 0) {
					p.subtract(0, 1);
					brushRequest(p);
				}
				return true;
			}
		});
	}

	public abstract void brushRequest(GridPoint p);

	private void drawBorder(float x, float y, SpriteBatch batch, boolean selected) {
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(x, y, 0);

		Gdx.gl.glLineWidth(3.0f);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		if (selected) {
			shapeRenderer.setColor(Color.YELLOW);
		} else {
			shapeRenderer.setColor(Color.GRAY);
		}
		shapeRenderer.line(0, 0, BRUSH_SIZE, 0);
		shapeRenderer.line(BRUSH_SIZE, 0, BRUSH_SIZE, BRUSH_SIZE);
		shapeRenderer.line(BRUSH_SIZE, BRUSH_SIZE, 0, BRUSH_SIZE);
		shapeRenderer.line(0, BRUSH_SIZE, 0, 0);
		shapeRenderer.end();
		Gdx.gl.glLineWidth(1.0f);

		batch.begin();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		for (int x = 0; x < MAX_BRUSHES_IN_WIDTH; ++x) {
			for (int y = 0; y < MAX_BRUSHES_IN_HEIGHT; ++y) {
				Vector2 v = gridPointToPoint(new GridPoint(x, y));
				boolean selected = currentBrush.equals(new GridPoint(x, y));
				if (y > 0 || x == 0) {
					drawBorder(getX() + v.x, getY() + v.y, batch, selected);
				}
				if (brushes[x][y] != null) {
					brushes[x][y].draw(batch, getX() + v.x, getY() + v.y, BRUSH_SIZE, BRUSH_SIZE);
				}
			}
		}
	}

	public void setPaletteBrush(GridPoint p, Brush brush) {
		p.add(0, 1);
		brushes[p.x][p.y] = brush;
		selectBrush(p.x, p.y);
	}

	public Brush getCurrentBrush() {
		return brushes[currentBrush.x][currentBrush.y];
	}

	public void selectBrush(int x, int y) {
		currentBrush = new GridPoint(x, y);
	}

	public Vector2 getMenuButtonPosition() {
		Vector2 v = gridPointToPoint(new GridPoint(1, 0));
		return new Vector2(getX() + v.x, getY() + v.y);
	}
}
