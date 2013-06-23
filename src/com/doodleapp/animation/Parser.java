package com.doodleapp.animation;

import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.doodleapp.animation.xfl.DOMTimeline;
import com.doodleapp.animation.xfl.Xfl;

public class Parser {
	XmlReader reader;
	
	public Parser() {
		reader = new XmlReader();
	}
	
	public Xfl parse(FileHandle file) throws IOException {
		Xfl ret = null;
		Element xml = reader.parse(file);
		
		xml = xml.getChildByName("timelines");
		if (xml != null) {
			xml = xml.getChildByName("DOMTimeline");
			if (xml != null) {
				ret = new Xfl();
				ret.timelines = new DOMTimeline(xml);
			}
		}
		return ret;
	}
}
