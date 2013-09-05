package com.github.donttouchit.screen;

import com.badlogic.gdx.Screen;

/**
 * User: iiotep9huy
 * Date: 9/5/13
 * Time: 8:06 PM
 * Project: Don'tTouchIt
 */
public abstract class BasicScreen implements Screen {

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
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void show() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void hide() {
		//To change body of implemented methods use File | Settings | File Templates.
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
