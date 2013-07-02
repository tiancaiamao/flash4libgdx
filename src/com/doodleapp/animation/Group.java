package com.doodleapp.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doodleapp.animation.xfl.Matrix;

public class Group implements DrawableRecurse {
	DrawableRecurse members[];
	
	@Override
	public void drawRecurse(SpriteBatch batch, int index, float x, float y,
			float width, float height, Matrix matrix) {
		// TODO Auto-generated method stub
		for(DrawableRecurse member:members) {
			member.drawRecurse(batch, index, x, y, width, height, matrix);
		}
	}

}
