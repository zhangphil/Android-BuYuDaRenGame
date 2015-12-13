package fishjoy.control.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import fishjoy.control.about.About;
import fishjoy.control.billboard.Billboard;
import fishjoy.control.help.Help;
import fishjoy.control.record.MyDataBaseAdapter;
import fishjoy.control.systemsetting.Systemsetting;


public class MainMenu extends Activity {
	private int musicVolume;
	private int soundVolume;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
		
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);  
        
        Intent intent=getIntent(); 
        this.musicVolume=intent.getIntExtra("musicVolume", audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)); 
        this.soundVolume=intent.getIntExtra("soundVolume", audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)); 
        
        this.startGame();
        this.set();
        this.help();
        this.billboard();
        this.about();
        this.exit();

    }
	

	public void startGame(){
		ImageButton startButton = (ImageButton) findViewById(R.id.start);
		startButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("database", mDataBase);
				Intent intent = new Intent();
				
				intent.putExtra("musicVolume", MainMenu.this.musicVolume);
				intent.putExtra("soundVolume", MainMenu.this.soundVolume);			
//				intent.putExtras(bundle);
				intent.setClass(MainMenu.this, SubMenuForGame.class);
				
				startActivity(intent);
				MainMenu.this.finish();
			}
		});
	}
	
	public void set(){
		ImageButton setButton = (ImageButton) findViewById(R.id.set);
		setButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", MainMenu.this.musicVolume);
				intent.putExtra("soundVolume", MainMenu.this.soundVolume);
				intent.setClass(MainMenu.this, Systemsetting.class);
				startActivity(intent);
				MainMenu.this.finish();
			}
		});
	}

	public void help(){

		ImageButton helpButton = (ImageButton) findViewById(R.id.help);
		helpButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", MainMenu.this.musicVolume);
				intent.putExtra("soundVolume", MainMenu.this.soundVolume);
				intent.setClass(MainMenu.this, Help.class);
				startActivity(intent);
				MainMenu.this.finish();
			}
		});
	}
	
	
	public void billboard() {
		ImageButton billboardButton = (ImageButton) findViewById(R.id.rank);
		billboardButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", MainMenu.this.musicVolume);
				intent.putExtra("soundVolume", MainMenu.this.soundVolume);
				intent.setClass(MainMenu.this, Billboard.class);
				startActivity(intent);
				MainMenu.this.finish();
			}
		});
		
	}
	
	public void exit(){
		ImageButton exitButton = (ImageButton) findViewById(R.id.exit);
		exitButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				android.os.Process.killProcess(android.os.Process.myPid());

				System.exit(0);
			}
		});
	}

	public void about() {
		ImageView aboutButton = (ImageView) findViewById(R.id.logo);
		aboutButton.setClickable(true);
		aboutButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.putExtra("musicVolume", MainMenu.this.musicVolume);
				intent.putExtra("soundVolume", MainMenu.this.soundVolume);
				intent.setClass(MainMenu.this, About.class);
				startActivity(intent);
				MainMenu.this.finish();
			}
		});
		
	}
}
