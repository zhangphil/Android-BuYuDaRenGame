package fishjoy.control.game.operation.artilleryoperation;

import java.util.HashMap;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import fishjoy.control.game.GameConstants;

public class ArtilleryController implements GameConstants {
	
	private static ArtilleryController uniqueInstance;
	
	private static ArtillerySprite artillery = null;
	private final ArtilleryOperation artilleryOperation = new ArtilleryOperation();
	
	private static HashMap<Integer,TextureRegion> allNumberTextureRegionMap = new HashMap<Integer,TextureRegion>();
	private static int mInitialCoin;
	
	private ArtilleryController(){}
	
	public static ArtilleryController getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new ArtilleryController();
		}
		return uniqueInstance;
	}
	
	private TiledSprite createSingleButton(final Artillery_Operate operate, TiledTextureRegion pTiledTextureRegion, 
			final TiledTextureRegion artilleryTextureRegion, final Scene scene ){
		
		int pX;
		if(operate.equals(Artillery_Operate.WEAKEN))
			pX = CAMERA_WIDTH /2 - artilleryTextureRegion.getWidth()/10 - pTiledTextureRegion.getWidth()/2 +9;
		else
			pX = CAMERA_WIDTH /2 - artilleryTextureRegion.getWidth()/10 - pTiledTextureRegion.getWidth()/2 +121;
		
		int pY = CAMERA_HEIGHT - pTiledTextureRegion.getHeight();
		
		TiledSprite button = new TiledSprite(pX, pY, pTiledTextureRegion){		
			
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
				if(pSceneTouchEvent.isActionDown()){
					this.setCurrentTileIndex(1);
					
					if(operate.equals(Artillery_Operate.WEAKEN))
						artilleryOperation.weak(artillery, artilleryTextureRegion);
					else
						artilleryOperation.strength(artillery, artilleryTextureRegion);
					
				}else if(pSceneTouchEvent.isActionUp()){
					this.setCurrentTileIndex(0);
				}
				return true;				
			}
		};
		return button;	
	}
	
	public void artilleryInitialization(TiledTextureRegion artilleryTextureRegion,
			HashMap<Artillery_Rank, TiledTextureRegion> allBulletTextureRegionMap, 
			HashMap<Artillery_Operate, TiledTextureRegion> allButtonTextureRegionMap, 
			HashMap<Integer,TextureRegion> allNumberTextureRegionMap,
			Scene scene , int mInitialCoin){
		
		//about artillery
		int px = (CAMERA_WIDTH - artilleryTextureRegion.getWidth()/5)/2 + 18;
		int py = CAMERA_HEIGHT - artilleryTextureRegion.getHeight()/8*7 + 10;
		artillery = new ArtillerySprite(px, py, artilleryTextureRegion, allBulletTextureRegionMap);
		scene.getChild(LAYER_ARTILLERY).attachChild(artillery);
		
		this.allNumberTextureRegionMap.putAll(allNumberTextureRegionMap);
		this.mInitialCoin = mInitialCoin;
		
		this.showCoins(scene);
		
		//about button
		this.createButtons(allButtonTextureRegionMap, artilleryTextureRegion, scene);
		
	}
	
	private void createButtons(HashMap<Artillery_Operate, TiledTextureRegion> allButtoneTextureRegionMap, 
			TiledTextureRegion artilleryTextureRegion, Scene scene){

	TiledSprite leftButton = this.createSingleButton(Artillery_Operate.WEAKEN, allButtoneTextureRegionMap.get(Artillery_Operate.WEAKEN), artilleryTextureRegion, scene);	
	TiledSprite rightButton = this.createSingleButton(Artillery_Operate.STRENGTHEN, allButtoneTextureRegionMap.get(Artillery_Operate.STRENGTHEN), artilleryTextureRegion, scene);

		
	scene.registerTouchArea(leftButton);
	scene.registerTouchArea(rightButton);
	
	scene.getChild(LAYER_BUTTON).attachChild(leftButton);
	scene.getChild(LAYER_BUTTON).attachChild(rightButton);
	
}

	public BulletSprite lauchBullet(float angle, Scene scene) {
		
		if(mInitialCoin - artillery.getLevel()-1 >= 0){
			mInitialCoin = mInitialCoin - artillery.getLevel()-1;
			this.showCoins(scene);
		
			artilleryOperation.changeArtilleryDirection(artillery, angle, scene);
			return artilleryOperation.lauchBullet(artillery, angle, scene);
		}
		
		return null;
	}

	private void showCoins(Scene scene) {
		int thousand = mInitialCoin/1000;
		int hundred = (mInitialCoin - thousand*1000)/100;
		int ten = (mInitialCoin - thousand*1000 - hundred*100)/10;
		int one = mInitialCoin - thousand*1000 - hundred*100 - ten*10;
		
		Sprite thousandSprite = new Sprite(75, 295, allNumberTextureRegionMap.get(thousand).clone());
		Sprite hundredSprite = new Sprite(95, 295, allNumberTextureRegionMap.get(hundred).clone());
		Sprite tenSprite = new Sprite(115, 295, allNumberTextureRegionMap.get(ten).clone());
		Sprite oneSprite = new Sprite(135, 295, allNumberTextureRegionMap.get(one).clone());
		
		thousandSprite.setSize(20, 25);
		hundredSprite.setSize(20, 25);
		tenSprite.setSize(20, 25);
		oneSprite.setSize(20, 25);
		
		scene.attachChild(thousandSprite);
		scene.attachChild(hundredSprite);
		scene.attachChild(tenSprite);
		scene.attachChild(oneSprite);
				
	}

}
