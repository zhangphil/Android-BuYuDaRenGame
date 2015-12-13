package fishjoy.control;

import java.io.IOException;
import java.util.ArrayList;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

public class GameSound {
	private Sound CoinFly;
	private Sound NetOpen;
	private Sound SwitchSilo;
	private ArrayList<Sound> FishDying = new ArrayList<Sound>();
	private ArrayList<Sound> Silo = new ArrayList<Sound>();

	public void create(BaseGameActivity activity)
	{
		SoundFactory.setAssetBasePath("mfx/");
		try {		
			this.CoinFly = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "CoinFly.ogg");
			this.NetOpen = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "NetOpen.ogg");
			this.SwitchSilo = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "SwitchSilo.ogg");
			this.FishDying.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "FishDying1.ogg"));
			this.FishDying.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "FishDying2.ogg"));
			this.FishDying.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "FishDying3.ogg"));
			this.FishDying.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "FishDying4.ogg"));
			this.FishDying.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "FishDying5.ogg"));

			this.Silo.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Silo1.ogg"));
			this.Silo.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Silo2.ogg"));
			this.Silo.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Silo3.ogg"));
			this.Silo.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Silo4.ogg"));
			this.Silo.add(SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "Silo5.ogg"));
		} catch (final IOException e) {
			Debug.e(e);
		}
	}
	
	public void switchSilo()
	{
		this.SwitchSilo.play();
	}
	
	public void coinFly()
	{
		this.CoinFly.play();
	}
	
	public void netOpen()
	{
		this.NetOpen.play();
	}
	
	public void fishDying(int index)
	{
		this.FishDying.get(index).play();
	}
	
	public void lauch(int index)
	{
		this.Silo.get(index).play();
	}

	public void setVolume(float volume)
	{
		this.CoinFly.setVolume(volume);
		this.NetOpen.setVolume(volume);
		this.SwitchSilo.setVolume(volume);
		for (int i=0; i<5; i++)
		{
			this.FishDying.get(i).setVolume(volume);
			this.Silo.get(i).setVolume(volume);
		}
	}

}
