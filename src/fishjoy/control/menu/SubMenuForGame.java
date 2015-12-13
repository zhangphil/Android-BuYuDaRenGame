package fishjoy.control.menu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import fishjoy.control.game.FishJoy;
import fishjoy.control.help.Help;
import fishjoy.control.help.ImageAdapter;
import fishjoy.control.systemsetting.Systemsetting;


public class SubMenuForGame extends Activity {

	private int musicVolume;
	private int soundVolume;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submenu);

		Intent intent=getIntent(); 
		this.musicVolume=intent.getIntExtra("musicVolume", 0); 
        this.soundVolume=intent.getIntExtra("soundVolume", 0); 
		
		ImageButton returnbutton = (ImageButton) findViewById(R.id.back);
		returnbutton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", SubMenuForGame.this.musicVolume);
				intent.putExtra("soundVolume", SubMenuForGame.this.soundVolume);
				intent.setClass(SubMenuForGame.this, MainMenu.class);
				startActivity(intent);
				SubMenuForGame.this.finish();
			}
		});
		
		ImageButton easyButton = (ImageButton) findViewById(R.id.easy);
		easyButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", SubMenuForGame.this.musicVolume);
				intent.putExtra("soundVolume", SubMenuForGame.this.soundVolume);
				intent.putExtra("difficulty", 1);
				intent.setClass(SubMenuForGame.this, FishJoy.class);
				startActivity(intent);
				SubMenuForGame.this.finish();
			}
		});
		
		ImageButton normalButton = (ImageButton) findViewById(R.id.normal);
		normalButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", SubMenuForGame.this.musicVolume);
				intent.putExtra("soundVolume", SubMenuForGame.this.soundVolume);
				intent.putExtra("difficulty", 2);
				intent.setClass(SubMenuForGame.this, FishJoy.class);
				startActivity(intent);
				SubMenuForGame.this.finish();
			}
		});
		
		ImageButton hardButton = (ImageButton) findViewById(R.id.hard);
		hardButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", SubMenuForGame.this.musicVolume);
				intent.putExtra("soundVolume", SubMenuForGame.this.soundVolume);
				intent.putExtra("difficulty", 3);
				intent.setClass(SubMenuForGame.this, FishJoy.class);
				startActivity(intent);
				SubMenuForGame.this.finish();
			}
		});
		
	}
}
