package fishjoy.model.fishinformation;

import fishjoy.model.capturedfishinformation.ICapturedFishInformation;
import fishjoy.model.IEntityInformation;
import fishjoy.model.scoreInformation.IScoreInformation;

public abstract class IFishInformation extends IEntityInformation{

	int line,row,speed;
	private ICapturedFishInformation captureFishInformation;
	private IScoreInformation scoreInformation;
	public IFishInformation(int _speed,String _path,int _line,int _row,
			int _size_w,int _size_h,int _TextureRegion_width,int _TextureRegion_height,
			ICapturedFishInformation captureFishInformation,
			IScoreInformation scoreInformation) {
		
		super(_path,_size_w,_size_h,_TextureRegion_width,_TextureRegion_height);
		line=_line;
		row=_row;
		speed=_speed;
		this.captureFishInformation = captureFishInformation;
		this.scoreInformation = scoreInformation;
		// TODO Auto-generated constructor stub
	}
	
	public int get_speed()
	{
		return speed;
	}
	
	public int get_line()
	{
		return line;
	}
	
	public int get_row()
	{
		return row;
	}
	
	public IScoreInformation getScoreInformation(){
		return this.scoreInformation;
	}
	
	public ICapturedFishInformation getCaptureFishInformation(){
		return this.captureFishInformation;
	}

}
