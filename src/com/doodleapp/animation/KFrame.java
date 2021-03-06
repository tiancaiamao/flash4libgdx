package com.doodleapp.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

// The KFrame is constructed of server layer of Frame....
// all layers of the this frame combined to be a KFrame
public class KFrame implements Drawable {
	int index;
	String keyMode;
	Array<Frame> layers;
	FAnimation animation;
	
	public KFrame(FAnimation animation) {
		layers = new Array<Frame>();
		this.animation = animation;
	}
	
	public void setIndex(int index){
		this.index=index;
	}
	public void setKeyMode(String keyMode){
		this.keyMode=keyMode;
	}
	public String getKeyMode(){
		return keyMode;
	}
	public int getIndex(){
		return index;
	}

	public void addLayer(Frame frame) {
		if (layers == null)
			System.out.println("addLayer error");
		layers.add(frame);
	}
	public void setLayers(Array<Frame> frames) {
		this.layers = frames;
	}
	public Array<Frame> getLayers() {
		return layers;
	}
	
	public void Debug() {
		for (Frame frame:layers) {
			frame.Debug();
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float x, float y, float width,
			float height) {
		for (Frame frame:layers) {
			frame.draw(batch, x, y, width, height);
		}	
	}

	@Override
	public float getLeftWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLeftWidth(float leftWidth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getRightWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRightWidth(float rightWidth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getTopHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTopHeight(float topHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getBottomHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBottomHeight(float bottomHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMinWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMinWidth(float minWidth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMinHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMinHeight(float minHeight) {
		// TODO Auto-generated method stub
		
	}

}
