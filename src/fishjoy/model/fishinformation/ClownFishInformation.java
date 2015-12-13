package fishjoy.model.fishinformation;

import fishjoy.model.capturedfishinformation.CapturedClownFishInformation;
import fishjoy.model.scoreInformation.Score2Information;

public final class ClownFishInformation extends IFishInformation{

	public ClownFishInformation() {
		super(45,"clownfish_1.png",1,5,53,38,128,256, 
				new CapturedClownFishInformation(), new Score2Information());
	}
}
