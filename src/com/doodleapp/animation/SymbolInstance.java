package com.doodleapp.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.doodleapp.animation.xfl.AdjustColorFilter;
import com.doodleapp.animation.xfl.Color;
import com.doodleapp.animation.xfl.DOMSymbolInstance;
import com.doodleapp.animation.xfl.Matrix;
import com.doodleapp.animation.xfl.Point;
import com.doodleapp.animation.xfl.Xfl;

public class SymbolInstance implements DrawableRecurse {
	public Matrix matrix;
	public Point transformationPoint;
	public AdjustColorFilter filters;
	public Color color;
	public float centerPoint3DX;
	public float centerPoint3DY;
	private KFrame[] keyFrames;
	
	public SymbolInstance(DOMSymbolInstance id, Xfl xfl, FAnimation animation) {
		
	}

	@Override
	public void drawRecurse(SpriteBatch batch, int index, float x, float y,
			float width, float height, Matrix matrix) {
		// TODO Auto-generated method stub
		KFrame keyFrame = keyFrames[index];
		keyFrame.drawRecurse(batch, index, x, y, width, height, matrix);
	}

}
