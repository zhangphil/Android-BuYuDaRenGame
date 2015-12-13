package fishjoy.control.game.operation.fishoperation;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;

import fishjoy.control.game.GameConstants;
import fishjoy.control.game.operation.ModelInformationController;
import fishjoy.control.game.operation.TextureRegionCreator;
import fishjoy.control.game.operation.artilleryoperation.BulletSprite;

public class FishController extends AnimatedSprite implements GameConstants{
	
	protected PhysicsHandler mPhysicsHandler;	
	private FishOperation fishOperation;
	AnimatedSprite capturedFish = null;
	
	//�������
	int X,Y,A,way,up;
	
	//��ǰ״̬��Ϣ
	int count_of_circle;
	Fish_Name name;
	Fish_Move move;
	float current_X,current_Y;
	float current_rotation;
	Move_Direction direction;
	Edge_Position position;
	
	public FishController(Fish_Name _name,TiledTextureRegion pTiledTextureRegion) {
		super(0, 0, pTiledTextureRegion);
		mPhysicsHandler=new PhysicsHandler(this);
		this.registerUpdateHandler(mPhysicsHandler);
		name=_name;
	}
	
	public void set_Fish_Operation(FishOperation operation)
	{
		fishOperation=operation;
	}
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		fishOperation.fishOnManagedUpdate();
	}
	
	
	public Fish_Name getFishName(){
		return this.name;
	}

	public boolean isOutOfScene() {
		if(fishOperation.isOutOfBound())
			return true;
		return false;
	}

	public void captured(TiledTextureRegion capturedFishTextureRegion,final BaseGameActivity activity){
//		this.detachSelf();
		capturedFish =
			new AnimatedSprite(this.getX(), this.getY(),capturedFishTextureRegion.clone());
//		capturedFish.setSize(ResourcesOperation.getInstance().getFishInformation(name).getCaptureFishInformation().get_size_w(),
//				ResourcesOperation.getInstance().getFishInformation(name).getCaptureFishInformation().get_size_h());
		capturedFish.setSize(ModelInformationController.getInstance().getFishInformation(name).getCaptureFishInformation().get_size_w(),
				ModelInformationController.getInstance().getFishInformation(name).getCaptureFishInformation().get_size_h());
		capturedFish.animate(80, 2, new IAnimationListener(){
			@Override
			public void onAnimationEnd(AnimatedSprite pAnimatedSprite){
				 activity.runOnUpdateThread(new Runnable() {
                     @Override
                     public void run() {
                    	 Log.d("test","test");
						activity.getEngine().getScene().getChild(LAYER_FISH).detachChild(capturedFish);
					}
				});
			}
		});
		capturedFish.setRotation(this.getRotation());
		activity.getEngine().getScene().getChild(LAYER_FISH).attachChild(capturedFish);
	}

	public boolean canBeCaughtByBullet(BulletSprite bulletSprite) {
		
		if(name == Fish_Name.SARDINE)
			return CapturedProbability.getInstance().SardineCanBeCapturedByBullet(bulletSprite.getRank());
		else if(name == Fish_Name.CLOWNFISH)
			return CapturedProbability.getInstance().ClownFishCanBeCapturedByBullet(bulletSprite.getRank());
		else if(name == Fish_Name.PUFFERFISH)
			return CapturedProbability.getInstance().PufferFishCanBeCapturedByBullet(bulletSprite.getRank());
		else if(name == Fish_Name.TORTOISE)
			return CapturedProbability.getInstance().TortoiseCanBeCapturedByBullet(bulletSprite.getRank());
		else if(name == Fish_Name.SHARK)
			return CapturedProbability.getInstance().SharkCanBeCapturedByBullet(bulletSprite.getRank());

		return false;
	}
	
	public boolean canBeCaughtByNet(AnimatedSprite net, BulletSprite bulletSprite) {
		
		if(name == Fish_Name.SARDINE)
			return CapturedProbability.getInstance().SardineCanBeCapturedByNet(bulletSprite.getRank());
		else if(name == Fish_Name.CLOWNFISH)
			return CapturedProbability.getInstance().ClownFishCanBeCapturedByNet(bulletSprite.getRank());
		else if(name == Fish_Name.PUFFERFISH)
			return CapturedProbability.getInstance().PufferFishCanBeCapturedByNet(bulletSprite.getRank());
		else if(name == Fish_Name.TORTOISE)
			return CapturedProbability.getInstance().TortoiseCanBeCapturedByNet(bulletSprite.getRank());
		else if(name == Fish_Name.SHARK)
			return CapturedProbability.getInstance().SharkCanBeCapturedByNet(bulletSprite.getRank());
		
		return false;
	}
	
	
}
	

