package fishjoy.control.game.operation.sceneoperation;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.entity.modifier.RotationModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.HorizontalAlign;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fishjoy.control.GameSound;
import fishjoy.control.game.FishJoy;
import fishjoy.control.game.GameConstants;
import fishjoy.control.menu.MainMenu;
import fishjoy.control.menu.R;
import fishjoy.control.record.MyDataBaseAdapter;

public class SceneConverter implements GameConstants{
	
	private Sprite returnButton, retryButton, nextButton;
	private  TiledSprite soundButton;
	private static int  mMusicVolume, mSoundVolume;
	private static Music mBgMusic;
	boolean isEstablished=false;
	
	public SceneConverter(TextureRegion returnButtonTextureRegion, TextureRegion retryButtonTextureRegion, 
			TextureRegion nextTextureRegion, TiledTextureRegion soundButtonTextureRegion, 
			FishJoy activity, Context context, GameSound mSound, int mDifficulty){
		nextButton = this.createNextButton(activity,context,nextTextureRegion);
		returnButton = this.createReturnButton(activity,context, returnButtonTextureRegion, mMusicVolume, mSoundVolume);
		retryButton = this.createRetryButton(activity,context, retryButtonTextureRegion, mMusicVolume, mSoundVolume, mDifficulty);
		soundButton = this.createSoundButton(activity, soundButtonTextureRegion, mBgMusic, mSound, mSoundVolume);
		mBgMusic.play();
	}
	
	public static GameSound initialGameSound(BaseGameActivity activity, int mMusicVolume, int mSoundVolume){
		
		MusicFactory.setAssetBasePath("mfx/");
		
		try {
			mBgMusic = MusicFactory.createMusicFromAsset(activity.getEngine().getMusicManager(), activity, "scg.ogg");
			mBgMusic.setLooping(true);
		} catch (final IOException e) {
			Debug.e(e);
		}
		SceneConverter.mBgMusic.setVolume(mMusicVolume);
		SceneConverter.mMusicVolume = mMusicVolume;
		SceneConverter.mSoundVolume = mSoundVolume;
		
		GameSound mSound = new GameSound();
		mSound.create(activity);
		mSound.setVolume(mSoundVolume);
		
		return mSound;
	}

