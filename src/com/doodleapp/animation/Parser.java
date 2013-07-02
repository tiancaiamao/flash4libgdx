package com.doodleapp.animation;

import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.doodleapp.animation.xfl.DOMSymbolItem;
import com.doodleapp.animation.xfl.DOMTimeline;
import com.doodleapp.animation.xfl.Xfl;

public class Parser {
	XmlReader reader;
	
	public Parser() {
		reader = new XmlReader();
	}
	
	public Xfl parse(String folder) throws IOException {
		Xfl ret = new Xfl();
		Element xml = reader.parse(folder+"/DOMDocument.xml");
		
		if (!xml.getName().equals("DOMDocument")) {
			System.out.println("wrong xml to construct DOMFrame, get "
					+ xml.getName());
		}
		
		Element tmp = xml.getChildByName("symbols");
		Array<Element> elems = tmp.getChildrenByName("Include");
		ret.symbols = new DOMSymbolItem[elems.size];
		for (int i=0; i<elems.size; i++) {
			String fileName = elems.get(i).getAttribute("href");
			Element include = reader.parse(folder + "/LIBRARY/" + fileName);
			ret.symbols[i] = new DOMSymbolItem(include);
		}
		
		xml = xml.getChildByName("timelines");
		if (xml != null) {
			xml = xml.getChildByName("DOMTimeline");
			if (xml != null) {
				ret.timelines = new DOMTimeline(xml);
			}
		}
		return ret;
	}
}
