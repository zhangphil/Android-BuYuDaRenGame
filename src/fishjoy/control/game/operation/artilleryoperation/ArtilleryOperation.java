package fishjoy.control.game.operation.artilleryoperation;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import fishjoy.control.game.GameConstants;

public class ArtilleryOperation implements GameConstants {

	public void weak(ArtillerySprite artillery,
			TiledTextureRegion artilleryTextureRegion) {
		if(artillery.getLevel() != ARTILLERY_MIN_RANK ){
			artillery.levelDown();
			artilleryTextureRegion.setCurrentTileIndex(artillery.getLevel());
		}	
	}

	public void strength(ArtillerySprite artillery,
			TiledTextureRegion artilleryTextureRegion) {
		if(artillery.getLevel() != ARTILLERY_MAX_RANK ){
			artillery.levelUp();
			artilleryTextureRegion.setCurrentTileIndex(artillery.getLevel());
		}
	}

	public void changeArtilleryDirection(ArtillerySprite artillery, float angle, Scene scene) {
		scene.detachChild(artillery);
		artillery.setRotation(angle);
		scene.getChild(LAYER_ARTILLERY).attachChild(artillery);
	}

	public BulletSprite lauchBullet(ArtillerySprite artillery, float angle, Scene scene) {
		artillery.createBullet();
		artillery.setBulletParameter(angle);
		BulletSprite bullet = artillery.getBullet();
		scene.getChild(LAYER_BULLET).attachChild(bullet);
		return bullet;
	}

}
