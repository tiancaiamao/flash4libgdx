package com.doodleapp.animation;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C4;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.NumberUtils;
import com.doodleapp.animation.xfl.Color;
import com.doodleapp.animation.xfl.DOMSymbolInstance;
import com.doodleapp.animation.xfl.Matrix;
import com.doodleapp.animation.xfl.Point;

public class FrameElement {
	static public final int X1 = 0;
	static public final int Y1 = 1;
	static public final int C1 = 2;
	static public final int U1 = 3;
	static public final int V1 = 4;
	static public final int X2 = 5;
	static public final int Y2 = 6;
	static public final int C2 = 7;
	static public final int U2 = 8;
	static public final int V2 = 9;
	static public final int X3 = 10;
	static public final int Y3 = 11;
	static public final int C3 = 12;
	static public final int U3 = 13;
	static public final int V3 = 14;
	static public final int X4 = 15;
	static public final int Y4 = 16;
	static public final int C4 = 17;
	static public final int U4 = 18;
	static public final int V4 = 19;

	Sprite sprite;
	Texture texture;
	
	float centerPoint3DX;
	float centerPoint3DY;
	Matrix matrix;
	Point transformationPoint;
	Color color;
	String name;

	String type;
	String loop;
	String instanceName;

	public FrameElement(DOMSymbolInstance inst, Map<String, Texture> map) {
		this.name = inst.libraryItemName;
		this.texture = map.get(inst.libraryItemName);
		this.sprite = new Sprite(texture);
		if (this.texture == null) {
			System.out.println("没有找到对应的texture"+inst.libraryItemName);
		}
		this.matrix = inst.matrix;
		this.transformationPoint = inst.transformationPoint;
		this.color = inst.color;
		this.centerPoint3DX = inst.centerPoint3DX;
		this.centerPoint3DY = inst.centerPoint3DY;
	}

	public void draw(SpriteBatch batch, float x, float y, float width,
			float height) {
		float oldAlpha = 1;
		if (color != null) {
			com.badlogic.gdx.graphics.Color tmp = sprite.getColor();
			oldAlpha = tmp.a;
			tmp.a *= (float) color.alphaMultiplier;
			sprite.setColor(tmp);
		}
		final float[] vertices = sprite.getVertices();

		float x1 = -transformationPoint.x; //vertices[X1];
		float y1 = -transformationPoint.y;//vertices[Y1];
		float x2 = -transformationPoint.x;//vertices[X2];
		float y2 = -transformationPoint.y + texture.getHeight();//vertices[Y2];
		float x3 = -transformationPoint.x + texture.getWidth();//vertices[X3];
		float y3 = -transformationPoint.y + texture.getHeight();//vertices[Y3];
		float x4 = -transformationPoint.x + texture.getWidth();//vertices[X4];
		float y4 = -transformationPoint.y;//vertices[Y4];

		// x' = a*x + c*y + tx
		// y' = b*x + d*y + ty
		x1 = (float) (x1 * matrix.a + y1 * matrix.c) + matrix.tx + transformationPoint.x;
		y1 = (float) (x1 * matrix.b + y1 * matrix.d) + matrix.ty + transformationPoint.y;
		x2 = (float) (x2 * matrix.a + y2 * matrix.c) + matrix.tx + transformationPoint.x;
		y2 = (float) (x2 * matrix.b + y2 * matrix.d) + matrix.ty + transformationPoint.y;
		x3 = (float) (x3 * matrix.a + y3 * matrix.c) + matrix.tx + transformationPoint.x;
		y3 = (float) (x3 * matrix.b + y3 * matrix.d) + matrix.ty + transformationPoint.y;
		x4 = (float) (x4 * matrix.a + y4 * matrix.c) + matrix.tx + transformationPoint.x;
		y4 = (float) (x4 * matrix.b + y4 * matrix.d) + matrix.ty + transformationPoint.y;

		vertices[X1] = x1 - centerPoint3DX + x;
		vertices[Y1] = y1 - centerPoint3DY + y;
		vertices[X2] = x2 - centerPoint3DX + x;
		vertices[Y2] = y2 - centerPoint3DY + y;
		vertices[X3] = x3 - centerPoint3DX + x;
		vertices[Y3] = y3 - centerPoint3DY + y;
		vertices[X4] = x4 - centerPoint3DX + x;
		vertices[Y4] = y4 - centerPoint3DY + y;
		
		batch.draw(texture, vertices, 0, 20);

/*		if (color != null) {
			com.badlogic.gdx.graphics.Color tmp = sprite.getColor();
			tmp.a = oldAlpha;
			sprite.setColor(tmp);
		}*/
	}
	
	public com.badlogic.gdx.graphics.Color getColor (float vertices) {
		int intBits = NumberUtils.floatToIntColor(vertices);
		com.badlogic.gdx.graphics.Color color = new com.badlogic.gdx.graphics.Color(1, 1, 1, 1);
		color.r = (intBits & 0xff) / 255f;
		color.g = ((intBits >>> 8) & 0xff) / 255f;
		color.b = ((intBits >>> 16) & 0xff) / 255f;
		color.a = ((intBits >>> 24) & 0xff) / 255f;
		return color;
	}
	
	public void setColor (float[] vertices, com.badlogic.gdx.graphics.Color tint) {
		float color = tint.toFloatBits();
		vertices[C1] = color;
		vertices[C2] = color;
		vertices[C3] = color;
		vertices[C4] = color;
	}

	public void Debug() {
		System.out.println("draw: " + name);
		System.out.println("transformationPoint: " + transformationPoint.x
				+ " " + transformationPoint.y);
		System.out.println("matrix: " + matrix.a + " " + matrix.b + " "
				+ matrix.c + " " + matrix.tx + " " + matrix.ty);
		if (color != null) {
			System.out.println("color: " + color.alphaMultiplier);
		}
		System.out.println("centerPoint3DX: "+centerPoint3DX);
		System.out.println("centerPoint3DY: "+centerPoint3DY);
		System.out.println();
	}
}