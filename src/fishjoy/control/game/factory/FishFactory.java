package fishjoy.control.game.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import fishjoy.control.game.GameConstants;
import fishjoy.control.game.operation.ModelInformationController;
import fishjoy.control.game.operation.fishoperation.FishController;
import fishjoy.control.game.operation.fishoperation.FishOperation;
import fishjoy.control.record.Matrix;

public class FishFactory implements GameConstants {
	final Random random = new Random();	
	private Matrix matrix=new Matrix();
	private static FishFactory uniqueInstance;
	private FishFactory()
	{
	}
	
	public static FishFactory getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new FishFactory();
		}
		return uniqueInstance;
	}
	
	public FishController createSingleFish(TiledTextureRegion fishTextureRegion, Fish_Name name, Scene scene, Fish_Move fishMove){
		FishController fish = new FishController(name, fishTextureRegion);
		FishOperation fishOperation=new FishOperation();
		fishOperation.set_Controller(fish);
		fish.set_Fish_Operation(fishOperation);
		fishOperation.set_edge_position(Edge_Position.RANDOM);
		fishOperation.set_side(Move_Direction.RANDOM);
		fishOperation.set_Move(fishMove);
		fish.animate(100);
		fish.setSize(ModelInformationController.getInstance().getFishInformation(name).get_size_w(), 
				ModelInformationController.getInstance().getFishInformation(name).get_size_h());
	
		scene.getChild(LAYER_FISH).attachChild(fish);
		
		return fish;
	}
	
	public void createFish(ArrayList<FishController> movingFishList,
			HashMap<Fish_Name, TiledTextureRegion> allFishTextureRegionMap, Scene scene){
		int randomListSize = (int) (Math.random()*FISHLIST_MAX);
		if(movingFishList.size() < randomListSize){
			for(int i = 0; i < randomListSize - movingFishList.size(); i++){
				Fish_Name fishName = this.getFishName();
				if(fishName == Fish_Name.SARDINE || fishName == Fish_Name.CLOWNFISH){
					Fish_Group_Way groupWay = this.getFishGroupWay();
					if(groupWay == Fish_Group_Way.Circle_Group)
						this.creat_Circle_Group(fishName, movingFishList, allFishTextureRegionMap.get(fishName), scene);
					else if(groupWay == Fish_Group_Way.Curve_Group)
						this.creat_Curve_Group(fishName, movingFishList, allFishTextureRegionMap.get(fishName), scene);
					else
						movingFishList.add(this.createSingleFish(allFishTextureRegionMap.get(fishName), fishName, scene, this.getFishMove()));	
				}else if(fishName == Fish_Name.PUFFERFISH || fishName == Fish_Name.TORTOISE){
					movingFishList.add(this.createSingleFish(allFishTextureRegionMap.get(fishName), fishName, scene, this.getFishMove()));				
				}else
					movingFishList.add(this.createSingleFish(allFishTextureRegionMap.get(fishName), fishName, scene, Fish_Move.Direct));
			}		
		}
	}


	private Fish_Group_Way getFishGroupWay() {
	 int i = (int) (Math.random()*10);
	 if(i < 2)
		 return Fish_Group_Way.Circle_Group;
	 else if( i >=2 && i < 6)
		 return Fish_Group_Way.Curve_Group;
	 else
		 return Fish_Group_Way.Random_Single;
}

	private Fish_Name getFishName() {
		int i = (int) (Math.random()*100);
		if(i < 20)
			return Fish_Name.SARDINE;
		else if(i >=20 && i < 35)
			return Fish_Name.CLOWNFISH;
		else if(i >=35 && i < 65)
			return Fish_Name.PUFFERFISH;
		else if(i >=65 && i < 90)
			return Fish_Name.TORTOISE;
		else
			return Fish_Name.SHARK;
	}

	public void remove_fish(Scene scene,FishController fish)
	{
		scene.detachChild(fish);
	}
	
	private Fish_Move getFishMove()
	{
		int i=Math.abs(random.nextInt())%2;
		switch(i){
		case 0:{
			return Fish_Move.Direct;
		}
		case 1:{
			return Fish_Move.Curve;
		}
		}
		return Fish_Move.Direct;
	}
	
	private Move_Direction getDirection()
	{
		int i=Math.abs(random.nextInt())%2;
		switch(i){
		case 0:{
			return Move_Direction.LEFT;
		}
		case 1:{
			return Move_Direction.RIGHT;
		}
		}
		return Move_Direction.RIGHT;
	}
	
	public void creat_Circle_Group(Fish_Name fishName, ArrayList<FishController> movingFishList,
			TiledTextureRegion fishTextureRegion, Scene scene)
	{
		Move_Direction direction=this.getDirection();
		for(int i = 0; i < 3; i++){
				FishController fish = new FishController(fishName,fishTextureRegion.clone());
				FishOperation fishOperation=new FishOperation();
				fishOperation.set_Controller(fish);
				fish.set_Fish_Operation(fishOperation);
				if(i==0)
					fishOperation.set_edge_position(Edge_Position.UP);
				else if(i==1)
					fishOperation.set_edge_position(Edge_Position.MIDDLE);
				else if(i==2)
					fishOperation.set_edge_position(Edge_Position.DOWN);
				fishOperation.set_side(direction);
				fishOperation.set_Circle_Move();
				fish.animate(100);
				fish.setSize(ModelInformationController.getInstance().getFishInformation(fishName).get_size_w(), 
						ModelInformationController.getInstance().getFishInformation(fishName).get_size_h());
				
				movingFishList.add(fish);
			
				scene.getChild(LAYER_FISH).attachChild(fish);	
		}		
	}
	
	public void creat_Curve_Group(Fish_Name fishName, ArrayList<FishController> movingFishList,
			TiledTextureRegion fishTextureRegion, Scene scene)
	{
		Move_Direction direction=this.getDirection();
		int a=Math.abs(random.nextInt())%41-20;
		while(a==0)
			a=Math.abs(random.nextInt())%41-20;
		
		for(int i = 0; i < 3; i++){
				FishController fish = new FishController(fishName,fishTextureRegion.clone());
				FishOperation fishOperation=new FishOperation();
				fishOperation.set_Controller(fish);
				fish.set_Fish_Operation(fishOperation);
				if(i==0)
					fishOperation.set_edge_position(Edge_Position.UP);
				else if(i==1)
					fishOperation.set_edge_position(Edge_Position.MIDDLE);
				else if(i==2)
					fishOperation.set_edge_position(Edge_Position.DOWN);
				fishOperation.set_side(direction);
				fishOperation.set_Curve_Move(a);
				fish.animate(100);
				fish.setSize(ModelInformationController.getInstance().getFishInformation(fishName).get_size_w(), 
						ModelInformationController.getInstance().getFishInformation(fishName).get_size_h());
				
				movingFishList.add(fish);
			
				scene.getChild(LAYER_FISH).attachChild(fish);	
		}		
	}
	
	public void creat_String_Move(ArrayList<FishController> movingFishList,
			HashMap<Fish_Name, TiledTextureRegion> allFishTextureRegionMap, Scene scene,String str)
	{		
		int distance;
		distance=creat_Char_Move(movingFishList,allFishTextureRegionMap,scene,str.charAt(0),0)*38+38;	
		for(int i = 1; i < str.length(); i++){
			if(str.charAt(i)==' ')
				distance+=65;
			else
			 distance+=creat_Char_Move(movingFishList,allFishTextureRegionMap,scene,str.charAt(i),distance)*38+38;	
		}				
	}
	
	private int creat_Char_Move(ArrayList<FishController> movingFishList,
			HashMap<Fish_Name, TiledTextureRegion> allFishTextureRegionMap, Scene scene,char ch,int i)
	{
		Move_Direction direction=Move_Direction.RIGHT;		
		int[][] c=matrix.get_Matrix(String.valueOf(ch));
	
		int column=c.length;
		int row=c[0].length;
		for(int t = 0; t < column; t++){
			for(int j=0;j < row; j++)
			{
				if(c[t][j]!=0)
				{
					FishController fish = new FishController(Fish_Name.SARDINE,allFishTextureRegionMap.get(Fish_Name.SARDINE).clone());
					FishOperation fishOperation=new FishOperation();
					fishOperation.set_Controller(fish);
					fish.set_Fish_Operation(fishOperation);
					fishOperation.set_side(direction);
					fishOperation.set_fixed_Y(t*33+60);
					fishOperation.set_fixed_X(CAMERA_WIDTH+j*38+i);
					fishOperation.set_Direct_Move(0);
					fish.animate(100);

					fish.setSize(ModelInformationController.getInstance().getFishInformation(Fish_Name.SARDINE).get_size_w(), 
							ModelInformationController.getInstance().getFishInformation(Fish_Name.SARDINE).get_size_h());

					scene.getChild(LAYER_FISH).attachChild(fish);
					movingFishList.add(fish);
				}
			}
		}	
		return row;
	}

	public void createStartFishSequence(ArrayList<FishController> movingFishList,
			HashMap<Fish_Name, TiledTextureRegion> allFishTextureRegionMap,
			Scene scene, int mDifficulty) {
		if(mDifficulty == 1)
			this.creat_String_Move(movingFishList, allFishTextureRegionMap, scene, "EASY");
		else if(mDifficulty == 2)
			this.creat_String_Move(movingFishList, allFishTextureRegionMap, scene, "COMMON");
		else if(mDifficulty == 3)
			this.creat_String_Move(movingFishList, allFishTextureRegionMap, scene, "HARD");
		
	}
}
