package com.github.donttouchit;

import com.badlogic.gdx.Game;
import com.github.donttouchit.screen.GameScreen;

/**
 * User: iiotep9huy
 * Date: 9/5/13
 * Time: 7:40 PM
 * Project: Don'tTouchIt
 */
public class DontTouchIt extends Game {
	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
