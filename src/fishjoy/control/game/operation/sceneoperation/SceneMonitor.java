package fishjoy.control.game.operation.sceneoperation;

import java.util.ArrayList;
import java.util.HashMap;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import fishjoy.control.GameSound;
import fishjoy.control.game.GameConstants;
import fishjoy.control.game.operation.artilleryoperation.BulletSprite;
import fishjoy.control.game.operation.fishoperation.FishController;

public class SceneMonitor implements GameConstants{
	private static SceneMonitor uniqueInstance;
	private static ScoringOperation scoringOperation = new ScoringOperation();
	private SceneMonitor(){}
	
	public static SceneMonitor getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new SceneMonitor();
		return uniqueInstance;
	}

	public int Scoring(ArrayList<FishController> movingFishList,
			ArrayList<BulletSprite> bulletList, 
			HashMap<Artillery_Rank, TiledTextureRegion> allNetTextureRegionMap,
			HashMap<Fish_Name, TiledTextureRegion> allCapturedFishTextureRegionMap,
			HashMap<Fish_Name, TiledTextureRegion> allScoreTextureRegionMap,
			BaseGameActivity activity,int mScore, GameSound mSound){
		
		if(!bulletList.isEmpty()){
			for(int i = 0; i < bulletList.size(); i++){

				ArrayList<FishController> fishCollided = new ArrayList<FishController>();
				for(int j = 0; j < movingFishList.size(); j++){
					if(movingFishList.get(j).collidesWith(bulletList.get(i)))
						fishCollided.add(movingFishList.get(j));
				}
				AnimatedSprite net = null;
				if(!fishCollided.isEmpty()){
					net = bulletList.get(i).lauchNet(allNetTextureRegionMap, activity, mSound);
//					bulletList.get(i).detachSelf();
					
					for(int j = 0; j < fishCollided.size(); j++){
						if(fishCollided.get(j).canBeCaughtByBullet(bulletList.get(i))){
							fishCollided.get(j).captured(
									allCapturedFishTextureRegionMap.get(fishCollided.get(j).getFishName()),activity);
							mScore = scoringOperation.addScore(fishCollided.get(j),
									allScoreTextureRegionMap.get(fishCollided.get(j).getFishName()), activity, mScore);
							movingFishList.remove(fishCollided.get(j));
						}
					}
					fishCollided.clear();
					
					for(int j = 0; j < movingFishList.size(); j++){
						if(movingFishList.get(j).collidesWith(net))
							fishCollided.add(movingFishList.get(j));
					}
					if(!fishCollided.isEmpty()){
						for(int j = 0; j < fishCollided.size(); j++){
							if(fishCollided.get(j).canBeCaughtByNet(net, bulletList.get(i))){
								fishCollided.get(j).captured(
										allCapturedFishTextureRegionMap.get(fishCollided.get(j).getFishName()),activity);
								mScore = scoringOperation.addScore(fishCollided.get(j),
										allScoreTextureRegionMap.get(fishCollided.get(j).getFishName()), activity, mScore);
								movingFishList.remove(fishCollided.get(j));
							}
						}
					}
					bulletList.remove(bulletList.get(i));
					break;							
				}
			}		
		}
		return mScore;
	}

	public void ifFishOutOfScene_Move(ArrayList<FishController> movingFishList) {
		for(int i = 0; i < movingFishList.size(); i++ ){
			if(movingFishList.get(i).isOutOfScene()){
//				movingFishList.get(i).detachSelf();
				movingFishList.remove(movingFishList.get(i));
			}
		}	
	}

	public void ifBulletOutOfLength_Move(ArrayList<BulletSprite> bulletList,
			HashMap<Artillery_Rank, TiledTextureRegion> allNetTextureRegionMap,
		    BaseGameActivity activity, GameSound mSound) {
		for(int i = 0; i < bulletList.size(); i++){
			if(bulletList.get(i).isOutOfLength()){
//				bulletList.get(i).detachSelf();
				AnimatedSprite net = bulletList.get(i).lauchNet(allNetTextureRegionMap, activity, mSound);
				bulletList.remove(bulletList.get(i));
			}
		}
	}


}