	public TiledSprite createSoundButton(BaseGameActivity activity, TiledTextureRegion soundButtonTextureRegion,
			final Music mBgMusic, final GameSound mSound, final int mSoundVolume) {
		TiledSprite soundButton = new TiledSprite(0, 0, soundButtonTextureRegion) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {				
				if (mBgMusic.isPlaying()){
					if(pSceneTouchEvent.isActionUp()){
						this.setCurrentTileIndex(1);
						mBgMusic.pause();
						mSound.setVolume(0);
					}
				}
				else{
					if(pSceneTouchEvent.isActionUp()){
						this.setCurrentTileIndex(0);
						mBgMusic.resume();
						mSound.setVolume(mSoundVolume);
					}
				}
				return true;
			}
		};
		soundButton.setPosition((CAMERA_WIDTH - soundButton.getWidth())/2, (CAMERA_HEIGHT - soundButton.getHeight())/2);
		return soundButton;
	}

	public Sprite createRetryButton(final BaseGameActivity activity, final Context context, 
			TextureRegion retryButtonTextureRegion, final int mMusicVolume, final int mSoundVolume, final int mDifficulty) {
		
		Sprite retryButton = new Sprite(CAMERA_WIDTH/2 + 25, CAMERA_HEIGHT/2 + 40, retryButtonTextureRegion) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Intent intent = new Intent();
				intent.putExtra("musicVolume", mMusicVolume);
				intent.putExtra("soundVolume", mSoundVolume);
				intent.putExtra("difficulty", mDifficulty);
				intent.setClass(context, FishJoy.class);
				activity.startActivity(intent);
				activity.finish();
				return true;
			}
		};
		return retryButton;
		
	}

	public Sprite createReturnButton(final BaseGameActivity activity, final Context context, TextureRegion returnButtonTextureRegion,
			final int mMusicVolume, final int mSoundVolume) {
		
		Sprite returnButton = new Sprite(CAMERA_WIDTH/2 - 150, CAMERA_HEIGHT/2 + 40, returnButtonTextureRegion) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Intent intent = new Intent();
				intent.putExtra("musicVolume", mMusicVolume);
				intent.putExtra("soundVolume", mSoundVolume);
				intent.setClass(context, MainMenu.class);
				activity.startActivity(intent);
				activity.finish();
				return true;
			}
		};
		return returnButton;
		
	}

	public Sprite createNextButton(final FishJoy activity, final Context context, TextureRegion nextTextureRegion) {
		
		final EditText editText=new EditText(context);
		Sprite nextButton = new Sprite(CAMERA_WIDTH * 0.5f + 100, CAMERA_HEIGHT * 0.5f, nextTextureRegion) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				
				if(!isEstablished)
				{
				LayoutInflater mInflater = LayoutInflater.from(context);
				final View view = mInflater.inflate(R.layout.newrecord, null);
				AlertDialog.Builder dialog=new AlertDialog.Builder(context);
				
				dialog.setIcon(R.drawable.icon);
				dialog.setView(view);
				dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				      public void onClick(DialogInterface dialog, int which) {
				          //unimplemented method 
				    	  EditText editText=(EditText)view.findViewById(R.id.edittext);
				    	  if(editText.getText().toString().equals(""))
				    	  {
				    		  Toast.makeText(activity, "����������", Toast.LENGTH_SHORT).show();
				    		  dialog.cancel();
				    	  }
				    	  else
				    	  {
				    		  String name=editText.getText().toString();
				    		//plug in Database and open it for more operations
				    		  MyDataBaseAdapter mDataBase=activity.get_DataBase();
				    		  mDataBase.insertData(name, String.valueOf(activity.get_Difficulty()),String.valueOf(activity.get_Score()));
				    		  Toast.makeText(activity, "лл", Toast.LENGTH_SHORT).show();			    		  
				    		  dialog.dismiss();
				    		  mDataBase.close();
				    	  }
				         }});
				
				dialog.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				      public void onClick(DialogInterface dialog, int which) {
				    	 //unimplemented method 
				    	  dialog.dismiss();
				         }});
				dialog.create().show();//show the dialog
				isEstablished=true;
				}
				return true;
			}
		};
		
		return nextButton;
		
	}

	public void onGameEnd(int mScore, int minRecord, Font mWinFont, Font mLoseFont, Font mScoreFont, Font mTimeFont,
			 FishJoy activity, boolean mGameRunning) {
		
		Text mWinText = this.createText(mWinFont,"YOU WIN!",-50);
		Text mLoseText = this.createText(mLoseFont, "GAME OVER!",-50);
		Text mScoreText = this.createText(mScoreFont,"SCORE: " + mScore,+10);
		
			mGameRunning = false;
			int Max=minRecord;		
			MyDataBaseAdapter mDataBase=activity.get_DataBase();
			Cursor cursor=mDataBase.fetchAllData();
			int row=cursor.getCount(),temp;
			if(row!=0)
			{
				cursor.moveToFirst();
				if(Integer.valueOf(cursor.getString(2)).intValue()==activity.get_Difficulty())
					Max=Integer.valueOf(cursor.getString(3)).intValue(); 
				if(cursor != null)
				{
					while(cursor.moveToNext())
					{
						if(Integer.valueOf(cursor.getString(2)).intValue()==activity.get_Difficulty())
						{
							temp=Integer.valueOf(cursor.getString(3)).intValue(); 
							if(temp>Max)
								Max=temp;
						}
					}
				}
			}

			if (mScore >= Max)
			{
				if(!isEstablished){
					Scene recordScene = this.createNewScene(mWinText, mScoreText);	
					Text inputText = new Text(0, 0, mTimeFont, "�������µļ�¼����������Ĵ���", HorizontalAlign.CENTER);
					inputText.setPosition((CAMERA_WIDTH - inputText.getWidth()) * 0.5f, (CAMERA_HEIGHT - inputText.getHeight()) * 0.5f+40);
					recordScene.attachChild(inputText);			
					recordScene.attachChild(nextButton);
					recordScene.registerTouchArea(nextButton);
					activity.getEngine().getScene().setChildScene(recordScene, false, true, true);
				}
				else{
					activity.getEngine().getScene().clearChildScene();
					Scene newScene = creaNewSceneWithButton(returnButton,retryButton);
					activity.getEngine().getScene().setChildScene(newScene, false, true, true);		
				}
			}
			else if (mScore >= minRecord)
			{
				if(!isEstablished){
					Scene winScene = this.creaNewSceneWhileAddButton(mWinText,mScoreText, returnButton,retryButton);
					activity.getEngine().getScene().setChildScene(winScene, false, true, true);
				}
				else{
					activity.getEngine().getScene().clearChildScene();
					Scene newScene = creaNewSceneWithButton(returnButton,retryButton);
					activity.getEngine().getScene().setChildScene(newScene, false, true, true);	
				}
			}
			else
			{
				Scene loseScene = this.creaNewSceneWhileAddButton(mLoseText, mScoreText, returnButton, retryButton);
				activity.getEngine().getScene().setChildScene(loseScene, false, true, true);
			}
	}
	
	private Scene creaNewSceneWhileAddButton(Text text, Text mScoreText,
			Sprite returnButton, Sprite retryButton) {
		Scene newScene = this.createNewScene(text, mScoreText);
		newScene.attachChild(returnButton);
		newScene.attachChild(retryButton);
		newScene.registerTouchArea(returnButton);
		newScene.registerTouchArea(retryButton);
		
		return newScene;
	}
	
	private Scene creaNewSceneWithButton(Sprite returnButton, Sprite retryButton) {
		Scene newScene = new Scene(1);
		newScene.setBackgroundEnabled(false);
		newScene.attachChild(returnButton);
		newScene.attachChild(retryButton);
		newScene.registerTouchArea(returnButton);
		newScene.registerTouchArea(retryButton);
		
		return newScene;
	}

	private Scene createNewScene(Text text, Text mScoreText) {
		Scene newScene = new Scene(1);
		newScene.setBackgroundEnabled(false);
		newScene.attachChild(text);
		newScene.attachChild(mScoreText);
		return newScene;
	}

	private Text createText(Font mFont, String string, int i) {
		Text text = new Text(0, 0, mFont, string, HorizontalAlign.CENTER);
		text.setPosition((CAMERA_WIDTH - text.getWidth()) * 0.5f, (CAMERA_HEIGHT - text.getHeight()) * 0.5f+i);
		text.registerEntityModifier(new ScaleModifier(2, 0.1f, 2.0f));
		text.registerEntityModifier(new RotationModifier(2, 0, 720));
		return text;
	}

	public void onPauseGame(BaseGameActivity activity, Scene mPauseScene,
			 TiledSprite pauseButton) {
		
		returnButton.setPosition(CAMERA_WIDTH/2 + 50, (CAMERA_HEIGHT - returnButton.getHeight())/2);
		retryButton.setPosition(CAMERA_WIDTH/2 - retryButton.getWidth()-50, (CAMERA_HEIGHT - retryButton.getHeight())/2);
		mPauseScene.attachChild(returnButton);
		mPauseScene.attachChild(retryButton);
		mPauseScene.registerTouchArea(returnButton);
		mPauseScene.registerTouchArea(retryButton);
		mPauseScene.attachChild(pauseButton);
		mPauseScene.registerTouchArea(pauseButton);
		mPauseScene.attachChild(soundButton);
		mPauseScene.registerTouchArea(soundButton);
		
		activity.getEngine().getScene().setChildScene(mPauseScene, false, true, true);	
	}
	
	public void onResumeGame(BaseGameActivity activity) {

		activity.getEngine().getScene().clearChildScene();

		returnButton.setPosition(CAMERA_WIDTH/2 - 150, CAMERA_HEIGHT/2 + 40);
		retryButton.setPosition(CAMERA_WIDTH/2 + 25, CAMERA_HEIGHT/2 + 40);
	}

	
}
