package fishjoy.model.fishinformation;

import fishjoy.model.capturedfishinformation.CapturedSharkInformation;
import fishjoy.model.scoreInformation.Score5Information;

public final class SharkInformation extends IFishInformation {

	public SharkInformation() {
		super(50,"shark_1.png",1,4,176,69,256,256, 
				new CapturedSharkInformation(), new Score5Information());
	}
}
