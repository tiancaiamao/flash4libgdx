package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class FillStyle {
	public int index;
	public RadialGradient radialGradient;
	public SolidColor solidColor;
	
	public FillStyle(Element xml) {
		if (!xml.getName().equals("FillStyle")) {
			System.out.println("wrong xml to construct FillStyle, get "
					+ xml.getName());
		}
		
		index = xml.getIntAttribute("index");
		
		Element tmp = xml.getChildByName("RadialGradient");
		if (tmp != null) {
			radialGradient = new RadialGradient(tmp);
		}
		
		tmp = xml.getChildByName("SolidColor");
		if (tmp != null) {
			solidColor = new SolidColor(tmp);
		}
		
	}
}
