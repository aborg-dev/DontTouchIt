package com.github.donttouchit;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * User: iiotep9huy
 * Date: 9/5/13
 * Time: 7:42 PM
 * Project: Don'tTouchIt
 */
public class AndroidStarter extends AndroidApplication {

	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        cfg.useWakelock = false;
        cfg.useGL20 = true;
        initialize(new DontTouchIt(), cfg);
    }
}
