package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class DOMSymbolItem {
	DOMTimeline timeline;
	
	public DOMSymbolItem(Element xml) {
		if (!xml.getName().equals("DOMSymbolItem")) {
			System.out.println("wrong xml to construct DOMSymbolItem, get "
					+ xml.getName());
		}
		
		Element tmp = xml.getChildByName("timeline");
		if (tmp != null) {
			timeline = new DOMTimeline(tmp.getChildByName("DOMTimeline"));
		}
	}
}
