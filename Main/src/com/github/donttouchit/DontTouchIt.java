package com.github.donttouchit;

import com.badlogic.gdx.Game;
import com.github.donttouchit.screen.GameScreen;
import com.github.donttouchit.screen.MenuScreen;

public class DontTouchIt extends Game {
	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
