package com.github.donttouchit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {
	private static final String fontName = "AgentOrange.ttf";
	private static final FreeTypeFontGenerator generator;
	public static final BitmapFont menuFont;

	static {
		generator = new FreeTypeFontGenerator(Gdx.files.internal(fontName));
		menuFont = generator.generateFont(32);
	}
}
