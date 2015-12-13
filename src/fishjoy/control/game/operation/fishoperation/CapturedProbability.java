package fishjoy.control.game.operation.fishoperation;

import fishjoy.control.GameEnum.Artillery_Rank;

public class CapturedProbability {
	private static CapturedProbability uniqueInstance;
	
	private CapturedProbability(){}
	
	public static CapturedProbability getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new CapturedProbability();
		return uniqueInstance;
	}
	
	public boolean SardineCanBeCapturedByBullet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK1){
			if(i < 30)
				return true;
		}else if(rank == Artillery_Rank.RANK2){
			if(i < 40)
				return true;
		}else if(rank == Artillery_Rank.RANK3){
			if(i < 50)
				return true;
		}else if(rank == Artillery_Rank.RANK4){
			if(i < 60)
				return true;
		}else{
			if(i < 70)
				return true;
		}
		return false;
	}
	
	public boolean SardineCanBeCapturedByNet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK1){
			if(i < 10)
				return true;
		}else if(rank == Artillery_Rank.RANK2){
			if(i < 20)
				return true;
		}else if(rank == Artillery_Rank.RANK3){
			if(i < 30)
				return true;
		}else if(rank == Artillery_Rank.RANK4){
			if(i < 40)
				return true;
		}else{
			if(i < 50)
				return true;
		}
		return false;
	}
	
	public boolean ClownFishCanBeCapturedByBullet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK1){
			if(i < 10)
				return true;
		}else if(rank == Artillery_Rank.RANK2){
			if(i < 30)
				return true;
		}else if(rank == Artillery_Rank.RANK3){
			if(i < 40)
				return true;
		}else if(rank == Artillery_Rank.RANK4){
			if(i < 50)
				return true;
		}else{
			if(i < 60)
				return true;
		}
		return false;
	}
	
	public boolean ClownFishCanBeCapturedByNet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK1){
			if(i < 5)
				return true;
		}else if(rank == Artillery_Rank.RANK2){
			if(i < 10)
				return true;
		}else if(rank == Artillery_Rank.RANK3){
			if(i < 30)
				return true;
		}else if(rank == Artillery_Rank.RANK4){
			if(i < 40)
				return true;
		}else{
			if(i < 50)
				return true;
		}
		return false;
	}
	
	public boolean PufferFishCanBeCapturedByBullet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK2){
			if(i < 5)
				return true;
		}else if(rank == Artillery_Rank.RANK3){
			if(i < 30)
				return true;
		}else if(rank == Artillery_Rank.RANK4){
			if(i <40)
				return true;
		}else{
			if(i < 50)
				return true;
		}
		return false;
	}
	
	public boolean PufferFishCanBeCapturedByNet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK3){
			if(i < 10)
				return true;
		}else if(rank == Artillery_Rank.RANK4){
			if(i < 20)
				return true;
		}else if(rank == Artillery_Rank.RANK5){
			if(i < 30)
				return true;
		}
		return false;
	}
	
	public boolean TortoiseCanBeCapturedByBullet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK3){
			if(i < 5)
				return true;
		}else if(rank == Artillery_Rank.RANK4){
			if(i < 10)
				return true;
		}else if(rank == Artillery_Rank.RANK5){
			if(i < 15)
				return true;
		}
		return false;
	}
	
	public boolean TortoiseCanBeCapturedByNet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK4){
			if(i < 5)
				return true;
		}else if(rank == Artillery_Rank.RANK5){
			if(i < 10)
				return true;
		}
		
		return false;
	}
	
	public boolean SharkCanBeCapturedByBullet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK4){
			if(i < 2)
				return true;
		}else if(rank == Artillery_Rank.RANK5){
			if(i < 5)
				return true;
		}
		return false;
	}
	
	
	public boolean SharkCanBeCapturedByNet(Artillery_Rank rank){
		int i = (int) (Math.random()*100);
		
		if(rank == Artillery_Rank.RANK5){
			if(i < 3)
				return true;
		}
		return false;
	}
}
