package fishjoy.control.game.timer;

import java.text.DecimalFormat;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import fishjoy.control.game.GameConstants;
import fishjoy.control.game.operation.sceneoperation.SceneConverter;

public class Timer implements GameConstants {
////	private static float mTime;
//	//private static ChangeableText mTimeText;
//	
//	private static Timer uniqueInstance;
//	
//	private Timer(){}
//	
//	public static Timer getInstance(){
//		if(uniqueInstance == null)
//			uniqueInstance = new Timer();
//		return uniqueInstance;
//	}
//	
//	public static void initial(BaseGameActivity activity , Font mTimeFont, ChangeableText mTimeText){
//
//		mTimeText = new ChangeableText((CAMERA_WIDTH - "剩余时间: XX:XX:XX".length()) * 0.5f - 155, 10, 
//				mTimeFont, "剩余时间:", "剩余时间: XX:XX:XX".length());
//		
//		activity.getEngine().getScene().getChild(LAYER_STATE).attachChild(mTimeText);
//	}
////	
	public static String setTimeFormat(float time)
	{
		DecimalFormat df = new DecimalFormat("00"); 
		String text = "时间：" + df.format((int)time/60) + 
				":" + df.format((int)time%60) + 
				":" + df.format((time - (int)time)*100);
		return text;
	}


}
