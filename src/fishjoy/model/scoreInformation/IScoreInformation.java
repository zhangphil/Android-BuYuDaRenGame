package fishjoy.model.scoreInformation;

import fishjoy.model.IEntityInformation;

public class IScoreInformation extends IEntityInformation {

	private int line, row;
	
	public IScoreInformation(String path, int sizeW, int sizeH,
			int textureRegionWidth, int textureRegionHeight, int _line,int _row) {
		super(path, sizeW, sizeH, textureRegionWidth, textureRegionHeight);
		this.line = _line;
		this.row = _row;
		// TODO Auto-generated constructor stub
	}
	
	public int get_line()
	{
		return line;
	}
	
	public int get_row()
	{
		return row;
	}
	

}
