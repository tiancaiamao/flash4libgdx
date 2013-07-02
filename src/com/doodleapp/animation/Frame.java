package com.doodleapp.animation;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doodleapp.animation.xfl.DOMFrame;
import com.doodleapp.animation.xfl.Matrix;

// It's a Frame in one layer
public class Frame implements DrawableRecurse {
	DrawableRecurse[] elements;
	FAnimation animation;

	public Frame(DOMFrame domframe, Map<String, TextureRegion> map, FAnimation animation) {
		int length = domframe.elements.length;
		if (length > 0) {
			elements = new DrawableRecurse[length];
			for (int i = 0; i < length; i++) {
				elements[i] = new FrameElement(domframe.elements[i], map, animation);
			}
		}
		this.animation = animation;
	}

	public void draw(SpriteBatch batch, float x, float y, float width,
			float height) {
		if (elements != null) {
			for (FrameElement elem : elements) {
				elem.draw(batch, x, y, width, height);
			}
		}
	}

	public void Debug() {
		if (elements != null) {
			for (FrameElement elem : elements) {
				elem.Debug();
			}
		}
	}

	@Override
	public void drawRecurse(SpriteBatch batch, int index, float x, float y,
			float width, float height, Matrix matrix) {
		// TODO Auto-generated method stub
		
	}
}
