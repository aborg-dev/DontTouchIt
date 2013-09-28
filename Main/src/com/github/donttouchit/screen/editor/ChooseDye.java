package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.donttouchit.game.Level;
import com.github.donttouchit.game.LevelObject;
import com.github.donttouchit.game.properties.Dye;
import com.github.donttouchit.geom.GridPoint;

public abstract class ChooseDye extends Grid {
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private LevelObject changingObject;

	public ChooseDye() {
		widthInCells = Dye.values().length;
		heightInCells = 1;
		setWidth(Gdx.graphics.getWidth() * 0.66f);
		setHeight(80);
		setX((Gdx.graphics.getWidth() - getWidth()) / 2);
		setY((Gdx.graphics.getHeight() - getHeight()) / 2);

		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GridPoint p = pointToGridPoint(x, y);
				if (p != null && gridPointToIndex(p) < widthInCells) {
					dyeChosen(Dye.values()[gridPointToIndex(p)]);
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

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for (Dye dye : Dye.values()) {
			shapeRenderer.setColor(dye.getColor());
			Vector2 v = indexToPoint(dye.ordinal());
			shapeRenderer.rect(v.x, v.y, Level.CELL_SIZE, Level.CELL_SIZE);
		}
		shapeRenderer.end();

		batch.begin();
	}

	public LevelObject getChangingObject() {
		return changingObject;
	}

	public void setChangingObject(LevelObject changingObject) {
		this.changingObject = changingObject;
	}

	public abstract void dyeChosen(Dye dye);
}
