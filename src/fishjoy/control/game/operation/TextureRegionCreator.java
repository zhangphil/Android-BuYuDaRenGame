package fishjoy.control.game.operation;

import java.util.HashMap;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.StrokeFont;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.graphics.Typeface;

import fishjoy.control.GameEnum.Artillery_Rank;
import fishjoy.control.GameEnum.Artillery_Operate;
import fishjoy.control.GameEnum.Fish_Name;

public class TextureRegionCreator {

	private static TextureRegionCreator uniqueInstance;
	
	private TextureRegionCreator(){}
	
	public static TextureRegionCreator getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new TextureRegionCreator();
		}
		return uniqueInstance;
	}

	private TiledTextureRegion creatMovingFishTextureRegion(Fish_Name name, BaseGameActivity activity){
		TiledTextureRegion fishTextureRegion = this.createTiledTextureRegion(
				ModelInformationController.getInstance().getFishInformation(name).get_TextureRegion_width(),
				ModelInformationController.getInstance().getFishInformation(name).get_TextureRegion_height(),
				ModelInformationController.getInstance().getFishInformation(name).get_Path(),
				ModelInformationController.getInstance().getFishInformation(name).get_line(),
				ModelInformationController.getInstance().getFishInformation(name).get_row(), 
				activity);
		
		return fishTextureRegion;
	}

	public void creatAllMovingFishTextureRegion(
			HashMap<Fish_Name, TiledTextureRegion> allMovingFishTextureRegionMap,
			BaseGameActivity activity) {
        
		allMovingFishTextureRegionMap.put(Fish_Name.CLOWNFISH, this.creatMovingFishTextureRegion(Fish_Name.CLOWNFISH, activity));
		allMovingFishTextureRegionMap.put(Fish_Name.PUFFERFISH, this.creatMovingFishTextureRegion(Fish_Name.PUFFERFISH, activity));
		allMovingFishTextureRegionMap.put(Fish_Name.SARDINE, this.creatMovingFishTextureRegion(Fish_Name.SARDINE, activity));
		allMovingFishTextureRegionMap.put(Fish_Name.SHARK, this.creatMovingFishTextureRegion(Fish_Name.SHARK, activity));
		allMovingFishTextureRegionMap.put(Fish_Name.TORTOISE, this.creatMovingFishTextureRegion(Fish_Name.TORTOISE, activity));
		
	}

	private TiledTextureRegion creatBulletTextureRegion(Artillery_Rank rank, BaseGameActivity activity) {
		TiledTextureRegion  bulletTextureRegion = this.createTiledTextureRegion(
				ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().get_TextureRegion_width(),
				ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().get_TextureRegion_height(),
				ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().get_Path(),
				1, 1, activity);
		
		return bulletTextureRegion;
	}

	public void creatAllBulletTextureRegion(
			HashMap<Artillery_Rank, TiledTextureRegion> allBulletTextureRegionMap,
			BaseGameActivity activity) {
		allBulletTextureRegionMap.put(Artillery_Rank.RANK1,this.creatBulletTextureRegion(Artillery_Rank.RANK1, activity));
		allBulletTextureRegionMap.put(Artillery_Rank.RANK2,this.creatBulletTextureRegion(Artillery_Rank.RANK2, activity));
		allBulletTextureRegionMap.put(Artillery_Rank.RANK3,this.creatBulletTextureRegion(Artillery_Rank.RANK3, activity));
		allBulletTextureRegionMap.put(Artillery_Rank.RANK4,this.creatBulletTextureRegion(Artillery_Rank.RANK4, activity));
		allBulletTextureRegionMap.put(Artillery_Rank.RANK5,this.creatBulletTextureRegion(Artillery_Rank.RANK5, activity));
	}

	public void createAllButtonTextureRegion(
			HashMap<Artillery_Operate, TiledTextureRegion> allButtonTextureRegionMap,
			BaseGameActivity activity) {
		Texture buttonTexture = new Texture(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		allButtonTextureRegionMap.put(Artillery_Operate.WEAKEN, TextureRegionFactory.createTiledFromAsset(buttonTexture, activity, "button_reduce.png", 0, 0, 2, 1));
		allButtonTextureRegionMap.put(Artillery_Operate.STRENGTHEN, TextureRegionFactory.createTiledFromAsset(buttonTexture, activity, "button_add.png", 100, 0, 2, 1));
		activity.getEngine().getTextureManager().loadTexture(buttonTexture);
		
	}

	public TiledTextureRegion createArtilleryTextureRegion(BaseGameActivity activity){
		TiledTextureRegion artilleryTextureRegion = this.createTiledTextureRegion(
				512, 128, "pao5.png", 5, 1, activity);
		return artilleryTextureRegion;
	}

	public void createAllNetTextureRegion(
			HashMap<Artillery_Rank, TiledTextureRegion> allNetTextureRegionMap,
			BaseGameActivity activity) {
		allNetTextureRegionMap.put(Artillery_Rank.RANK1,this.creatNetTextureRegion(Artillery_Rank.RANK1, activity));
		allNetTextureRegionMap.put(Artillery_Rank.RANK2,this.creatNetTextureRegion(Artillery_Rank.RANK2, activity));
		allNetTextureRegionMap.put(Artillery_Rank.RANK3,this.creatNetTextureRegion(Artillery_Rank.RANK3, activity));
		allNetTextureRegionMap.put(Artillery_Rank.RANK4,this.creatNetTextureRegion(Artillery_Rank.RANK4, activity));
		allNetTextureRegionMap.put(Artillery_Rank.RANK5,this.creatNetTextureRegion(Artillery_Rank.RANK5, activity));
		
	}

    private TiledTextureRegion creatNetTextureRegion(Artillery_Rank rank, BaseGameActivity activity) {
    	TiledTextureRegion netTextureRegion = this.createTiledTextureRegion(
    			ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().getNetInformation().get_TextureRegion_width(),
    			ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().getNetInformation().get_TextureRegion_height(),
    			ModelInformationController.getInstance().getArtilleryInformation(rank).getBulletInformation().getNetInformation().get_Path(),
 
    			1, 5, activity);
		
		return netTextureRegion;
    }

	public void creatAllCapturedFishTextureRegion(
			HashMap<Fish_Name, TiledTextureRegion> allCapturedFishTextureRegionMap,
			BaseGameActivity activity) {
		allCapturedFishTextureRegionMap.put(Fish_Name.CLOWNFISH, this.creatCapturedFishTextureRegion(Fish_Name.CLOWNFISH, activity));
		allCapturedFishTextureRegionMap.put(Fish_Name.PUFFERFISH, this.creatCapturedFishTextureRegion(Fish_Name.PUFFERFISH, activity));
		allCapturedFishTextureRegionMap.put(Fish_Name.SARDINE, this.creatCapturedFishTextureRegion(Fish_Name.SARDINE, activity));
		allCapturedFishTextureRegionMap.put(Fish_Name.SHARK, this.creatCapturedFishTextureRegion(Fish_Name.SHARK, activity));
		allCapturedFishTextureRegionMap.put(Fish_Name.TORTOISE, this.creatCapturedFishTextureRegion(Fish_Name.TORTOISE, activity));

		
	}

	private TiledTextureRegion creatCapturedFishTextureRegion(Fish_Name name, BaseGameActivity activity) {
		TiledTextureRegion fishTextureRegion =
			this.createTiledTextureRegion(
					ModelInformationController.getInstance().getFishInformation(name).getCaptureFishInformation().get_TextureRegion_width(), 
					ModelInformationController.getInstance().getFishInformation(name).getCaptureFishInformation().get_TextureRegion_height(), 
					ModelInformationController.getInstance().getFishInformation(name).getCaptureFishInformation().get_Path(),
					ModelInformationController.getInstance().getFishInformation(name).getCaptureFishInformation().get_line(), 
					ModelInformationController.getInstance().getFishInformation(name).getCaptureFishInformation().get_row(),

					activity);
	
		return fishTextureRegion;
	}

	public void creatAllScoreTextureRegion(
			HashMap<Fish_Name, TiledTextureRegion> allScoreTextureRegionMap,
			BaseGameActivity activity) {
		allScoreTextureRegionMap.put(Fish_Name.CLOWNFISH, this.creatScoreTextureRegion(Fish_Name.CLOWNFISH, activity));
		allScoreTextureRegionMap.put(Fish_Name.PUFFERFISH, this.creatScoreTextureRegion(Fish_Name.PUFFERFISH, activity));
		allScoreTextureRegionMap.put(Fish_Name.SARDINE, this.creatScoreTextureRegion(Fish_Name.SARDINE, activity));
		allScoreTextureRegionMap.put(Fish_Name.SHARK, this.creatScoreTextureRegion(Fish_Name.SHARK, activity));
		allScoreTextureRegionMap.put(Fish_Name.TORTOISE, this.creatScoreTextureRegion(Fish_Name.TORTOISE, activity));
		
	}

	private TiledTextureRegion creatScoreTextureRegion(Fish_Name name,BaseGameActivity activity) {

		TiledTextureRegion scoreTextureRegion =
			this.createTiledTextureRegion(
					ModelInformationController.getInstance().getFishInformation(name).getScoreInformation().get_TextureRegion_width(),
					ModelInformationController.getInstance().getFishInformation(name).getScoreInformation().get_TextureRegion_height(), 
					ModelInformationController.getInstance().getFishInformation(name).getScoreInformation().get_Path(), 
					ModelInformationController.getInstance().getFishInformation(name).getScoreInformation().get_line(),
					ModelInformationController.getInstance().getFishInformation(name).getScoreInformation().get_row(),
					activity);
	
		return scoreTextureRegion;
	}
	
	public Font creatFont(BaseGameActivity activity,  float pSize, int pColor){
		Texture fontTexture = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Font font = new Font(fontTexture, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), pSize, true, pColor);
		activity.getEngine().getTextureManager().loadTexture(fontTexture);
		activity.getEngine().getFontManager().loadFonts(font);
		return font;
	}
	
	public StrokeFont createStrokeFont(BaseGameActivity activity, float pSize, int pColor, float pStrokeWidth, int pStrokeColor){
		Texture fontTexture = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		StrokeFont strokeFont = new StrokeFont(fontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), pSize, true, pColor, pStrokeWidth,  pStrokeColor);
		activity.getEngine().getTextureManager().loadTexture(fontTexture);
		activity.getEngine().getFontManager().loadFonts(strokeFont);
		return strokeFont;
	}
	
	public TextureRegion createTextureRegion(int pWidth, int pHeight, TextureOptions textureOptions, String path, BaseGameActivity activity ){
		Texture texture = new Texture(pWidth, pHeight, textureOptions/*TextureOptions.BILINEAR_PREMULTIPLYALPHA*/);
		TextureRegion textureRegion = TextureRegionFactory.createFromAsset(texture, activity, path, 0, 0);
		activity.getEngine().getTextureManager().loadTexture(texture);
		return textureRegion;
	}
	
	public TiledTextureRegion  createTiledTextureRegion(int pWidth, int pHeight, String path,int pTileColumns, int pTileRows, BaseGameActivity activity ){
		Texture texture = new Texture(pWidth, pHeight, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TiledTextureRegion  tiledTextureRegion = TextureRegionFactory.createTiledFromAsset(texture, activity, path, 0, 0, pTileColumns,pTileRows);
		activity.getEngine().getTextureManager().loadTexture(texture);
		return tiledTextureRegion;
	}

	public void createAllNumberTextureRegion(
			HashMap<Integer, TextureRegion> allNumberTextureRegionMap,BaseGameActivity activity) {
		for(int i = 0; i < 10; i++ )
			allNumberTextureRegionMap.put(i,
					this.createTextureRegion(ModelInformationController.getInstance().getNumberInformation(i).get_TextureRegion_width(),
							ModelInformationController.getInstance().getNumberInformation(i).get_TextureRegion_height(), 
							TextureOptions.BILINEAR_PREMULTIPLYALPHA,
							ModelInformationController.getInstance().getNumberInformation(i).get_Path(), 
					activity));
	}

	public TextureRegion createBackgroundTextureRegion(BaseGameActivity activity, int mDifficulty) {
		String path;
//        switch(mDifficulty){
//        case 1 : path = "background_easy.png"; break;
//        case 2 : path = "background_common.png"; break;
//        case 3 : path = "background_hard.png"; break;
//        default : path = "background_easy.png";
//        }
//        
//		Texture texture = new Texture(1024, 512, TextureOptions.DEFAULT);
//		TextureRegion  backgroundTextureRegion = TextureRegionFactory.createFromAsset(texture, activity, path, 0, 0);
//		activity.getEngine().getTextureManager().loadTexture(texture);
		
		TextureRegion  backgroundTextureRegion = 
			this.createTextureRegion(
					ModelInformationController.getInstance().getGameInformation(mDifficulty).get_TextureRegion_width(),
					ModelInformationController.getInstance().getGameInformation(mDifficulty).get_TextureRegion_height(),
					TextureOptions.DEFAULT, 
					ModelInformationController.getInstance().getGameInformation(mDifficulty).get_Path(),
					activity);

		return backgroundTextureRegion;
	}
}
