package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class GradientEntry {
	public String color;
	public double alpha;
	
	public GradientEntry(Element xml) {
		if (!xml.getName().equals("GradientEntry")) {
			System.out.println("wrong xml to construct GradientEntry, get "
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
