package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.github.donttouchit.game.LevelObject;

import java.util.ArrayList;
import java.util.List;

public class Brush extends BaseDrawable {
	private final ShapeRenderer shapeRenderer = new ShapeRenderer();

	private final BrushType brushType;
	private final LevelObject.Specification specification;
	private final LevelObject levelObject;

	private static final List<Brush> registeredBrushes = new ArrayList<Brush>();
	public static void registerBrush(LevelObject.Specification specification) {
		registeredBrushes.add(new Brush(BrushType.LEVEL_OBJECT, specification));
	}

	public static List<Brush> getRegisteredBrushes() {
		return registeredBrushes;
	}

	static {
		registeredBrushes.add(new Brush(BrushType.WALL, null));
	}

	public Brush(BrushType brushType, LevelObject.Specification specification) {
		if (brushType == BrushType.LEVEL_OBJECT && specification == null) {
			throw new IllegalArgumentException("LEVEL_OBJECT brush with null specification");
		}
		this.brushType = brushType;
		this.specification = specification;
		levelObject = createLevelObject();
	}

	public BrushType getBrushType() {
		return brushType;
	}

	public LevelObject.Specification getSpecification() {
		return specification;
	}

	public LevelObject createLevelObject() {
		if (specification == null) {
			return null;
		}
		return specification.createLevelObject();
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y, float width, float height) {
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(x, y, 0);

		switch (brushType) {
			case ERASER:
				shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
				shapeRenderer.setColor(1, 0, 0, 1);
				shapeRenderer.line(0, 0, width, height);
				shapeRenderer.line(0, height, width, 0);
				shapeRenderer.line(0, 0, width, 0);
				shapeRenderer.line(width, 0, width, height);
				shapeRenderer.line(width, height, 0, height);
				shapeRenderer.line(0, height, 0, 0);
				shapeRenderer.end();
				break;
			case WALL:
				shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
				shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
				shapeRenderer.rect(0, 0, width, height);
				shapeRenderer.end();
				break;
			case LEVEL_OBJECT:
				levelObject.setPosition(x, y);
				levelObject.draw(batch, 1.0f);
				break;
		}
	}
}
