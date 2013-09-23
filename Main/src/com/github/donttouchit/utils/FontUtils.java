package com.github.donttouchit.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontUtils {
	private static final String fontName = "AgentOrange.ttf";
	private static final FreeTypeFontGenerator generator;
	public static final BitmapFont menuFont;
	public static final BitmapFont smallFont;

	static {
		generator = new FreeTypeFontGenerator(Gdx.files.internal(fontName));
		menuFont = generator.generateFont(32);
		smallFont = generator.generateFont(14);
	}
}
