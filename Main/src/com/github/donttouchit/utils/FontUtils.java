package com.github.donttouchit.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class FontUtils {
	private static final String fontPath = "resources/AgentOrange.ttf";
	private static final FreeTypeFontGenerator generator;
	public static final BitmapFont menuFont;
	public static final BitmapFont smallFont;
	public static final TextButton.TextButtonStyle style;

	static {
		generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
		menuFont = generator.generateFont(32);
		smallFont = generator.generateFont(14);

		style = new TextButton.TextButtonStyle();
		style.font = FontUtils.menuFont;
		style.downFontColor = Color.ORANGE;
		style.fontColor = Color.YELLOW;
	}
}
