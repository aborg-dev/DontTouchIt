package com.github.donttouchit.screen;

import com.badlogic.gdx.Screen;
import com.github.donttouchit.DontTouchIt;

public abstract class BasicScreen implements Screen {
	private final DontTouchIt game;

	public BasicScreen(DontTouchIt game) {
		this.game = game;
	}

	public DontTouchIt getGame() {
		return game;
	}

	protected void draw() {
	}

	protected void update(float delta) {
	}


	@Override
	public void render(float delta) {
		update(delta);
		draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void resume() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void dispose() {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
