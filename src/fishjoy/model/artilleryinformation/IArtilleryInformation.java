package fishjoy.model.artilleryinformation;

import fishjoy.model.IEntityInformation;
import fishjoy.model.bulletinformation.IBulletInformation;

public abstract class IArtilleryInformation extends IEntityInformation {

	private IBulletInformation bulletInfor;
	
	public IArtilleryInformation(String path, int sizeW, int sizeH,
			int textureRegionWidth, int textureRegionHeight, IBulletInformation bulletInfor) {
		super(path, sizeW, sizeH, textureRegionWidth, textureRegionHeight);
		this.bulletInfor = bulletInfor;
	}
	
	public IBulletInformation getBulletInformation(){
		return this.bulletInfor;
	}

}
