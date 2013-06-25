package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class Color {
	public Color(Element xml) {
		if (!xml.getName().equals("Color")) {
			System.out.println("wrong xml to construct Color, get "
					+ xml.getName());
		}
		alphaMultiplier = 1d;
		String str = xml.getAttribute("alphaMultiplier", null);
		if (str != null)
			alphaMultiplier = Double.parseDouble(str);
	}

	public double alphaMultiplier;
}
