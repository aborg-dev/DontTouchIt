package com.github.donttouchit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * User: iiotep9huy
 * Date: 9/5/13
 * Time: 7:41 PM
 * Project: Don'tTouchIt
 */
public class DesktopStarter {
    public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Title";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		new LwjglApplication(new DontTouchIt(), cfg);
    }
}
