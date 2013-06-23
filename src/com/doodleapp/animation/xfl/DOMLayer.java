package com.doodleapp.animation.xfl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class DOMLayer {
	public String name;
	public Color color;
//	public boolean locked;
	
	public DOMFrame[] frames;

	public DOMLayer(Element xml) {
		if (!xml.getName().equals("DOMLayer")) {
			System.out.println("wrong xml to construct DOMLayer, get "
					+ xml.getName());
		}
		
		name = xml.getAttribute("name");
		color = Color.valueOf(xml.getAttribute("color").substring(1));
//		locked = xml.getBoolean("locked");
		
		Element tmp = xml.getChildByName("frames");
		if (tmp != null) {
			Array<Element> elems = tmp.getChildrenByName("DOMFrame");
			frames = new DOMFrame[elems.size];
			for (int i=0; i<elems.size; i++) {
				frames[i] = new DOMFrame(elems.get(i));
			}
		}
	}
}