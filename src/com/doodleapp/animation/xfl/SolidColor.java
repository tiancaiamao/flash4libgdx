package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class SolidColor {
	public String color;
	public double alpha;
	
	public SolidColor(Element xml) {
		if (!xml.getName().equals("SolidColor")) {
			System.out.println("wrong xml to construct SolidColor, get "
					+ xml.getName());
		}
		
		color = xml.getAttribute("color");
		String tmp = xml.getAttribute("alpha", null);
		if (tmp != null) {
			alpha = Double.parseDouble(tmp);
		} else {
			alpha = 1;
		}
	}

}
