package com.example.jwgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.jwgl.net.GetTable;
import com.jwgl.net.NetDeal;
import com.jwgl.net.NetLogin;
import com.jwgl.util.HtmlPrase;


import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    private EditText UserName;
	private EditText Password;
	private NetDeal netDeal;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			int what = msg.what;
			switch(what){
			case 0:
				getTable.setEnabled(true);
				SharedPreferences.Editor editor =
						MainActivity.this
						.getSharedPreferences("login", Context.MODE_PRIVATE).edit();
				editor.putString("UserName", UserName.getText().toString());
				editor.putString("PassWord", Password.getText().toString());
				editor.commit();
				Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
				break;
			case 1:
				Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_LONG).show();
				break;
			case 2:
				String content = (String)msg.obj;
				Toast.makeText(MainActivity.this, "获取课表成功", Toast.LENGTH_LONG).show();
				getTable.setEnabled(false);
				HtmlPrase.htmlPrase(content,arrays);
				adapter.notifyDataSetChanged();
				break;
			
			}
			super.handleMessage(msg);
		}
		
	};
	private Button getTable;
	private GridView response;
	private ArrayList<HashMap<String, Object>> arrays;
	private SimpleAdapter adapter;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //定义一个listView数据适配器
     
       
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userName = UserName.getText().toString().trim();
				String passWord = Password.getText().toString().trim();
				if(userName.length()>0&&passWord.length()>0){
					netDeal = new NetDeal(userName,passWord);
					NetLogin login = new NetLogin(netDeal,handler);
					login.start();
				}else{
					Toast
					.makeText(MainActivity.this, "请输入用户名和密码", Toast.LENGTH_LONG).show();
				}
			}
		});
        getTable = (Button)findViewById(R.id.getTable);
        getTable.setEnabled(false);
        getTable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				if(netDeal!=null){
					GetTable  getTable = new GetTable(netDeal,handler);
					getTable.start();
				}
				
			}
		});
        
        UserName = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.PassWord);
        response = (GridView)findViewById(R.id.gridview);
        SharedPreferences sp =this.getSharedPreferences("login",Context.MODE_PRIVATE);
        String str_username=sp.getString("UserName", "");
        String str_password = sp.getString("PassWord", "");
        UserName.setText(str_username);
        Password.setText(str_password);
        
        
        
        arrays 
        = new ArrayList<HashMap<String,Object>>();
//        for(int i = 0;i< 42;i++){
//        	HashMap<String,Object> map = new HashMap<String, Object>();
//        	map.put("lesson", "数学");
//        	map.put("teacher", "王长佳");
//        	map.put("site", "东配110");
//        	map.put("time", "周一");
//        	map.put("num", "110");
//        	arrays.add(map);
//        }
        adapter =
        		new SimpleAdapter(this, arrays
        				, R.layout.girditem
        				, new String[]{"td0","td1","td2","td3","td4","td5"}
        		, new int[]{R.id.all,R.id.lesson,R.id.teacher,R.id.site,R.id.time,R.id.num});
        response.setAdapter(adapter);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
