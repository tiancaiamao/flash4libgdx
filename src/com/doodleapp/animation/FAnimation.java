package com.doodleapp.animation;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.doodleapp.animation.xfl.DOMFrame;
import com.doodleapp.animation.xfl.DOMLayer;
import com.doodleapp.animation.xfl.DOMTimeline;
import com.doodleapp.animation.xfl.Xfl;


public class FAnimation {
	public float X;
	public float Y;
	public float W;
	public float H;
	private KFrame[] keyFrames;
	

	
	public static final int NORMAL = 0;
	public static final int REVERSED = 1;
	public static final int LOOP = 2;
	public static final int LOOP_REVERSED = 3;
	public static final int LOOP_PINGPONG = 4;
	public static final int LOOP_RANDOM = 5;
	
	public static final int CENTER = 0;
	public static final int LEFT_TOP = 1;

	private float frameDuration;
	private float animationDuration;

	private int playMode = NORMAL;
	
	public FAnimation(Xfl xfl, Map<String, Texture> map) {
		init(xfl, map, 0f, 0f, 0.0f, 0.0f, 1f/24f, NORMAL);
	}
	public FAnimation(Xfl xfl, Map<String, Texture> map, int playMode) {
		init(xfl, map, 0f, 0f, 0.0f, 0.0f, 1f/24f,  playMode);
	}
	public FAnimation(Xfl xfl, Map<String, Texture> map, float x, float y) {
		init(xfl, map, x, y, 0.0f, 0.0f, 1f/24f, NORMAL);
	}
	public FAnimation(Xfl xfl, Map<String, Texture> map, float x, float y, int playMode) {
		init(xfl, map, x, y, 0.0f, 0.0f, 1f/24f, playMode);
	}
	public FAnimation(Xfl xfl, Map<String, Texture> map, float x, float y, float w, float h, int playMode) {
		init(xfl, map, x, y, w, h, 1f/24f, playMode);
	}
	
	// the constructor
	private void init(Xfl xfl, Map<String, Texture> map, float x,
			float y, float w, float h, float frameDuration, int playMode) {
		X = x;
		Y = y;
		W = w;
		H = h;
		
		this.playMode = playMode;
		this.frameDuration = frameDuration;
		DOMTimeline timeline = xfl.timelines;
		
		int keyFramesLength = 0;
		for (DOMLayer layer:timeline.layers) {
			for (DOMFrame frame:layer.frames) {
				if(frame.index+frame.duration > keyFramesLength) {
					keyFramesLength = frame.index+frame.duration;
				}	
			}
		}
		
		animationDuration = keyFramesLength * frameDuration;
		keyFrames = new KFrame[keyFramesLength];
		for (int i=0; i< keyFramesLength; i++) {
			KFrame tmp = new KFrame(this);
			tmp.setIndex(i);
			keyFrames[i] = tmp;
		}

		for (DOMLayer layer : timeline.layers) {
			for (DOMFrame domframe : layer.frames) {
				Frame frame = new Frame(domframe, map, this);
				for (int i = 0; i<domframe.duration; i++) {
					keyFrames[domframe.index + i].addLayer(frame);
				}
			}
		}
		
		for (KFrame kframe:keyFrames) {
			kframe.getLayers().reverse();
		}
	}
	
	public KFrame getKeyFrame (float stateTime, boolean looping) {
		// we set the play mode by overriding the previous mode based on looping
		// parameter value
		if (looping && (playMode == NORMAL || playMode == REVERSED)) {
			if (playMode == NORMAL)
				playMode = LOOP;
			else
				playMode = LOOP_REVERSED;
		} else if (!looping && !(playMode == NORMAL || playMode == REVERSED)) {
			if (playMode == LOOP_REVERSED)
				playMode = REVERSED;
			else
				playMode = LOOP;
		}

		return getKeyFrame(stateTime);
	}
	
	public KFrame getKeyFrame (float stateTime) {
		int frameNumber = getKeyFrameIndex (stateTime);
		return keyFrames[frameNumber];
	}
	
	public int getKeyFrameIndex (float stateTime) {
		int frameNumber = (int)(stateTime / frameDuration);

		if(keyFrames.length == 1)
         return 0;
		
		switch (playMode) {
		case NORMAL:
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		case LOOP:
			frameNumber = frameNumber % keyFrames.length;
			break;
		case LOOP_PINGPONG:
			frameNumber = frameNumber % ((keyFrames.length * 2) - 2);
         if (frameNumber >= keyFrames.length)
            frameNumber = keyFrames.length - 2 - (frameNumber - keyFrames.length);
         break;
		case LOOP_RANDOM:
			frameNumber = MathUtils.random(keyFrames.length - 1);
			break;
		case REVERSED:
			frameNumber = Math.max(keyFrames.length - frameNumber - 1, 0);
			break;
		case LOOP_REVERSED:
			frameNumber = frameNumber % keyFrames.length;
			frameNumber = keyFrames.length - frameNumber - 1;
			break;

		default:
			// play normal otherwise
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		}
		
		return frameNumber;
	}
	
	public void setPlayMode (int playMode) {
		this.playMode = playMode;
	}


	public boolean isAnimationFinished (float stateTime) {
		int frameNumber = (int)(stateTime / frameDuration);
		return keyFrames.length - 1 < frameNumber;
	}
}