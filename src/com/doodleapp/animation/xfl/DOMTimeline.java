package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class DOMTimeline {
	public String name;
	public int currentFrame;
	
	public DOMLayer[] layers;
	
	public DOMTimeline(Element xml) {
		if (!xml.getName().equals("DOMTimeline")) {
			System.out.println("wrong xml to construct DOMTimeline, get "
					+ xml.getName());
		}
		
		name = xml.getAttribute("name");
		currentFrame = xml.getIntAttribute("currentFrame");
		
		Element tmp = xml.getChildByName("layers");
		if (tmp != null) {
			Array<Element> elems = tmp.getChildrenByName("DOMLayer");
			layers = new DOMLayer[elems.size];
			
			for(int i=0; i<elems.size; i++) {
				layers[i] = new DOMLayer(elems.get(i));
			}
		}
	}
}
