package fishjoy.control.systemsetting;

import fishjoy.control.menu.MainMenu;
import fishjoy.control.menu.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class Systemsetting extends Activity
{
	//声明SeekBar对象
    SeekBar		mMusic;
    SeekBar		mSound;
	TextView	mMusicText;
	TextView	mSoundText;
	private int musicVolume = 0;
	private int soundVolume = 0;


	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Intent intent=getIntent(); 
	    this.musicVolume=intent.getIntExtra("musicVolume", 0); 
	    this.soundVolume=intent.getIntExtra("soundVolume", 0); 
	    
		setContentView(R.layout.set);
		//取得SeekBar对象
		mMusicText = (TextView) findViewById(R.id.music_value);
		mSoundText = (TextView) findViewById(R.id.sound_value);
		mMusic = (SeekBar) findViewById(R.id.music);
		mMusic.setProgress(this.musicVolume);
		mMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekbar, int progress, boolean fromTouch) {
				Systemsetting.this.mMusicText.setText(Integer.toString(progress));	
				Systemsetting.this.musicVolume = progress;
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {			
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {			
			}});
		mSound = (SeekBar) findViewById(R.id.sound);
		mSound.setProgress(this.soundVolume);
		mSound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekbar, int progress, boolean fromTouch) {
				Systemsetting.this.mSoundText.setText(Integer.toString(progress));	
				Systemsetting.this.soundVolume = progress;
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {		
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {			
			}});
		
			
		
		ImageButton returnbutton = (ImageButton) findViewById(R.id.set_back);
		returnbutton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", Systemsetting.this.musicVolume);
				intent.putExtra("soundVolume", Systemsetting.this.soundVolume);
				intent.setClass(Systemsetting.this, MainMenu.class);
				startActivity(intent);
				Systemsetting.this.finish();
			}
		});
	}
}
