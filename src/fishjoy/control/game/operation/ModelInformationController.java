package fishjoy.control.game.operation;

import fishjoy.control.GameEnum.Artillery_Rank;
import fishjoy.control.GameEnum.Fish_Name;
import fishjoy.model.artilleryinformation.*;
import fishjoy.model.fishinformation.*;
import fishjoy.model.gameinformation.*;
import fishjoy.model.numberinformation.*;

public class ModelInformationController {
	private static ModelInformationController uniqueInstance;
	
	private ModelInformationController(){}
	
	public static ModelInformationController getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new ModelInformationController();
		}
		return uniqueInstance;
	}

	public IFishInformation getFishInformation(Fish_Name name){
		if(name.equals(Fish_Name.CLOWNFISH))
			return new ClownFishInformation();
		else if(name.equals(Fish_Name.PUFFERFISH))
			return new PufferFishInformation() ;
		else if(name.equals(Fish_Name.SARDINE))
			return new SardineInformation() ;
		else if(name.equals(Fish_Name.SHARK))
			return new SharkInformation() ;
		else if(name.equals(Fish_Name.TORTOISE))
			return new TortoiseInformation() ;
		
		return null;
	}

	public IArtilleryInformation getArtilleryInformation(Artillery_Rank rank) {
		if(rank.equals(Artillery_Rank.RANK1))
			return new Artillery1Information();
		else if(rank.equals(Artillery_Rank.RANK2))
			return new Artillery2Information() ;
		else if(rank.equals(Artillery_Rank.RANK3))
			return new Artillery3Information() ;
		else if(rank.equals(Artillery_Rank.RANK4))
			return new Artillery4Information() ;
		else if(rank.equals(Artillery_Rank.RANK5))
			return new Artillery5Information() ;
		
		return null;
	}
	
	public INumberInformation getNumberInformation(int i){
		if(i == 0)
			return new Number0Information();
		else if(i == 1)
			return new Number1Information();
		else if(i == 2)
			return new Number2Information();
		else if(i == 3)
			return new Number3Information();
		else if(i == 4)
			return new Number4Information();
		else if(i == 5)
			return new Number5Information();
		else if(i == 6)
			return new Number6Information();
		else if(i == 7)
			return new Number7Information();
		else if(i == 8)
			return new Number8Information();
		else if(i == 9)
			return new Number9Information();
		
		return null;
	}
	
	public IGameInformation getGameInformation(int difficulty){
		if(difficulty == 1)
			return new Game1Information();
		else if(difficulty == 2)
			return new Game2Information();
		else if(difficulty == 3)
			return new Game3Information();
		else
			return new Game1Information();
	}
}
