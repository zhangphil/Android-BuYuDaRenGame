package fishjoy.control.game.operation.fishoperation;

import java.util.Random;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;

import android.util.Log;

import fishjoy.control.GameEnum.Edge_Position;
import fishjoy.control.GameEnum.Fish_Move;
import fishjoy.control.GameEnum.Fish_Name;
import fishjoy.control.GameEnum.Move_Direction;
import fishjoy.control.game.GameConstants;
import fishjoy.control.game.operation.ModelInformationController;
import fishjoy.control.game.operation.TextureRegionCreator;

public class FishOperation implements GameConstants {
final Random random = new Random();	
FishController controller;
	

	public void set_Controller(FishController _controller)
	{
		controller=_controller;
	}
	public void set_side(Move_Direction _diretion)
	{
		controller.direction=_diretion;
		if(controller.direction.equals(Move_Direction.RANDOM))
		{
			controller.way=Math.abs(random.nextInt())%2;
			if(controller.way==1)
			{
//				controller.X=-1*ResourcesOperation.getInstance().getFishInformation(controller.name).get_TextureRegion_width();
				controller.X=-1*ModelInformationController.getInstance().getFishInformation(controller.name).get_TextureRegion_width();
			}
			else
			{
				controller.X=CAMERA_WIDTH;
			}
		}
		else
		{
			switch(controller.direction)
			{
			case LEFT:
			{
				controller.way=1;
//				controller.X=-1*ResourcesOperation.getInstance().getFishInformation(controller.name).get_TextureRegion_width();
				controller.X=-1*ModelInformationController.getInstance().getFishInformation(controller.name).get_TextureRegion_width();
				break;
			}
			case RIGHT:
			{
				controller.way=0;
				controller.X=CAMERA_WIDTH;
				break;
			}
			}
		}
	}
	
	public void set_edge_position(Edge_Position _position)
	{
		controller.position=_position;
		if(controller.position.equals(Edge_Position.RANDOM))
		{		
			controller.Y=Math.abs(random.nextInt())%3*60+70;
		}
		else
		{
			switch(controller.position)
			{
			case UP:
			{		
				controller.Y=70;
				break;
			}
			case MIDDLE:
			{		
				controller.Y=130;
				break;
			}
			case DOWN:
			{			
				controller.Y=190;
				break;
			}
			}
		}
	}
	
	public void set_fixed_Y(int _position)
	{
		controller.Y=_position;
	}
	
	public void set_fixed_X(int _position)
	{
		controller.X=_position;
	}
	
	private void Direct_initial(int currentRotation)
	{	
//		float speed=ResourcesOperation.getInstance().getFishInformation(controller.name).get_speed();
		float speed=ModelInformationController.getInstance().getFishInformation(controller.name).get_speed();

		switch(controller.way)
		{
		case 0:
		{			
			controller.setPosition(controller.X, controller.Y);	
			controller.current_X=controller.X;
			controller.current_Y=controller.Y;
			controller.current_rotation=currentRotation;
			controller.setRotation(controller.current_rotation);
			controller.mPhysicsHandler.setVelocity(-1*speed*(float)Math.cos(controller.current_rotation*3.14/180),
					-1*speed*(float)Math.sin(controller.current_rotation*3.14/180));		
			break;
		}
		case 1:
		{
			controller.setPosition(controller.X, controller.Y);
			controller.current_X=controller.X;
			controller.current_Y=controller.Y;
			controller.current_rotation=currentRotation+160;
			controller.setRotation(controller.current_rotation);
			controller.mPhysicsHandler.setVelocity(-1*speed*(float)Math.cos(controller.current_rotation*3.14/180),
					-1*speed*(float)Math.sin(controller.current_rotation*3.14/180));	
			break;
		}
		};
	}
	
	private void Curve_initial(int A)
	{
		switch(controller.way)
		{
		case 0:
		{
			controller.setPosition(controller.X, controller.Y);	
			controller.current_X=controller.X;
			controller.current_Y=controller.Y;
//			controller.mPhysicsHandler.setVelocity(-1*ResourcesOperation.getInstance().getFishInformation(controller.name).get_speed(),0);
			controller.mPhysicsHandler.setVelocity(-1*ModelInformationController.getInstance().getFishInformation(controller.name).get_speed(),0);
			controller.mPhysicsHandler.setAcceleration(0, A);			
			break;
		}
		case 1:
		{
			controller.setPosition(controller.X, controller.Y);
			controller.current_X=controller.X;
			controller.current_Y=controller.Y;
//			controller.mPhysicsHandler.setVelocity(ResourcesOperation.getInstance().getFishInformation(controller.name).get_speed(),0);
			controller.mPhysicsHandler.setVelocity(ModelInformationController.getInstance().getFishInformation(controller.name).get_speed(),0);
			controller.mPhysicsHandler.setAcceleration(0, A);
			break;
		}
		};
	}
	
