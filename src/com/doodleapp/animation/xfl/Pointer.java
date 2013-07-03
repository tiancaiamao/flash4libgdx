package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class Pointer {
	float x;
	float y;
	
	public Pointer(Element xml) {
		if (xml.getName() != "Pointer") {
			System.out.println("wrong xml to construct Pointer, get "
					+ xml.getName());
		}

		x = xml.getIntAttribute("x");
		y = xml.getIntAttribute("y");
	}

}
