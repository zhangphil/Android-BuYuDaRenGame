package fishjoy.control.help;


import fishjoy.control.menu.MainMenu;
import fishjoy.control.menu.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;

public class Help extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

		//获得Gallery对象
		Gallery g = (Gallery) findViewById(R.id.Gallery01);

		//添加ImageAdapter给Gallery对象
		g.setAdapter(new ImageAdapter(this));

		//设置Gallery的背景
		g.setBackgroundResource(0);
				
		//设置Gallery的图片间隔
		g.setSpacing(120);
		
		/* findViewById(R.id.button1)取得布局main.xml中的help */
		ImageButton returnbutton = (ImageButton) findViewById(R.id.help_back);
		/* 监听button的事件信息 */
		returnbutton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				// 新建一个Intent对象 
				Intent intent = new Intent();
				// 指定intent要启动的类 
				intent.setClass(Help.this, MainMenu.class);
				// 启动一个新的Activity 
				startActivity(intent);
				// 关闭当前的Activity 
				Help.this.finish();
			}
		});
	}


}
