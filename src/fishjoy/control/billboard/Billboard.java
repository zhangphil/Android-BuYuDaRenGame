package fishjoy.control.billboard;

import fishjoy.control.menu.MainMenu;
import fishjoy.control.menu.R;
import fishjoy.control.record.MyDataBaseAdapter;
import fishjoy.control.systemsetting.Systemsetting;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Billboard extends Activity{
	private TextView mText_easy,mText_normal,mText_hard,mText_easy_1,mText_easy_2,mText_easy_3,
					 mText_normal_1,mText_normal_2,mText_normal_3,mText_hard_1,mText_hard_2,mText_hard_3;
	private int musicVolume;
	private int soundVolume;
	MyDataBaseAdapter mDataBase;
	class User
	{
		String name;
		String score;
		String difficulty;
		public User(String name,String score,String difficulty){
			 this.name=name;
			 this.score=score;
			 this.difficulty=difficulty;
		}
		public String getScore() {
			 return score;
		}
		public void setScore(String score) {
			 this.score = score;
		}
		public String getName() {
			 return name;
		}
		public void setName(String name) {
			 this.name = name;
		} 
		public String getDifficulty() {
			 return difficulty;
		}
		public void setDifficulty(String difficulty) {
			 this.difficulty = difficulty;
		} 
	}
	List<User> userlist=new ArrayList();
	public class ComparatorUser implements Comparator{

		 public int compare(Object arg0, Object arg1) {
		  User user0=(User)arg0;
		  User user1=(User)arg1;

		   //首先比较年龄，如果年龄相同，则比较名字

		  int flag=user0.getDifficulty().compareTo(user1.getDifficulty());
		  if(flag==0){
		   if(Integer.valueOf(user1.getScore())<Integer.valueOf(user0.getScore()))
			   return -1;
		   else if(Integer.valueOf(user1.getScore())>Integer.valueOf(user0.getScore()))
			   return 1;
		   else 
			   return 0;
		  }else{
		   return flag;
		  }  
		 }	 
		}
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Intent intent=getIntent(); 
	    this.musicVolume=intent.getIntExtra("musicVolume", 0); 
	    this.soundVolume=intent.getIntExtra("soundVolume", 0); 
	    
		setContentView(R.layout.billboard);
		
		mDataBase=new MyDataBaseAdapter(this);
		mDataBase.open();
		
		this.mText_easy = (TextView) findViewById(R.id.billboard_easy);
		this.mText_normal = (TextView) findViewById(R.id.billboard_normal);
		this.mText_hard = (TextView) findViewById(R.id.billboard_hard);
		this.mText_easy_1 = (TextView) findViewById(R.id.billboard_easy_1);
		this.mText_easy_2 = (TextView) findViewById(R.id.billboard_easy_2);
		this.mText_easy_3 = (TextView) findViewById(R.id.billboard_easy_3);
		this.mText_normal_1 = (TextView) findViewById(R.id.billboard_normal_1);
		this.mText_normal_2 = (TextView) findViewById(R.id.billboard_normal_2);
		this.mText_normal_3 = (TextView) findViewById(R.id.billboard_normal_3);
		this.mText_hard_1 = (TextView) findViewById(R.id.billboard_hard_1);
		this.mText_hard_2 = (TextView) findViewById(R.id.billboard_hard_2);
		this.mText_hard_3 = (TextView) findViewById(R.id.billboard_hard_3);
		
		this.mText_easy.setText("EASY:");
		this.mText_normal.setText("NORMAL:");
		this.mText_hard.setText("HARD:");
		
		Cursor cursor=mDataBase.fetchAllData();
		if(cursor.getCount()!=0)
		{
			cursor.moveToFirst();
			for(int i=0;i<cursor.getCount();i++)
			{
				userlist.add(new User(cursor.getString(1),cursor.getString(3),cursor.getString(2)));
				cursor.moveToNext();
			}
			
			ComparatorUser comparator=new ComparatorUser();
			Collections.sort(userlist, comparator);
			
			for(int i=0;i<userlist.size();i++)
			{
				if(userlist.get(i).getDifficulty().equals("1"))
				{
					this.mText_easy_1.setText("1."+userlist.get(i).getName()+"\n"+userlist.get(i).getScore());
					if(i+1<userlist.size() && userlist.get(i+1).getDifficulty().equals("1"))
						this.mText_easy_2.setText("2."+userlist.get(i+1).getName()+"\n"+userlist.get(i+1).getScore());
					if(i+2<userlist.size() && userlist.get(i+2).getDifficulty().equals("1"))
						this.mText_easy_3.setText("3."+userlist.get(i+2).getName()+"\n"+userlist.get(i+2).getScore());
					break;
				}
			}
			
			for(int i=0;i<userlist.size();i++)
			{
				if(userlist.get(i).getDifficulty().equals("2"))
				{
					this.mText_normal_1.setText("1."+userlist.get(i).getName()+"\n"+userlist.get(i).getScore());
					if(i+1<userlist.size() && userlist.get(i+1).getDifficulty().equals("2"))
						this.mText_normal_2.setText("2."+userlist.get(i+1).getName()+"\n"+userlist.get(i+1).getScore());
					if(i+2<userlist.size() && userlist.get(i+2).getDifficulty().equals("2"))
						this.mText_normal_3.setText("3."+userlist.get(i+2).getName()+"\n"+userlist.get(i+2).getScore());
					break;
				}
			}
			
			for(int i=0;i<userlist.size();i++)
			{
				if(userlist.get(i).getDifficulty().equals("3"))
				{
					this.mText_hard_1.setText("1."+userlist.get(i).getName()+"\n"+userlist.get(i).getScore());
					if(i+1<userlist.size() && userlist.get(i+1).getDifficulty().equals("3"))
						this.mText_hard_2.setText("2."+userlist.get(i+1).getName()+"\n"+userlist.get(i+1).getScore());
					if(i+2<userlist.size() && userlist.get(i+2).getDifficulty().equals("3"))
						this.mText_hard_3.setText("3."+userlist.get(i+2).getName()+"\n"+userlist.get(i+2).getScore());
					break;
				}
			}
		}
		ImageButton returnbutton = (ImageButton) findViewById(R.id.billboard_back);
		returnbutton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				mDataBase.close();
				Intent intent = new Intent();
				intent.putExtra("musicVolume", Billboard.this.musicVolume);
				intent.putExtra("soundVolume", Billboard.this.soundVolume);
				intent.setClass(Billboard.this, MainMenu.class);
				startActivity(intent);
				Billboard.this.finish();
			}
		});
	}
}
