package fishjoy.control.about;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import fishjoy.control.billboard.Billboard;
import fishjoy.control.menu.MainMenu;
import fishjoy.control.menu.R;

public class About extends Activity{
	private int musicVolume;
	private int soundVolume;
	

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Intent intent=getIntent(); 
	    this.musicVolume=intent.getIntExtra("musicVolume", 0); 
	    this.soundVolume=intent.getIntExtra("soundVolume", 0); 
	    
		setContentView(R.layout.about);

		
		ImageButton returnbutton = (ImageButton) findViewById(R.id.about_back);
		returnbutton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", About.this.musicVolume);
				intent.putExtra("soundVolume", About.this.soundVolume);
				intent.setClass(About.this, MainMenu.class);
				startActivity(intent);
				About.this.finish();
			}
		});
	}

}

