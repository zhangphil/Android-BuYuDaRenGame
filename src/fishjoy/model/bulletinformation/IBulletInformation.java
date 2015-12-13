package fishjoy.model.bulletinformation;

import fishjoy.model.IEntityInformation;
import fishjoy.model.netinformation.INetInformation;

public abstract class IBulletInformation extends IEntityInformation {
	
	private INetInformation iNetInfor;
	private int minHeight, maxHeight,  minWeight;
	
	public IBulletInformation(String _path,int _size_w,int _size_h,int _TextureRegion_width,int _TextureRegion_height, 
			INetInformation iNetInfor,int minHeight,int maxHeight, int minWeight ) {
		super(_path,_size_w,_size_h,_TextureRegion_width,_TextureRegion_height);
		this.iNetInfor = iNetInfor;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.minWeight = minWeight;
		// TODO Auto-generated constructor stub
	}
	
	public INetInformation getNetInformation(){
		return this.iNetInfor;
	}
	
	public int getMinHeight(){
		return this.minHeight;
	}
	
	public int getMaxHeight(){
		return this.maxHeight;
	}
	
	public int getMinWeight(){
		return this.minWeight;
	}
}
