package com.cerspri.tapit.lite;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.BaseGameActivity;

import android.view.MotionEvent;

public class TapITv2Activity extends BaseGameActivity implements IOnSceneTouchListener{

	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;

	private Camera mCamera;
	private Font mFont;

	private Scene mCurrentScene, splashScene, mainScene;

	private Sprite splash;

	private BitmapTextureAtlas splashTextureAtlas;
	private ITextureRegion splashTextureRegion;

	private enum SceneType {
		SPLASH, MAIN, HELP, GAME, PAUSE, END_GAME
	}

	private SceneType currentScene = SceneType.SPLASH;

	@Override
	public EngineOptions onCreateEngineOptions() {
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				new FillResolutionPolicy(), mCamera);
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(),
				487, 297, TextureOptions.DEFAULT);
		splashTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(splashTextureAtlas, this, "background_2.png",
						0, 0);
		splashTextureAtlas.load();

		pOnCreateResourcesCallback.onCreateResourcesFinished();

	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		initSplashScene();
		pOnCreateSceneCallback.onCreateSceneFinished(this.splashScene);
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		mEngine.registerUpdateHandler(new TimerHandler(5f,
				new ITimerCallback() {
					public void onTimePassed(final TimerHandler pTimerHandler) {
						mEngine.unregisterUpdateHandler(pTimerHandler);
						loadResources();
						loadScenes();
						splash.detachSelf();
						mEngine.setScene(mainScene);
						currentScene = SceneType.MAIN;
						mainScene.setOnSceneTouchListener(TapITv2Activity.this);
					}
				}));

		pOnPopulateSceneCallback.onPopulateSceneFinished();

	}

	public void loadResources() {
		// Load your game resources here!
	}

	private void loadScenes() {
		// load your game here, you scenes
		mainScene = new Scene();
		mainScene.setBackground(new Background(50, 50, 50));
	}

	private void initSplashScene() {
		splashScene = new Scene();
		splash = new Sprite(0, 0, splashTextureRegion,
				mEngine.getVertexBufferObjectManager()) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};

		splashScene.setBackground(new SpriteBackground(splash));
		float scale = CAMERA_WIDTH * 1f / 487;
		float indent = (CAMERA_HEIGHT - scale * 297) / 2;
		if (scale < CAMERA_HEIGHT * 1f / 297) {
			scale = CAMERA_HEIGHT * 1f / 297;
			indent = (CAMERA_WIDTH - scale * 487) / 2;
			splashScene.setPosition(indent, 0);
		} else {
			splashScene.setPosition(0, indent);
		}
		splashScene.setScale(scale);
		splashScene.attachChild(splash);
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		System.out.println(pSceneTouchEvent.getX()+" "+pSceneTouchEvent.getY());
		return false;
	}
	
	
}