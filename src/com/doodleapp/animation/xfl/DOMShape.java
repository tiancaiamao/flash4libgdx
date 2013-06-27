package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class DOMShape implements XflDrawable {
	public Matrix matrix;
	public Point transformationPoint;
	public FillStyle fills[];
	public Edge edges[];
	
	public DOMShape(Element xml) {
		if (!xml.getName().equals("DOMShape")) {
			System.out.println("wrong xml to construct DOMShape, get "
					+ xml.getName());
		}
		
		Element tmp = xml.getChildByName("matrix");
		if (tmp != null) {
			matrix = new Matrix(tmp.getChildByName("Matrix"));
		}
		
		tmp = xml.getChildByName("transformationPoint");
		if (tmp != null) {
			transformationPoint = new Point(tmp.getChildByName("Point"));
		}
		
		Array<Element> elems = xml.getChildrenByName("fills");
		fills = new FillStyle[elems.size];
		for(int i=0; i<elems.size; i++) {
			fills[i] = new FillStyle(elems.get(i));
		}
		
		elems = xml.getChildrenByName("edges");
		edges = new Edge[elems.size];
		for(int i=0; i<elems.size; i++) {
			edges[i] = new Edge(elems.get(i));
		}
	}
}
