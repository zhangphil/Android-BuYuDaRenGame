package fishjoy.control.game.operation.sceneoperation;

import java.util.ArrayList;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import fishjoy.control.game.GameConstants;
import fishjoy.control.game.operation.ModelInformationController;
import fishjoy.control.game.operation.artilleryoperation.BulletSprite;
import fishjoy.control.game.operation.fishoperation.FishController;

public class ScoringOperation implements GameConstants{
	AnimatedSprite scoreSprite=null;
	public void ColDetBetweenBulletAndFish(ArrayList<FishController> movingFishList,
			ArrayList<BulletSprite> bulletList, ArrayList<AnimatedSprite> netList){
		
	}

	public int addScore(FishController fishController, TiledTextureRegion scoreTextureRegion, 
			BaseGameActivity activity, int mScore) {
		
		this.createScoreSprite(fishController.getX(), fishController.getY(),
				ModelInformationController.getInstance().getFishInformation(fishController.getFishName()).getScoreInformation().get_size_w(),
				ModelInformationController.getInstance().getFishInformation(fishController.getFishName()).getScoreInformation().get_size_h(),
				scoreTextureRegion,activity);
		
		if(fishController.getFishName() == Fish_Name.SARDINE)
			mScore = mScore+4;
		else if(fishController.getFishName() == Fish_Name.CLOWNFISH)
			mScore = mScore+10;
		else if(fishController.getFishName() == Fish_Name.PUFFERFISH)
			mScore = mScore+30;
		else if(fishController.getFishName() == Fish_Name.TORTOISE)
			mScore = mScore+50;
		else 
			mScore = mScore+100;
		
		return mScore;
		
	}

	private void createScoreSprite(float x, float y, float pWidth, float pHeight, TiledTextureRegion scoreTextureRegion,final BaseGameActivity activity) {
		scoreSprite = new AnimatedSprite(x, y, scoreTextureRegion.clone());
		scoreSprite.setSize(pWidth, pHeight);
		scoreSprite.animate(10,3,new IAnimationListener(){
			@Override
			public void onAnimationEnd(AnimatedSprite pAnimatedSprite){
				 activity.runOnUpdateThread(new Runnable() {
                     @Override
                     public void run() {
                    	activity.getEngine().getScene().getChild(LAYER_SCORE).detachChildren();
					}
				});
			}
		});
		activity.getEngine().getScene().getChild(LAYER_SCORE).attachChild(scoreSprite);
	}
}
