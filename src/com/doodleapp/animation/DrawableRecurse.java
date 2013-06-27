package com.doodleapp.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doodleapp.animation.xfl.Matrix;

public interface DrawableRecurse {
	void drawRecurse(SpriteBatch batch, int index, float x, float y, float width, float height, Matrix matrix);
}
