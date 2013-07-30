package com.doodleapp.animation;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.doodleapp.animation.xfl.Color;
import com.doodleapp.animation.xfl.DOMSymbolInstance;
import com.doodleapp.animation.xfl.Matrix;
import com.doodleapp.animation.xfl.Point;


/* Holds the texture and the matrix information for drawing. The texture contains a width and a height information.
 * A FrameElement also has an transformationPoint around which rotations and scaling are performed (that
 * is, the transformationPoint is not modified by rotation and scaling). The transformationPoint is given relative to the top left corner of the FrameElement.
 * When drawing, we get the animation's width and height, also the draw arguments's width and height, which can be combined to get the scale ratio. 
 */
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
	TextureRegion texture;
	
//	float centerPoint3DX;
//	float centerPoint3DY;
	Matrix matrix;
	Point transformationPoint;
	Color color;
	String name;

	String type;
	String loop;
	
	FAnimation animation;

	public FrameElement(DOMSymbolInstance inst, Map<String, TextureRegion> map, FAnimation animation) {
		this.animation = animation;
		this.name = inst.libraryItemName;
		this.texture = map.get(inst.libraryItemName);
		if (this.texture == null) {
			System.out.println("没有找到对应的texture"+inst.libraryItemName);
		}
		this.sprite = new Sprite(texture);
		this.matrix = inst.matrix;
		this.transformationPoint = inst.transformationPoint;
		this.color = inst.color;
//		this.centerPoint3DX = inst.centerPoint3DX;
//		this.centerPoint3DY = inst.centerPoint3DY;
	}

	public FrameElement(DOMSymbolInstance inst1,
			DOMSymbolInstance inst2, float precent, float precent_after_interpolation,
			Map<String, TextureRegion> map, FAnimation animation) {

		this.animation = animation;
		if (!inst1.libraryItemName.equals(inst2.libraryItemName)) {
			System.out.println("FrameElement construce error: not equal to DOMSymbolInstance");
		}
		if (precent > 1 || precent < 0) {
			System.out.println("FrameElement construct error: precent not right!"+precent);
		}
		this.name = inst1.libraryItemName;
		this.texture = map.get(inst1.libraryItemName);
		if (this.texture == null) {
			System.out.println("没有找到对应的texture"+inst1.libraryItemName);
		}
		this.sprite = new Sprite(texture);
		Matrix matrix = new Matrix();
		matrix.a = inst1.matrix.a + (inst2.matrix.a - inst1.matrix.a) * precent_after_interpolation;
		matrix.b = inst1.matrix.b + (inst2.matrix.b - inst1.matrix.b) * precent_after_interpolation;
		matrix.c = inst1.matrix.c + (inst2.matrix.c - inst1.matrix.c) * precent_after_interpolation;
		matrix.d = inst1.matrix.d + (inst2.matrix.d - inst1.matrix.d) * precent_after_interpolation;
		matrix.tx = inst1.matrix.tx + (inst2.matrix.tx - inst1.matrix.tx) * precent_after_interpolation;
		matrix.ty = inst1.matrix.ty + (inst2.matrix.ty - inst1.matrix.ty) * precent_after_interpolation;
		this.matrix = matrix;
		this.transformationPoint = inst1.transformationPoint;
		this.color = new Color();
		double c1 = 1;
		double c2 = 1;
		if (inst1.color != null)
			c1 = inst1.color.alphaMultiplier;
		if (inst2.color != null)
			c2 = inst2.color.alphaMultiplier;
		this.color.alphaMultiplier = c1 + (c2 - c1) * precent;
	}

	public void draw(SpriteBatch batch, float x, float y, float width,
			float height) {
		if (color != null) {
			com.badlogic.gdx.graphics.Color tmp = sprite.getColor();
			tmp.a = (float) color.alphaMultiplier;
			sprite.setColor(tmp);
		}
		final float[] vertices = sprite.getVertices();

//		float xScale = 1;
//		float yScale = 1;
//		if (animation.W != 0) {
//			xScale = width / animation.W;
//		}
//		if (animation.H != 0) {
//			yScale = height / animation.H;
//		}
		

//		float x1 = 0;
//		float y1 = 0;		
//		float x2 = 0;
//		float y2 = texture.getRegionHeight() * yScale;
//		float x3 = texture.getRegionWidth() * xScale;
//		float y3 = texture.getRegionHeight() * yScale;
//		float x4 = texture.getRegionWidth() * xScale;
//		float y4 = 0;

		// transformation is based on transformationPoint, do this sequence:
		// use transformationPoint as the origin, move the object
		// do matrix transform
		// move back the object
		float x1 = 0; //vertices[X1];
		float y1 = 0;//vertices[Y1];		
		float x2 = 0;//vertices[X2];
		float y2 = texture.getRegionHeight();//vertices[Y2];
		float x3 = texture.getRegionWidth();//vertices[X3];
		float y3 = texture.getRegionHeight();//vertices[Y3];
		float x4 = texture.getRegionWidth();//vertices[X4];
		float y4 = 0;//vertices[Y4];

//		float x1 = 0;
//		float y1 = 0;
//		float x2 = 0;
//		float y2 = texture.getHeight();
//		float x3 = texture.getWidth();
//		float y3 = texture.getHeight();
//		float x4 = texture.getWidth();
//		float y4 = 0;
//		float xScale = 4f;
//		float yScale = 4f;
//		float a = (float) (matrix.a * xScale);
//		float b = (float) (matrix.b * yScale);

		float a = (float) matrix.a;
		float b = (float) matrix.b;
		float c = (float) matrix.c;
		float d = (float) matrix.d;
		float tx = (float)matrix.tx;//- animation.W/2);
		float ty = (float)matrix.ty;// - animation.H/2);
		
		// x' = a*x + c*y + tx
		// y' = b*x + d*y + ty
/*		float fx1 = (float) (x1 * matrix.a + y1 * matrix.c) + matrix.tx + x;
		float fy1 = (float) (x1 * matrix.b + y1 * matrix.d) + matrix.ty + y;
		float fx2 = (float) (x2 * matrix.a + y2 * matrix.c) + matrix.tx + x;
		float fy2 = (float) (x2 * matrix.b + y2 * matrix.d) + matrix.ty + y;
		float fx3 = (float) (x3 * matrix.a + y3 * matrix.c) + matrix.tx + x;
		float fy3 = (float) (x3 * matrix.b + y3 * matrix.d) + matrix.ty + y;//		float a = -(float) matrix.a;
//		float b = -(float) matrix.b;
//		float c = -(float) matrix.c;
//		float d = -(float) matrix.d;
//		float tx = -matrix.tx;
//		float ty = -matrix.ty;
		float fx4 = (float) (x4 * matrix.a + y4 * matrix.c) + matrix.tx + x;
		float fy4 = (float) (x4 * matrix.b + y4 * matrix.d) + matrix.ty + y;*/
		
		// matrix transformation
		float fx1 = (float) (x1 *  a  + y1 * c) + tx;
		float fy1 = (float) (x1 *  b  + y1 * d) + ty;
		float fx2 = (float) (x2 *  a  + y2 * c) + tx;
		float fy2 = (float) (x2 *  b  + y2 * d) + ty;
		float fx3 = (float) (x3 *  a  + y3 * c) + tx;
		float fy3 = (float) (x3 *  b  + y3 * d) + ty;
		float fx4 = (float) (x4 *  a  + y4 * c) + tx;
		float fy4 = (float) (x4 *  b  + y4 * d) + ty;
		
//		float fx1 = (float) (x1 *  a  + y1 * c) + tx + transformationPoint.x;// + xScale*animation.W/2;
//		float fy1 = (float) (x1 *  b  + y1 * d) + ty + transformationPoint.y;// + yScale*animation.H/2;
//		float fx2 = (float) (x2 *  a  + y2 * c) + tx + transformationPoint.x;// + xScale*animation.W/2;
//		float fy2 = (float) (x2 *  b  + y2 * d) + ty + transformationPoint.y;// + yScale*animation.H/2;
//		float fx3 = (float) (x3 *  a  + y3 * c) + tx + transformationPoint.x;// + xScale*animation.W/2;
//		float fy3 = (float) (x3 *  b  + y3 * d) + ty + transformationPoint.y;// + yScale*animation.H/2;
//		float fx4 = (float) (x4 *  a  + y4 * c) + tx + transformationPoint.x;// + xScale*animation.W/2;
//		float fy4 = (float) (x4 *  b  + y4 * d) + ty + transformationPoint.y;// + yScale*animation.H/2;
		
/*		float fx1 = (float) (x1 *  matrix.a  + y1 * matrix.c) + matrix.tx + transformationPoint.x;
		float fy1 = (float) (x1 *  matrix.b  + y1 * matrix.d) + matrix.ty + transformationPoint.y;
		float fx2 = (float) (x2 *  matrix.a  + y2 * matrix.c) + matrix.tx + transformationPoint.x;
		float fy2 = (float) (x2 *  matrix.b  + y2 * matrix.d) + matrix.ty + transformationPoint.y;
		float fx3 = (float) (x3 *  matrix.a  + y3 * matrix.c) + matrix.tx + transformationPoint.x;
		float fy3 = (float) (x3 *  matrix.b  + y3 * matrix.d) + matrix.ty + transformationPoint.y;
		float fx4 = (float) (x4 *  matrix.a  + y4 * matrix.c) + matrix.tx + transformationPoint.x;
		float fy4 = (float) (x4 *  matrix.b  + y4 * matrix.d) + matrix.ty + transformationPoint.y;*/
		
		// move to Viewport center
		fx1 = fx1 - animation.X;
		fy1 = fy1 - animation.Y;
		fx2 = fx2 - animation.X;
		fy2 = fy2 - animation.Y;
		fx3 = fx3 - animation.X;
		fy3 = fy3 - animation.Y;
		fx4 = fx4 - animation.X;
		fy4 = fy4 - animation.Y;

		// inverse the y-coordinate
		vertices[X1] = fx1;
		vertices[Y1] = -fy1;
		vertices[U1] = texture.getU();
		vertices[V1] = texture.getV();
		
		vertices[X2] = fx2;
		vertices[Y2] = -fy2;
		vertices[U2] = texture.getU();
		vertices[V2] = texture.getV2();
		
		vertices[X3] = fx3;
		vertices[Y3] = -fy3;;
		vertices[U3] = texture.getU2();
		vertices[V3] = texture.getV2();
		
		vertices[X4] = fx4;
		vertices[Y4] = -fy4;
		vertices[U4] = texture.getU2();
		vertices[V4] = texture.getV();
		
		// move to Window center
		vertices[X1] += (x + width/2);
		vertices[Y1] += (y + height/2);
		
		vertices[X2] += (x + width/2);
		vertices[Y2] += (y + height/2);
		
		vertices[X3] += (x + width/2);
		vertices[Y3] += (y + height/2);
		
		vertices[X4] += (x + width/2);
		vertices[Y4] += (y + height/2);
		
		batch.draw(texture.getTexture(), vertices, 0, 20);

//		if (this.name.equals("tiekuai")) {
//			System.out.println(vertices[X1]+","+vertices[Y1]+ 
//					" "+vertices[X2]+","+vertices[Y2]+ " "+vertices[X3]+","+vertices[Y3]+ " "+vertices[X4]+","+vertices[Y4]);
//		}
			
//		System.out.println("x1:"+vertices[X1]+"y1:"+vertices[Y1]+"x3:"+vertices[X3]+"y3:"+vertices[Y3]);

		if (color != null) {
			com.badlogic.gdx.graphics.Color tmp = sprite.getColor();
			tmp.a = 1;
			sprite.setColor(tmp);
		}
	}
/*	
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
*/
	public void Debug() {
		System.out.println("draw: " + name);
		System.out.println("matrix: " + matrix.a + " " + matrix.b + " "
				+ matrix.c + " " + matrix.d + " "+ matrix.tx + " " + matrix.ty);
		if (color != null) {
			System.out.println("color: " + color.alphaMultiplier);
		}
//		System.out.println("centerPoint3DX: "+centerPoint3DX);
//		System.out.println("centerPoint3DY: "+centerPoint3DY);
		System.out.println();
	}
}