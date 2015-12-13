package fishjoy.control.game.operation.artilleryoperation;

import java.util.HashMap;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import fishjoy.control.game.GameConstants;

public class ArtillerySprite extends AnimatedSprite implements GameConstants{

	private HashMap<Artillery_Rank, TiledTextureRegion> allBulletTextureRegionMap = new HashMap<Artillery_Rank, TiledTextureRegion>();
	private Artillery_Rank rank;
	private BulletSprite bullet;
	private TiledTextureRegion arrtilleryTextureRegion = null;
	private int level ;
	
	public ArtillerySprite(float pX, float pY,
			TiledTextureRegion arrtilleryTextureRegion, 
			HashMap<Artillery_Rank, TiledTextureRegion> allBulletTextureRegionMap) {
		super(pX, pY, arrtilleryTextureRegion);
		this.level = ARTILLERY_MIN_RANK ;
		this.rank = Artillery_Rank.RANK1;
		this.arrtilleryTextureRegion = arrtilleryTextureRegion;
		this.allBulletTextureRegionMap = allBulletTextureRegionMap;
		this.createBullet();
	}
	
	public void levelUp(){
		this.level++;
		this.changeBulletRank();
	}
	
	public void levelDown(){
		this.level--;
		this.changeBulletRank();
	}
	
	public int getLevel(){
		return this.level;
	}
	
	private void changeBulletRank(){
		
		if(this.level == 0)
			rank = Artillery_Rank.RANK1;
	    else if(this.level == 1)
			rank = Artillery_Rank.RANK2;
		else if(this.level == 2)
			rank = Artillery_Rank.RANK3;
		else if(this.level == 3)
			rank = Artillery_Rank.RANK4;
		else if(this.level == 4)
			rank = Artillery_Rank.RANK5;
	}
	
	public void createBullet(){
		//子弹出去的坐标是微调结果... 我也不是很确切清楚正确位置><
		this.bullet = new BulletSprite(
				CAMERA_WIDTH/2+7, CAMERA_HEIGHT - this.arrtilleryTextureRegion.getHeight()/2 +5, 
				allBulletTextureRegionMap.get(this.rank).clone(), this.rank);
	}

	public void setBulletParameter(float angle) {
		this.bullet.setRotation(angle);
		this.bullet.setVelocity();
	}

	public BulletSprite getBullet() {
		return this.bullet;
	}
	

}
