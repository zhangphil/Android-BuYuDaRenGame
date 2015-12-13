package fishjoy.model.fishinformation;

import fishjoy.model.capturedfishinformation.CapturedTortoiseInformation;
import fishjoy.model.scoreInformation.Score4Information;

public final class TortoiseInformation extends IFishInformation {

	public TortoiseInformation() {
		super(50,"tortoise_1.png",1,5, 90,33,128,256,
				new CapturedTortoiseInformation(),
				new Score4Information());
		// TODO Auto-generated constructor stub
	}

}
