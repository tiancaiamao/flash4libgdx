package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class Point {
	public float x;
	public float y;
	
	public Point(Element xml) {
		if (!xml.getName().equals("Point")) {
			System.out.println("wrong xml to construct Point, get "
					+ xml.getName());
		}

		x = xml.getFloatAttribute("x", 0);
		y = xml.getFloatAttribute("y", 0);
	}

}
