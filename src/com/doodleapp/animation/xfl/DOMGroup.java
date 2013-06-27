package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class DOMGroup implements XflDrawable {
	Array<XflDrawable> members;
	
	public DOMGroup(Element xml) {
		if (!xml.getName().equals("DOMGroup")) {
			System.out.println("wrong xml to construct DOMGroup, get "
					+ xml.getName());
		}
		members = new Array<XflDrawable>();
		
		Element tmp = xml.getChildByName("DOMShape");
		if (tmp != null) {
			members.add(new DOMShape(tmp));
		}
		
		tmp = xml.getChildByName("DOMGroup");
		if (tmp != null) {
			members.add(new DOMGroup(tmp));
		}
	}
}
