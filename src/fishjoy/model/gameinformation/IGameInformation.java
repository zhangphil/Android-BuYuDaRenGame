package fishjoy.model.gameinformation;

import fishjoy.model.IEntityInformation;

public class IGameInformation extends IEntityInformation{

	private int mTotalTime, mMinScore, mInitialCoin;
	
	public IGameInformation(String path, int sizeW, int sizeH,
			int textureRegionWidth, int textureRegionHeight, int totalTime, int minScore, int initialCoin) {
		super(path, sizeW, sizeH, textureRegionWidth, textureRegionHeight);
		this.mTotalTime = totalTime;
		this.mMinScore = minScore;
		this.mInitialCoin = initialCoin;
	}
	
	public int getTotalTime(){
		return this.mTotalTime;
	}
	
	public int getMinScore(){
		return this.mMinScore;
	}
	
	public int getInitialCoin(){
		return this.mInitialCoin;
	}

}
