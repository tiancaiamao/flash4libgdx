package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class Matrix {
	public double a;
	public double b;
	public double c;
	public double d;
	public float tx;
	public float ty;

	public Matrix(Element xml) {
		if (!xml.getName().equals("Matrix")) {
			System.out.println("wrong xml to construct Matrix, get "
					+ xml.getName());
		}
		a = 1;
		b = 0;
		c = 0;
		d = 1;

		tx = xml.getFloatAttribute("tx", 0);
		ty = xml.getFloatAttribute("ty", 0);

		String str;
		str = xml.getAttribute("a", null);
		if (str != null) {
			a = Double.parseDouble(str);
		}
		str = xml.getAttribute("b", null);
		if (str != null) {
			b = Double.parseDouble(str);
		}
		str = xml.getAttribute("c", null);
		if (str != null) {
			c = Double.parseDouble(str);
		}
		str = xml.getAttribute("d", null);
		if (str != null) {
			d = Double.parseDouble(str);
		}
	}
}
