package fishjoy.control.game;

import fishjoy.control.GameSound;
import fishjoy.control.game.factory.FishFactory;
import fishjoy.control.game.operation.ModelInformationController;
import fishjoy.control.game.operation.TextureRegionCreator;
import fishjoy.control.game.operation.artilleryoperation.ArtilleryController;
import fishjoy.control.game.operation.artilleryoperation.BulletSprite;
import fishjoy.control.game.operation.fishoperation.FishController;
import fishjoy.control.game.operation.sceneoperation.SceneConverter;
import fishjoy.control.game.operation.sceneoperation.SceneMonitor;
import fishjoy.control.game.timer.Timer;
import fishjoy.control.record.MyDataBaseAdapter;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.StrokeFont;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.MathUtils;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class FishJoy extends BaseGameActivity implements IOnSceneTouchListener, GameConstants {
	MyDataBaseAdapter mDataBase;
	private Camera mCamera;
	private Scene mScene, mPauseScene;
	
	private TextureRegion mBackgroundTextureRegion, returnButtonTextureRegion, retryButtonTextureRegion, nextTextureRegion;
	private TiledTextureRegion artilleryTextureRegion, pauseButtonTextureRegion, soundButtonTextureRegion;	
	private HashMap<Fish_Name, TiledTextureRegion> allMovingFishTextureRegionMap = new HashMap<Fish_Name, TiledTextureRegion>();
	private HashMap<Fish_Name, TiledTextureRegion> allCapturedFishTextureRegionMap = new HashMap<Fish_Name, TiledTextureRegion>();
	private HashMap<Fish_Name, TiledTextureRegion> allScoreTextureRegionMap = new HashMap<Fish_Name, TiledTextureRegion>();
	private HashMap<Artillery_Rank, TiledTextureRegion> allBulletTextureRegionMap = new HashMap<Artillery_Rank, TiledTextureRegion>();
	private HashMap<Artillery_Rank, TiledTextureRegion> allNetTextureRegionMap = new HashMap<Artillery_Rank, TiledTextureRegion>();
	private HashMap<Artillery_Operate, TiledTextureRegion> allButtonTextureRegionMap = new HashMap<Artillery_Operate, TiledTextureRegion>();
	private HashMap<Integer, TextureRegion> allNumberTextureRegionMap = new HashMap<Integer, TextureRegion>();

	private TiledSprite pauseButton;
	private ArrayList<FishController> movingFishList = new ArrayList<FishController>();
	private ArrayList<BulletSprite> bulletList = new ArrayList<BulletSprite>();	
	
	private Font mTimeFont, mScoreFont;
	private StrokeFont mWinFont, mLoseFont;
	private ChangeableText mCurrentScoreText, mTimeText;
	
	protected  boolean mGameRunning = true;
	protected  boolean mGamePause = false;
	private int  mScore = 0, mMusicVolume, mSoundVolume, mDifficulty;
	private float mTime;

	private Texture timeBarTexture,  timeBarBackgroundTexture;
	private TextureRegion timeBarTextureRegion, timeBarBackgroundTextureRegion;
	private Sprite timeBar, timeBarBackground;
	
	private SceneConverter sceneConverter;
	private GameSound mSound;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Intent intent=getIntent(); 
		this.mMusicVolume=intent.getIntExtra("musicVolume", 0); 
        this.mSoundVolume=intent.getIntExtra("soundVolume", 0); 
        this.mDifficulty=intent.getIntExtra("difficulty", 1);        
	}
	
	@Override
	public Engine onLoadEngine() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, 
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera).setNeedsSound(true).setNeedsMusic(true));
	}

	@Override
	public void onLoadResources() {
		
		mDataBase=new MyDataBaseAdapter(this);
		mDataBase.open();
		
		TextureRegionFactory.setAssetBasePath("gfx/");

		mBackgroundTextureRegion = TextureRegionCreator.getInstance().createBackgroundTextureRegion(this, mDifficulty);

		TextureRegionCreator.getInstance().creatAllMovingFishTextureRegion(allMovingFishTextureRegionMap, this);
		TextureRegionCreator.getInstance().creatAllCapturedFishTextureRegion(allCapturedFishTextureRegionMap, this);
		TextureRegionCreator.getInstance().creatAllScoreTextureRegion(allScoreTextureRegionMap, this);
		
		artilleryTextureRegion = TextureRegionCreator.getInstance().createArtilleryTextureRegion(this);
		TextureRegionCreator.getInstance().creatAllBulletTextureRegion(allBulletTextureRegionMap, this);
		TextureRegionCreator.getInstance().createAllButtonTextureRegion(allButtonTextureRegionMap, this);		
		TextureRegionCreator.getInstance().createAllNetTextureRegion(allNetTextureRegionMap, this);
		TextureRegionCreator.getInstance().createAllNumberTextureRegion(allNumberTextureRegionMap, this);
		
		mTimeFont = TextureRegionCreator.getInstance().creatFont(this, 12, Color.WHITE);
		mScoreFont =TextureRegionCreator.getInstance().creatFont(this, 20, Color.YELLOW);
		
		mWinFont = TextureRegionCreator.getInstance().createStrokeFont(this,40, Color.RED, 2, Color.WHITE);
		mLoseFont = TextureRegionCreator.getInstance().createStrokeFont(this,40, Color.GREEN, 2, Color.WHITE);
				
		returnButtonTextureRegion = TextureRegionCreator.getInstance().createTextureRegion(128, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA, "back.png", this);
		retryButtonTextureRegion = TextureRegionCreator.getInstance().createTextureRegion(128, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA, "retry.png", this);
		pauseButtonTextureRegion = TextureRegionCreator.getInstance().createTiledTextureRegion(128, 32, "pause.png", 4, 1, this);
		nextTextureRegion = TextureRegionCreator.getInstance().createTextureRegion(128, 128,TextureOptions.BILINEAR_PREMULTIPLYALPHA, "next.png", this);
		soundButtonTextureRegion = TextureRegionCreator.getInstance().createTiledTextureRegion(1024, 512, "sound.png", 2, 1, this );

		timeBarTexture = new Texture(256, 32, TextureOptions.DEFAULT);
		this.timeBarTextureRegion = TextureRegionFactory.createFromAsset(this.timeBarTexture, this, "timebar.png", 0, 0);
		this.timeBarBackgroundTexture = new Texture(256, 32, TextureOptions.DEFAULT);
		this.timeBarBackgroundTextureRegion = TextureRegionFactory.createFromAsset(this.timeBarBackgroundTexture, this, "timebarbackground.png", 0, 0);
		this.mEngine.getTextureManager().loadTextures(this.timeBarTexture, this.timeBarBackgroundTexture);
		
		mSound = SceneConverter.initialGameSound(this, mMusicVolume, mSoundVolume);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		mScene = new Scene(1);
		for(int i = 0; i < LAYER_COUNT; i++) {
			mScene.attachChild(new Entity());
		}
		
		mScene.setBackgroundEnabled(false);
		mScene.getChild(LAYER_BACKGROUND).attachChild(new Sprite(0, 0, this.mBackgroundTextureRegion));

		ArtilleryController.getInstance().artilleryInitialization(
				artilleryTextureRegion, allBulletTextureRegionMap, allButtonTextureRegionMap,
				allNumberTextureRegionMap, mScene, ModelInformationController.getInstance().getGameInformation(mDifficulty).getInitialCoin() );
		mScene.setOnSceneTouchListener(this);

		sceneConverter = new SceneConverter(returnButtonTextureRegion, retryButtonTextureRegion, nextTextureRegion, soundButtonTextureRegion,
				this, FishJoy.this, mSound, mDifficulty);

		this.mPauseScene = new Scene(1);
		this.mPauseScene.setBackgroundEnabled(false);

		
		this.mTime = ModelInformationController.getInstance().getGameInformation(mDifficulty).getTotalTime();
		this.mTimeText = new ChangeableText((CAMERA_WIDTH - "ʱ��:XX:XX:XX".length()) * 0.5f - 135, 10, this.mTimeFont, "ʱ��:", "ʱ��:XX:XX:XX".length());
		
		mScene.getChild(LAYER_STATE).attachChild(mTimeText);

		mScene.registerUpdateHandler(new TimerHandler(1 / 20.0f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {				
				if (mGamePause == true){}
				else if (FishJoy.this.mTime >= 0  && mGameRunning == true ){
					mTimeText.setText(Timer.setTimeFormat(FishJoy.this.mTime));
					mTime -= 1 / 20.0f;
					timeBar.setWidth(timeBarBackground.getWidth()* mTime / ModelInformationController.getInstance().getGameInformation(mDifficulty).getTotalTime());
				}
				else
				{
					mTimeText.setText("ʱ�䣺00:00:00");
					mGameRunning = false;
					Log.d("MinScore", String.valueOf(ModelInformationController.getInstance().getGameInformation(mDifficulty).getMinScore()));
					sceneConverter.onGameEnd(mScore, ModelInformationController.getInstance().getGameInformation(mDifficulty).getMinScore(), mWinFont, mLoseFont, mScoreFont, mTimeFont, FishJoy.this, mGameRunning);
				}
			}
		}));
	
		pauseButton = new TiledSprite(CAMERA_WIDTH - 32, 0, this.pauseButtonTextureRegion) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {				
				if (!mGamePause){
					if(pSceneTouchEvent.isActionDown()){
						this.setCurrentTileIndex(1);
					}else if(pSceneTouchEvent.isActionUp()){
						this.setCurrentTileIndex(2);
						onPauseGame();
					}
				}
				else{
					if(pSceneTouchEvent.isActionDown()){
						this.setCurrentTileIndex(3);
					}else if(pSceneTouchEvent.isActionUp()){
						this.setCurrentTileIndex(0);
						onResumeGame();
					}
				}
				return true;
			}
		};
		this.mScene.getChild(LAYER_STATE).attachChild(this.pauseButton);
		this.mScene.registerTouchArea(this.pauseButton);

		this.mCurrentScoreText = new ChangeableText(330, 10, this.mTimeFont, this.mScore + "/" + ModelInformationController.getInstance().getGameInformation(mDifficulty).getMinScore(), "XXXX/XXXX".length());
		mScene.getChild(LAYER_STATE).attachChild(this.mCurrentScoreText);
		mScene.registerUpdateHandler(new TimerHandler(1 / 20.0f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {				
				mCurrentScoreText.setText( mScore + "/" + ModelInformationController.getInstance().getGameInformation(mDifficulty).getMinScore());
			}
		}));
	
		FishFactory.getInstance().createStartFishSequence(movingFishList, allMovingFishTextureRegionMap, mScene, mDifficulty);
		mScene.registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void reset() { }
			@Override
			public void onUpdate(final float pSecondsElapsed) {
				if(ModelInformationController.getInstance().getGameInformation(mDifficulty).getTotalTime() - mTime > 25){
					FishFactory.getInstance().createFish(movingFishList, allMovingFishTextureRegionMap, mScene);
					SceneMonitor.getInstance().ifFishOutOfScene_Move(movingFishList);
				}
				mScore = SceneMonitor.getInstance().Scoring(movingFishList, bulletList,allNetTextureRegionMap, 
						allCapturedFishTextureRegionMap, allScoreTextureRegionMap, FishJoy.this, mScore, mSound);
				SceneMonitor.getInstance().ifBulletOutOfLength_Move(bulletList, allNetTextureRegionMap, FishJoy.this, mSound);
			}
		});
		
		this.timeBar = new Sprite(0, 0, this.timeBarTextureRegion);
		this.timeBarBackground = new Sprite(0, 0, this.timeBarBackgroundTextureRegion);
		this.timeBar.setPosition((CAMERA_WIDTH-this.timeBar.getWidth())/2,35);
		this.timeBarBackground.setPosition((CAMERA_WIDTH-this.timeBar.getWidth())/2,35);
		
		mScene.getChild(LAYER_STATE).attachChild(this.timeBarBackground);
		mScene.getChild(LAYER_STATE).attachChild(this.timeBar);

		
		return mScene;
	}

	@Override
	public void onLoadComplete() {}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, final TouchEvent pSceneTouchEvent) {
		this.runOnUpdateThread(new Runnable(){
			int x = (int) pSceneTouchEvent.getX() - CAMERA_WIDTH/2;
			int y = (int) pSceneTouchEvent.getY() - (CAMERA_HEIGHT - artilleryTextureRegion.getHeight()/2);
			float angle = MathUtils.radToDeg((float)Math.atan2(x, -y));
			public void run() {
				if(angle >= -95 && angle <= 95){
					BulletSprite bulletSprite = ArtilleryController.getInstance().lauchBullet(angle, mScene);
					if(bulletSprite != null)
						bulletList.add(bulletSprite);
				}		
			}
		});
		return false;
	}

	public void onPauseGame() {
		mGamePause = true;
		sceneConverter.onPauseGame(this, mPauseScene, pauseButton);
	}
	
	public void onResumeGame() {	
		mGamePause = false;
		sceneConverter.onResumeGame(this);
	}
	
	public MyDataBaseAdapter get_DataBase() {
		return mDataBase;
	}

	public int get_Score() {
		return mScore;
	}
	
	public int get_Difficulty(){
		return mDifficulty;
	}
}