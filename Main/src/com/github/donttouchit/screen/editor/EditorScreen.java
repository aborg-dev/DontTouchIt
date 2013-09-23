package com.github.donttouchit.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.github.donttouchit.DontTouchIt;
import com.github.donttouchit.game.Board;
import com.github.donttouchit.screen.BasicScreen;
import com.github.donttouchit.utils.FontUtils;

public class EditorScreen extends BasicScreen {
	private static final float LEFT_PADDING = 10;
	private static final float TOP_PADDING = 10;
	private static final float RIGHT_PADDING = 10;
	private static final float BOTTOM_PADDING = 10;

	private static final float PALETTE_WIDTH = 150;
	private static final float PALETTE_PADDING = 150;

	private Stage levelStage = new Stage();
	private Stage controlsStage = new Stage();
	private Palette palette = new Palette();
	private Board board = null;

	public EditorScreen(DontTouchIt game) {
		super(game);

		Pixmap blackPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		Pixmap greenPixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
		greenPixmap.setColor(Color.GREEN);
		greenPixmap.fillRectangle(0, 0, 32, 32);
		greenPixmap.setColor(Color.BLACK);
		greenPixmap.drawCircle(16, 16, 16);
		blackPixmap.setColor(Color.BLACK);


		Drawable bs = new SpriteDrawable(new Sprite(new Texture(blackPixmap)));
		Drawable gs = new SpriteDrawable(new Sprite(new Texture(greenPixmap)));
		ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle(bs, bs, bs, bs, bs);
		List.ListStyle listStyle = new List.ListStyle(FontUtils.smallFont, Color.YELLOW, Color.GRAY, gs);

		SelectBox.SelectBoxStyle style = new SelectBox.SelectBoxStyle();
		style.font = FontUtils.menuFont;
		style.background = gs;
		style.scrollStyle = scrollPaneStyle;
		style.listStyle = listStyle;

		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

		Object[] items = {1000, 2, 3, 4, 5, 6, 7, 800};
		SelectBox selectBox = new SelectBox(items, skin);
		selectBox.setPosition(100, 300);

		controlsStage.addActor(selectBox);
	}

	@Override
	public void resize(int width, int height) {
		levelStage.setViewport(width, height, true);
		controlsStage.setViewport(width, height, true);

		float x = width - LEFT_PADDING - RIGHT_PADDING + PALETTE_PADDING - PALETTE_WIDTH;
		float y = BOTTOM_PADDING;
		palette.setPosition(x, y);
		palette.setSize(PALETTE_WIDTH, height - TOP_PADDING - BOTTOM_PADDING);

		if (false) {
			board.setPosition(LEFT_PADDING, BOTTOM_PADDING);
			board.setWidth(width - LEFT_PADDING - RIGHT_PADDING - PALETTE_PADDING - PALETTE_WIDTH);
			board.setHeight(height - TOP_PADDING - BOTTOM_PADDING);
		}
	}

	@Override
	public void dispose() {
		levelStage.dispose();
		controlsStage.dispose();
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(controlsStage);
	}

	@Override
	protected void draw() {
		super.draw();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		controlsStage.draw();
		levelStage.draw();
	}

	@Override
	protected void update(float delta) {
		super.update(delta);
		controlsStage.act();
		levelStage.act();
	}
}

