package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Edge {
	public int fillStyle0;
	public int fillStyle1;
	public int strokeStyle;
	public Array<Instruct> instructs;
	
	public Edge(Element xml) {
		if (!xml.getName().equals("Edge")) {
			System.out.println("wrong xml to construct Element, get "
					+ xml.getName());
		}
		
		xml.getIntAttribute("fillStyle0", 0);
		xml.getIntAttribute("fillStyle1", 0);
		String str = xml.getAttribute("edges");
		
		instructs = parseEdges(str);
	}
	
	private Array<Instruct> parseEdges(String str) {
		Instruct inst = null;
		float x, y, z, k;
		Array<Instruct> ret = new Array<Instruct>();
		for (int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if (c == ' ') {
				continue;
			}
			switch (c) {
			case '!':
				i += parsePointTo(str.substring(i), inst);
				break;
			case '|':
				i += parseLineTo(str.substring(i), inst);
				break;
			case '[':
				i += parseCurveTo(str.substring(i), inst);
				break;
			default:
				System.out.println("parse error! unknow edge instruct");
			}
			ret.add(inst);
		}
		return ret;
	}

	private int parseCurveTo(String str, Instruct inst) {
		Float arg1 = null;
		Float arg2 = null;
		Float arg3 = null;
		Float arg4 = null;
		Integer length = null;
		int ret = 0;
		ret ++;
		parseNumber(str.substring(ret), length, arg1);
		ret += length;
		parseNumber(str.substring(ret), length, arg2);
		ret += length;
		parseNumber(str.substring(ret), length, arg3);
		ret += length;
		parseNumber(str.substring(ret), length, arg4);
		ret += length;
		
		inst = new Instruct(Instruct.LINE_TO, arg1/20, arg2/20, arg3/20, arg4/20);
		return ret;
	}

	private int parseLineTo(String str, Instruct inst) {
		Float arg1 = null;
		Float arg2 = null;
		Integer length = null;
		int ret = 0;
		ret ++;
		parseNumber(str.substring(ret), length, arg1 / 20);
		ret += length;
		parseNumber(str.substring(ret), length, arg2 / 20);
		ret += length;
		
		inst = new Instruct(Instruct.LINE_TO, arg1, arg2);
		return ret;
	}

	private int parsePointTo(String str, Instruct inst) {
		Float arg1 = null;
		Float arg2 = null;
		Integer length = null;
		int ret = 0;
		ret ++;
		parseNumber(str.substring(ret), length, arg1);
		ret += length;
		parseNumber(str.substring(ret), length, arg2);
		ret += length;
		
		inst = new Instruct(Instruct.MOVE_TO, arg1 / 20, arg2 / 20);
		return ret;
	}
	
	private void parseNumber(String str, Integer length, Float ret) {
		int i = 0;
		while(str.charAt(i) == ' ')
			i++;
		char c = str.charAt(i);
		while(c >= '0' && str.charAt(i)<='9' || str.charAt(i)=='.') {
			i++;
			c = str.charAt(i);
		}
		length = i;
		ret = Float.parseFloat(str.substring(0, i));
	}

	public class Instruct {
		public static final int MOVE_TO = 0;
		public static final int LINE_TO = 1;
		public static final int CURVE_TO = 2;
		public static final int SELECTION = 3;
		
		int inst;
		float data[];
		
		public Instruct (int inst, float x, float y) {
			this.inst = inst;
			this.data = new float[2];
			data[0] = x;
			data[1] = y;
		}
		
		public Instruct (int inst, float x, float y, float x1, float y1) {
			this.inst = inst;
			this.data = new float[4];
			data[0] = x;
			data[1] = y;
			data[2] = x1;
			data[3] = y1;
		}
	}
}
