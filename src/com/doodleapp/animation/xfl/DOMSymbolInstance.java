package com.doodleapp.animation.xfl;

import com.badlogic.gdx.utils.XmlReader.Element;

public class DOMSymbolInstance {
	public String libraryItemName;
	public String symbolType;
//	public int firstFrame;
//	public String loop;

	public Matrix matrix;
	public Point transformationPoint;
	public AdjustColorFilter filters;
	public Color color;
//	public float centerPoint3DX;
//	public float centerPoint3DY;

	public DOMSymbolInstance(Element xml) {
		if (!xml.getName().equals("DOMSymbolInstance")) {
			System.out.println("wrong xml to construct DOMSymbolInstance, get "
					+ xml.getName());
		}

		libraryItemName = xml.getAttribute("libraryItemName");
//		symbolType = xml.getAttribute("symbolType", null);
//		if (symbolType == null || !symbolType.equals("graphic")) {
//			System.out.println("only graphic SymbolInstance supported! in " + libraryItemName);
//		}
	//	firstFrame = xml.getIntAttribute("firstFrame");
	//	loop = xml.getAttribute("loop");
	//	centerPoint3DX = xml.getFloatAttribute("centerPoint3DX", 0);
	//	centerPoint3DY = xml.getFloatAttribute("centerPoint3DY", 0);

		Element tmp = xml.getChildByName("matrix");
		if (tmp != null) {
			tmp = tmp.getChildByName("Matrix");
			if (tmp != null)
				matrix = new Matrix(tmp);
		}

		tmp = xml.getChildByName("transformationPoint");
		if (tmp != null) {
			tmp = tmp.getChildByName("Point");
			if (tmp != null)
				transformationPoint = new Point(tmp);
		}

		tmp = xml.getChildByName("filters");
		if (tmp != null) {
			tmp = tmp.getChildByName("AdjustColorFilter");
			if (tmp != null)
				filters = new AdjustColorFilter(tmp);
		}

		tmp = xml.getChildByName("color");
		if (tmp != null) {
			tmp = tmp.getChildByName("Color");
			if (tmp != null)
				color = new Color(tmp);
		}
	}
}
