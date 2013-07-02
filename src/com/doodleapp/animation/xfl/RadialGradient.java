package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class RadialGradient {
	public Matrix matrix;
	public GradientEntry g1;
	public GradientEntry g2;
	
	public RadialGradient(Element xml) {
		if (!xml.getName().equals("RadialGradient")) {
			System.out.println("wrong xml to construct RadialGradient, get "
					+ xml.getName());
		}
		
		Element tmp = xml.getChildByName("matrix");
		matrix = new Matrix(tmp.getChildByName("Matrix"));
		
		Array<Element> elems = xml.getChildrenByName("GradientEntry");
		g1 = new GradientEntry(elems.get(0));
		g2 = new GradientEntry(elems.get(1));
	}
}
