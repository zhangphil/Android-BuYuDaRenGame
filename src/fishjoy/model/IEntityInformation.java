package fishjoy.model;

public abstract class IEntityInformation {
	private int size_w,size_h,TextureRegion_width,TextureRegion_height;
	private String path;
	public IEntityInformation(String _path,int _size_w,int _size_h,int _TextureRegion_width,int _TextureRegion_height)
	{
		path=_path;
		size_w=_size_w;
		size_h=_size_h;
		TextureRegion_width=_TextureRegion_width;
		TextureRegion_height=_TextureRegion_height;
	}
	
	public String get_Path()
	{
		return path;
	}
	
	public int get_size_w()
	{
		return size_w;
	}
	
	public int get_size_h()
	{
		return size_h;
	}
	
	public int get_TextureRegion_height()
	{
		return TextureRegion_height;
	}
	
	public int get_TextureRegion_width()
	{
		return TextureRegion_width;
	}
}