	private void Circle_initial()
	{
		switch(controller.way)
		{
		case 0:
		{
			controller.setPosition(controller.X, controller.Y);		
			controller.current_X=controller.X;
			controller.current_Y=controller.Y;
			controller.mPhysicsHandler.setVelocity(-40,0);
			controller.mPhysicsHandler.setAngularVelocity(0);
			controller.setRotation(0);
			
			break;
		}
		case 1:
		{
			controller.setPosition(controller.X, controller.Y);
			controller.current_X=controller.X;
			controller.current_Y=controller.Y;
			controller.mPhysicsHandler.setVelocity(40, 0);
			controller.mPhysicsHandler.setAngularVelocity(0);
			controller.setRotation(180);
		
			break;
		}
		};
	}
	
	public void set_Direct_Move(int rotation)
	{
		controller.move=Fish_Move.Direct;
		Direct_initial(rotation);
	}
	
	public void set_Curve_Move(int acceleration)
	{
		controller.move=Fish_Move.Curve;
		Curve_initial(acceleration);
	}
	
	public void set_Circle_Move()
	{
		controller.move=Fish_Move.Circle;
		Circle_initial();
	}

	public void set_Move(Fish_Move move)
	{
		if(move.equals(Fish_Move.Random))
		{
			int i=Math.abs(random.nextInt())%2;
			switch(i)
			{
			case 0:
			{
				move=Fish_Move.Direct;
				break;
			}
			case 1:
			{
				move=Fish_Move.Curve;
				break;
			}
			}
		}
		switch(move)
		{
		case Direct:
		{
			int rotation=Math.abs(random.nextInt())%30+1;
			set_Direct_Move(rotation);
			break;
		}
		case Curve:
		{
			int a=-1*(Math.abs(random.nextInt())%25+1);
			set_Curve_Move(a);
			break;
		}
		case Circle:
		{
			set_Circle_Move();
			break;
		}
		}
	}
	
	public boolean isOutOfBound()
	{
		if(controller.getX()>=CAMERA_WIDTH || 
//				controller.getX()<-1*ResourcesOperation.getInstance().getFishInformation(controller.name).get_TextureRegion_width()
//				|| controller.getY()>=CAMERA_WIDTH|| 
//				controller.getY()<-1*ResourcesOperation.getInstance().getFishInformation(controller.name).get_TextureRegion_width())
				controller.getX()<-1*ModelInformationController.getInstance().getFishInformation(controller.name).get_TextureRegion_width()
				|| controller.getY()>=CAMERA_WIDTH|| 
				controller.getY()<-1*ModelInformationController.getInstance().getFishInformation(controller.name).get_TextureRegion_width())

		{
			return true;
		}
		else 
			return false;
	}
	
	public void fishOnManagedUpdate() {
		float x=controller.getX();
		float y=controller.getY();
		float rotation=controller.getRotation();
		
		if(controller.move.equals(Fish_Move.Direct))
		{
			if(isOutOfBound())
			{
				
			}
		}
		else if(controller.move.equals(Fish_Move.Curve))
		{
			if(!isOutOfBound())
			{
				switch(controller.way)
				{
				case 0:
				{
					rotation=(float) ((Math.atan((y-controller.current_Y)/(x-controller.current_X)))*180/3.14);
					controller.setRotation(rotation);	
					break;
				}
				case 1:
				{
					rotation=(float) (180+(Math.atan((y-controller.current_Y)/(x-controller.current_X)))*180/3.14);
					controller.setRotation(rotation);	
					break;
				}
				};
			}
		}
		else if(controller.move.equals(Fish_Move.Circle))
		{
			switch(controller.way)
			{
			case 0:
			{
				if(rotation>=360)
				{
					controller.mPhysicsHandler.setAngularVelocity(0);
					controller.mPhysicsHandler.setVelocity(-40, 0);
					controller.setRotation(360);								
				}
				else if(x<(CAMERA_WIDTH/2) || (x>(CAMERA_WIDTH/2) && y<controller.Y))
				{
					controller.mPhysicsHandler.setAngularVelocity(45);
					controller.mPhysicsHandler.setVelocity(-1*(float)Math.cos(rotation*3.14/180)*40, -1*(float)Math.sin(rotation*3.14/180)*40);
				}
				
				break;
			}
			case 1:
			{
				if(rotation<=-180)
				{
					controller.mPhysicsHandler.setAngularVelocity(0);
					controller.mPhysicsHandler.setVelocity(40, 0);
					controller.setRotation(-180);						
				}
				else if(x>(CAMERA_WIDTH/2) || (x<(CAMERA_WIDTH/2) && y<controller.Y))
				{
					controller.mPhysicsHandler.setAngularVelocity(-45);
					controller.mPhysicsHandler.setVelocity((float)Math.cos((180-rotation)*3.14/180)*40, -1*(float)Math.sin((180-rotation)*3.14/180)*40);
				}
				
				break;
			}
		};

		}
		controller.current_rotation=rotation;
		controller.current_X=x;
		controller.current_Y=y;
	}
}
