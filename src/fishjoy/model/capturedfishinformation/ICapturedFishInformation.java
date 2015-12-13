package fishjoy.model.capturedfishinformation;

import fishjoy.model.IEntityInformation;

public class ICapturedFishInformation extends IEntityInformation {

	int line,row,speed;
	public ICapturedFishInformation(String path, int sizeW, int sizeH,
			int textureRegionWidth, int textureRegionHeight, int line, int row, int speed) {
		super(path, sizeW, sizeH, textureRegionWidth, textureRegionHeight);
		this.line = line;
		this.row = row;
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}
	
	public int get_line(){
		return this.line;
	}
	
	public int get_row(){
		return this.row;
	}
	public int getSpeed(){
		return this.speed;
	}
}
