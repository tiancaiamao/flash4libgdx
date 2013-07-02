package com.badlogic.drop;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.TextureDict;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.doodleapp.animation.FAnimation;
import com.doodleapp.animation.FrameElement;
import com.doodleapp.animation.KFrame;
import com.doodleapp.animation.Parser;
import com.doodleapp.animation.xfl.Xfl;

public class Test implements ApplicationListener, InputProcessor {
	SpriteBatch batch;
	// private Stage stage;
	// TextureAtlas atlas;
	// Image play;
	// Image options;
	Texture actor;
	FAnimation animtaion;
	float[] spriteVertices;
	Sprite sprite;
	float time;
	TextureAtlas boxAtlas;
	FAnimation box;
	FAnimation hong;
	FAnimation ice;
	FAnimation cross;
	

	@Override
	public void create() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();

		FileHandle xml = Gdx.files.internal("tiekuai.xml");
		Parser xflparser = new Parser();
		Xfl xfl = null;
		try {
			xfl = xflparser.parse(xml);
			if (xfl == null) {
				System.out.println("xfl parse error!!!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		time = 0;
		TextureRegion suo = new TextureRegion(new Texture(Gdx.files.internal("suo.png")));
		TextureRegion liantiao = new TextureRegion( new Texture(Gdx.files.internal("liantiao.png")));
		TextureRegion suokou = new TextureRegion( new Texture(Gdx.files.internal("suokou.png")));
		TextureRegion suokou2 =new TextureRegion( new Texture(Gdx.files.internal("suokou2.png")));
		TextureRegion tiekuai = new TextureRegion( new Texture(Gdx.files.internal("tiekuai.png")));
		TextureRegion tielian = new TextureRegion(new Texture(Gdx.files.internal("tielian.png")));
		HashMap<String, TextureRegion> map = new HashMap<String, TextureRegion>();
		map.put("suo", suo);
		map.put("liantiao", liantiao);
		map.put("suokou", suokou);
		map.put("suokou2", suokou2);
		map.put("tiekuai", tiekuai);
		map.put("tielian", tielian);

		animtaion = new FAnimation(xfl, map, 317.45f, 242.25f, 85.05f, 84.5f, FAnimation.LOOP);
		// KFrame kframe = animtaion.getKeyFrame(Gdx.graphics.getDeltaTime());
		//KFrame kframe = animtaion.keyFrames[4];
		//kframe.Debug();

		actor = new Texture(Gdx.files.internal("actor1.gif"));
		sprite = new Sprite(actor);
		Color color = sprite.getColor();
		color.a *= 1.0;
		sprite.setColor(color);
/*		spriteVertices = sprite.getVertices();
		spriteVertices[0] = 0;
		spriteVertices[1] = 0;
		spriteVertices[3] = 0;
		spriteVertices[4] = 0;
		
		spriteVertices[5] = 0;
		spriteVertices[6] = actor.getHeight();
		spriteVertices[8] = 0;;
		spriteVertices[9] = 1.5f;
		
		spriteVertices[10] = actor.getWidth();
		spriteVertices[11] = actor.getHeight();
		spriteVertices[13] = 1.5f;
		spriteVertices[14] = 1.5f;
		
		spriteVertices[15] = actor.getWidth();
		spriteVertices[16] = 0;
		spriteVertices[18] = 1.5f;
		spriteVertices[19] = 0;
*/
		boxAtlas = new TextureAtlas(Gdx.files.internal("box.atlas"));

		HashMap<String, TextureRegion> boxmap = new HashMap<String, TextureRegion>();	
		boxmap.put("baiyang", boxAtlas.findRegion("baiyang"));
		boxmap.put("chunv", boxAtlas.findRegion("chunv"));
		boxmap.put("juxie", boxAtlas.findRegion("juxie"));
		boxmap.put("tianping", boxAtlas.findRegion("tianping"));
		boxmap.put("tianxie", boxAtlas.findRegion("tianxie"));
		boxmap.put("jinniu", boxAtlas.findRegion("jinniu"));
		boxmap.put("mojie", boxAtlas.findRegion("mojie"));
		boxmap.put("shuangzi", boxAtlas.findRegion("shuangzi"));
		boxmap.put("sheshou", boxAtlas.findRegion("sheshou"));
		boxmap.put("shuangnv", boxAtlas.findRegion("shuangnv"));
		boxmap.put("shuipin", boxAtlas.findRegion("shuipin"));
		boxmap.put("shizi", boxAtlas.findRegion("shizi"));
		boxmap.put("box", boxAtlas.findRegion("box"));
		
		try {
			xfl = xflparser.parse(Gdx.files.internal("box.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		box = new FAnimation(xfl, boxmap, 100, 100, 85, 85,  FAnimation.LOOP);
//		box.keyFrames[15].Debug();
		
/*		HashMap<String, Texture> hongmap = new HashMap<String, Texture>();
		hongmap.put("guang", new Texture(Gdx.files.internal("guang.png")));
		hongmap.put("guang2", new Texture(Gdx.files.internal("guang2.png")));
		hongmap.put("hong", new Texture(Gdx.files.internal("hong.png")));
		hongmap.put("xing", new Texture(Gdx.files.internal("xing.png")));
		try {
			xfl = xflparser.parse(Gdx.files.internal("hong.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	//	hong = new FAnimation(xfl, hongmap, 319.15f, 241.20f, 112f, 107f, FAnimation.LOOP);
	//	hong.keyFrames[3].Debug();
		
	/*	HashMap<String, Texture> icemap = new HashMap<String, Texture>();
		icemap.put("bingkuai", new Texture(Gdx.files.internal("bingkuai.png")));
		icemap.put("lie", new Texture(Gdx.files.internal("lie.png")));
		icemap.put("lie2", new Texture(Gdx.files.internal("lie2.png")));
		icemap.put("lie3", new Texture(Gdx.files.internal("lie3.png")));
		try {
			xfl = xflparser.parse(Gdx.files.internal("ice.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ice = new FAnimation(xfl, icemap, 100f, 100f, FAnimation.LOOP);
		
		HashMap<String, Texture> crossmap = new HashMap<String, Texture>();
		crossmap.put("guangmang", new Texture(Gdx.files.internal("guangmang.png")));
		crossmap.put("bangzi", new Texture(Gdx.files.internal("bangzi.png")));
		crossmap.put("yellowstar3", new Texture(Gdx.files.internal("yellowstar3.png")));
		crossmap.put("yuanxing", new Texture(Gdx.files.internal("yuanxing.png")));
		crossmap.put("yellowstar", new Texture(Gdx.files.internal("yellowstar.png")));
		crossmap.put("yellowstar4", new Texture(Gdx.files.internal("yellowstar4.png")));
		crossmap.put("zhuti", new Texture(Gdx.files.internal("zhuti.png")));
		crossmap.put("zhuti2", new Texture(Gdx.files.internal("zhuti2.png")));
		crossmap.put("zhutiguang", new Texture(Gdx.files.internal("zhutiguang.png")));
		crossmap.put("zhutihuang2", new Texture(Gdx.files.internal("zhutihuang2.png")));
		crossmap.put("zhutiguang3", new Texture(Gdx.files.internal("zhutiguang3.png")));
		crossmap.put("zhuti2guang", new Texture(Gdx.files.internal("zhuti2guang.png")));
		crossmap.put("zhuti2guang2", new Texture(Gdx.files.internal("zhuti2guang2.png")));
		crossmap.put("zhuti3guang3", new Texture(Gdx.files.internal("zhuti3guang3.png")));
		
		try {
			xfl = xflparser.parse(Gdx.files.internal("cross.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cross = new FAnimation(xfl, crossmap, 100f, 100f, FAnimation.LOOP);*/
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		// batch.setProjectionMatrix(cam.combined);
		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		
		time += Gdx.graphics.getDeltaTime();
		KFrame kframe1 = animtaion.getKeyFrame(time);
		KFrame kframe2 = box.getKeyFrame(time);
	//	KFrame kframe2 = box.keyFrames[15];
	//	kframe2.Debug();
	//	KFrame kframe3 = hong.getKeyFrame(time);
	//	KFrame kframe4 = ice.getKeyFrame(time);
	//	KFrame kframe5 = cross.getKeyFrame(time);

		batch.draw(actor, 200, 200, 100, 100);
		kframe1.draw(batch, 200, 200, 85, 85);
//		batch.draw(actor, spriteVertices, 0, 20);

		kframe2.draw(batch, 100, 100, 85, 85);
	//	kframe3.draw(batch, 0, 0, 85, 85);
	//	kframe4.draw(batch, 300, 300, 100, 100);
//		kframe5.draw(batch, 200, 200, 110, 110);
		batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
