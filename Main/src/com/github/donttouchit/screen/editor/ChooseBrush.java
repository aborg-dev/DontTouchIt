package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.donttouchit.game.Level;
import com.github.donttouchit.geom.GridPoint;

import java.util.List;

public abstract class ChooseBrush extends Grid {
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private final List<Brush> brushes;
	private GridPoint requestBrushPosition = null;

	public ChooseBrush() {
		widthInCells = 8;
		heightInCells = 4;
		setWidth(Gdx.graphics.getWidth() * 0.66f);
		setHeight(Gdx.graphics.getHeight() * 0.66f);
		setX((Gdx.graphics.getWidth() - getWidth()) / 2);
		setY((Gdx.graphics.getHeight() - getHeight()) / 2);
		brushes = Brush.getRegisteredBrushes();

		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GridPoint p = pointToGridPoint(x, y);
				if (p != null && gridPointToIndex(p) < brushes.size()) {
					brushChosen(brushes.get(gridPointToIndex(p)));
				}
			}
		});
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();

		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX(), getY(), 0);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.YELLOW);
		shapeRenderer.rect(0, 0, getWidth(), getHeight());
		shapeRenderer.end();

		Gdx.gl.glLineWidth(3.0f);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.ORANGE);
		shapeRenderer.rect(0, 0, getWidth(), getHeight());
		shapeRenderer.end();
		Gdx.gl.glLineWidth(1.0f);

		batch.begin();

		int index = 0;
		for (Brush brush : brushes) {
			Vector2 v = indexToPoint(index);
			brush.draw(batch, getX() + v.x, getY() + v.y, Level.CELL_SIZE, Level.CELL_SIZE);
			index++;
		}
	}

	public GridPoint getRequestBrushPosition() {
		return requestBrushPosition;
	}

	public void setRequestBrushPosition(GridPoint requestBrushPosition) {
		this.requestBrushPosition = requestBrushPosition;
	}

	public abstract void brushChosen(Brush brush);
}
