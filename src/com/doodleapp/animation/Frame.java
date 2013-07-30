package com.doodleapp.animation;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.doodleapp.animation.xfl.DOMFrame;

// It's a Frame in one layer
public class Frame {
	FrameElement[] elements;
	FAnimation animation;
	static 	Interpolation interpolation[] = {
		Interpolation.pow5In, 
		Interpolation.pow4In,
		Interpolation.pow3In,
		Interpolation.pow2In,
		Interpolation.linear,
		Interpolation.pow2Out,
		Interpolation.pow3Out,
		Interpolation.pow4Out,
		Interpolation.pow5Out,
	};

	public Frame(DOMFrame domframe, Map<String, TextureRegion> map, FAnimation animation) {
		int length = domframe.elements.length;
		if (length > 0) {
			elements = new FrameElement[length];
			for (int i = 0; i < length; i++) {
				elements[i] = new FrameElement(domframe.elements[i], map, animation);
			}
		}
		this.animation = animation;
	}
	
	public Frame(DOMFrame begin, DOMFrame end, float precent, Map<String, TextureRegion> map, FAnimation animation) {
		int length = begin.elements.length;
		int index = (begin.acceleration + 100) * 9 / 200;
		if (index > 8) index = 8;
		if (index < 0) index = 0;
		float precent_after_interpolate = Frame.interpolation[index].apply(precent);
		if (length > 0) {
			elements = new FrameElement[length];
			for (int i = 0; i < length; i++) {
				elements[i] = new FrameElement(begin.elements[i], end.elements[i], precent, precent_after_interpolate, map, animation);
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
}
