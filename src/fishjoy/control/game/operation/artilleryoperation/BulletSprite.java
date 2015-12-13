package fishjoy.control.game.operation.artilleryoperation;

import java.util.HashMap;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.MathUtils;

import fishjoy.control.GameSound;
import fishjoy.control.game.GameConstants;
import fishjoy.control.game.operation.ModelInformationController;
import fishjoy.control.game.operation.TextureRegionCreator;

public class BulletSprite extends AnimatedSprite implements GameConstants{
	
	private static final float BULLET_VELOCITY = 200;

	private PhysicsHandler bulletphysic;
	
	private float Velocity_X , Velocity_Y;
	
	private Artillery_Rank rank;
	
	AnimatedSprite net = null;
	
	public BulletSprite(float pX, float pY,TiledTextureRegion pTiledTextureRegion, Artillery_Rank rank) {
		super(pX, pY, pTiledTextureRegion);			
		bulletphysic = new PhysicsHandler(this);
		this.registerUpdateHandler(bulletphysic);
		this.rank = rank;

	}
	
	public void bulletRotation(float deg){
		this.setRotation(deg);
	}
	
	public void setVelocity(){
		Velocity_X = (float) (BULLET_VELOCITY * Math.sin(MathUtils.degToRad(getRotation())));
		Velocity_Y = (float) (BULLET_VELOCITY * Math.cos(MathUtils.degToRad(getRotation())));
		bulletphysic.setVelocity(Velocity_X, -Velocity_Y);
	}
	
	public Artillery_Rank getRank(){
		return this.rank;
	}
	
	public AnimatedSprite lauchNet(HashMap<Artillery_Rank, TiledTextureRegion> allNetTextureRegionMap, 
			final  BaseGameActivity activity, GameSound sound){
		net = new AnimatedSprite(this.getX()-86, this.getY()-80, 
				allNetTextureRegionMap.get(this.rank).clone());
		
		net.setSize(ModelInformationController.getInstance().getArtilleryInformation(
						this.getRank()).getBulletInformation().getNetInformation().get_size_w(), 
						ModelInformationController.getInstance().getArtilleryInformation(
						this.getRank()).getBulletInformation().getNetInformation().get_size_w());
		net.animate(80, false, new IAnimationListener(){
			@Override
			public void onAnimationEnd(AnimatedSprite pAnimatedSprite){
				 activity.runOnUpdateThread(new Runnable() {
                     @Override
                     public void run() {
						activity.getEngine().getScene().getChild(LAYER_NET).detachChild(net);
					}
				});
			}
		});
		activity.getEngine().getScene().getChild(LAYER_NET).attachChild(net);
		sound.netOpen();
		return net;
	}

	public boolean isOutOfLength() {
		if(this.getY() < ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().getMinWeight()
				|| this.getY() > CAMERA_WIDTH
				|| this.getX() < ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().getMinHeight()
				|| this.getX() > ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().getMaxHeight())
			return true;
		return false;
	}

	
	
}
