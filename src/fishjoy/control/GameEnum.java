package fishjoy.control;

public interface GameEnum {
	public enum Fish_Move{
		Direct,Curve, Circle,Random
		};
		
	public enum Fish_State{
		moving,captured,finished
		};
		
	public enum Fish_Name{
		SARDINE, CLOWNFISH, PUFFERFISH,TORTOISE,SHARK
	}
	
	public enum Artillery_Rank{
		RANK1, RANK2, RANK3, RANK4, RANK5, 
	}
	
	public enum Artillery_Operate{
		STRENGTHEN, WEAKEN,
	}

	public enum Move_Direction{
		RANDOM,LEFT,RIGHT
	}
	
	public enum Edge_Position{
		RANDOM,UP,MIDDLE,DOWN
	}
	
	public enum Game_Rank{
		EASY, COMMON, HARD
	}
	
	public enum Fish_Group_Way{
		Curve_Group, Circle_Group, Random_Single
	}
}
