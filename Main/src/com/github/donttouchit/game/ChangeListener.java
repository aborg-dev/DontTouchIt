package com.github.donttouchit.game;

import com.github.donttouchit.game.properties.Dye;

public interface ChangeListener {
	boolean accept(Dye dye, Object object);
	void changed(Object object);
}
