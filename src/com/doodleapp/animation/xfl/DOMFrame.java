package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class DOMFrame {
	public int index;
	public int duration;
	public int keyMode;
	public String tweenType;
	public int acceleration;
	
	public DOMSymbolInstance[] elements;
	
	public DOMFrame(Element xml) {
		if (!xml.getName().equals("DOMFrame")) {
			System.out.println("wrong xml to construct DOMFrame, get "
					+ xml.getName());
		}
		
		index = xml.getIntAttribute("index");
		duration = xml.getIntAttribute("duration", 1);
		keyMode = xml.getIntAttribute("keyMode");
		tweenType = xml.getAttribute("tweenType", null);
		acceleration = xml.getIntAttribute("acceleration", 0);
		
		Element tmp = xml.getChildByName("elements");
		if (tmp != null) {
			Array<Element> elems = tmp.getChildrenByName("DOMSymbolInstance");
			elements = new DOMSymbolInstance[elems.size];
			
			for (int i=0; i<elems.size; i++) {
				elements[i] = new DOMSymbolInstance(elems.get(i));
			}
		}
	}
}
