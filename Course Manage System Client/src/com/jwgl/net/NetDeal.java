package com.jwgl.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.jwgl.util.NetUtil;

public class NetDeal {
	private String UserName,PassWord;
	public NetDeal(String UserName,String password){
		this.UserName = UserName;
		this.PassWord = password;
	}
	/**
	 * µÇÂ½
	 * @return
	 */
	public boolean login(){
//		String loginUrl = 
//				"http://jwgl.cust.edu.cn/Login.aspx?username="
//		+UserName+"&password="+PassWord+"&role=student";
		String loginUrl = "http://jwgl.cust.edu.cn/PreLogin.aspx";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	 	NameValuePair nameValuePair1 = new BasicNameValuePair("__VIEWSTATE","dDwxNjY4ODI5NjgzO3Q8O2w8aTwxPjs+O2w8dDw7bDxpPDE+Oz47bDx0PHA8cDxsPEJhY2tJbWFnZVVybDs+O2w8aW1hZ2VzL3cxLmdpZjs+Pjs+O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzniYjmnKw6My40LjkxMjI2Nzs+PjtwPGw8c3R5bGU7PjtsPHBhZGRpbmctcmlnaHQ6MTBcOzs+Pj47Oz47Pj47Pj47Pj47bDxyb2xlMTtyb2xlMjtyb2xlMjtyb2xlNTtyb2xlNTtyb2xlNjtyb2xlNjtyb2xlNDtyb2xlNDtyb2xlNztyb2xlNzs+Pg6J6wtOOTiXnOxK+hxHBTjAcpsC");  
        NameValuePair nameValuePair2 = new BasicNameValuePair("BtnLogin","µÇÂ¼");
        NameValuePair nameValuePair3 = new BasicNameValuePair("Password",PassWord);  
        NameValuePair nameValuePair4 = new BasicNameValuePair("role","role1");
        NameValuePair nameValuePair5 = new BasicNameValuePair("Username",UserName);
        nameValuePairs.add(nameValuePair1);
        nameValuePairs.add(nameValuePair2);
        nameValuePairs.add(nameValuePair3);
        nameValuePairs.add(nameValuePair4);
        nameValuePairs.add(nameValuePair5);
		String response=NetUtil.httpPost(loginUrl, nameValuePairs);
		if(response.contains(UserName)){
			return true;
		}
		
		return false;
	}
	/**
	 * »ñÈ¡¿Î±í
	 * @return
	 */
	public String getTable(){
		String getTableUrl = 
				"http://jwgl.cust.edu.cn/kbcx/" +
				"PersonalCourses.aspx?role=student";
		String content = NetUtil.httpGet(getTableUrl);
		return content;
	}
	

}
