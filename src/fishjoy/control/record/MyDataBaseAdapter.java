package fishjoy.control.record;

import java.io.Serializable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseAdapter
{

	// 用于打印log
	private static final String	TAG	= "MyDataBaseAdapter";

	// 表中一条数据的名称
	public static final String	KEY_ID = "_id";												

	// 表中一条数据的内容
	public static final String	NAME = "name";
	
	// 表中一条数据的内容
	public static final String	MODE = "mode";

	// 表中一条数据的id
	public static final String	SCORE = "score";

	// 数据库名称为data
	private static final String	DB_NAME	= "fishjoy_rank.db";
	
	// 数据库表名
	private static final String	DB_TABLE = "ranking";
	
	// 数据库版本
	private static final int DB_VERSION = 1;

	// 本地Context对象
	private Context	mContext = null;
	
	//创建一个表
	private static final String	DB_CREATE = "CREATE TABLE " + DB_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"+ MODE + " TEXT,"+ SCORE + " TEXT)";

	// 执行open（）打开数据库时，保存返回的数据库对象
	private SQLiteDatabase mSQLiteDatabase	= null;

	// 由SQLiteOpenHelper继承过来
	private DatabaseHelper mDatabaseHelper	= null;
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		/* 构造函数-创建一个数据库 */
		DatabaseHelper(Context context)
		{
			//当调用getWritableDatabase() 
			//或 getReadableDatabase()方法时
			//则创建一个数据库
			super(context, DB_NAME, null, DB_VERSION);			
		}

		/* 创建一个表 */
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// 数据库没有表时创建一个
			db.execSQL(DB_CREATE);
		}

		/* 升级数据库 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}
	
	/* 构造函数-取得Context */
	public MyDataBaseAdapter(Context context)
	{
		mContext = context;
	}


	// 打开数据库，返回数据库对象
	public void open() throws SQLException
	{
		mDatabaseHelper = new DatabaseHelper(mContext);
		mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
	}


	// 关闭数据库
	public void close()
	{
		mDatabaseHelper.close();
	}

	/* 插入一条数据 */
	public long insertData(String name,String mode, String score)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, name);
		initialValues.put(MODE, mode);
		initialValues.put(SCORE, score);
		
		return mSQLiteDatabase.insert(DB_TABLE, KEY_ID, initialValues);
	}

	/* 删除一条数据 */
	public boolean deleteData(long rowId)
	{
		return mSQLiteDatabase.delete(DB_TABLE, KEY_ID + "=" + rowId, null) > 0;
	}

	/* 通过Cursor查询所有数据 */
	public Cursor fetchAllData()
	{
		return mSQLiteDatabase.query(DB_TABLE, new String[] { KEY_ID, NAME,MODE, SCORE }, null, null, null, null, null);
	}

	/* 查询指定数据 */
	public Cursor fetchData(long rowId) throws SQLException
	{

		Cursor mCursor =

		mSQLiteDatabase.query(true, DB_TABLE, new String[] { KEY_ID, NAME,MODE, SCORE }, KEY_ID + "=" + rowId, null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/* 更新一条数据 */
	public boolean updateData(long rowId, String name, String mode,String score)
	{
		ContentValues args = new ContentValues();
		args.put(NAME, name);
		args.put(MODE, mode);
		args.put(SCORE, score);
		return mSQLiteDatabase.update(DB_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
	}
	
}

