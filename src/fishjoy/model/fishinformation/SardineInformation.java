package fishjoy.model.fishinformation;

import fishjoy.model.capturedfishinformation.CapturedSardineInformation;
import fishjoy.model.scoreInformation.Score1Information;

public final class SardineInformation extends IFishInformation {

	public SardineInformation() {
		super(60,"sardine_1.png",1,5, 36, 18,64,256,
				new CapturedSardineInformation(), new Score1Information());
	}

}
