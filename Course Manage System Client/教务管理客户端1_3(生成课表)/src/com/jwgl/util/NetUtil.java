package com.jwgl.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

/**
 * 这个类主要负责封装一些函数，用于网络访问
 * 
 * @author Administrator
 * 
 */
public class NetUtil {
	
	public static List<Cookie> cookies = null ;
	
	/**
	 * 这个函数主要是封装get请求
	 * 
	 * @param url
	 *            get请求的链接
	 * @return
	 */
	public static String httpGet(String url) {
		HttpResponse httpResponse = null;
		// 生成一个请求对象
		HttpGet httpGet = new HttpGet(url);
		// 生成一个Http客户端对象
		HttpClient httpClient = new DefaultHttpClient();
		InputStream inputStream = null;
		//先判断是否有cookie信息，如果有，直接使用，没有就要获取
		if(cookies!=null&&cookies.size()>0){
			httpGet.setHeader("Cookie", "ASP.NET_SessionId="+cookies.get(0).getValue());
		}

		try {
			// 使用Http客户端发送请求对象
			httpResponse = httpClient.execute(httpGet);
			//在执行完请求后，服务器会发给我们一个cookie信息
			if(cookies==null||cookies.size()<=0){
				cookies = ((AbstractHttpClient)httpClient).getCookieStore().getCookies();
			}		
			// 从http响应对象中得到http entity
			HttpEntity httpEntity = httpResponse.getEntity();
			// 从http entity对象中得到内容，以输入输出流的形式
			inputStream = httpEntity.getContent();
			
			// 读取流的操作
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				result = result + line;
			}
			// 把http的响应内容返回出来
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭输入流
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * 
	 * @param url
	 *            post请求的链接
	 * @param nameValuePairs
	 *            请求参数列表
	 * @return
	 */
	public static String httpPost(String url, List<NameValuePair> nameValuePairs) {
		InputStream inputStream = null;
		try {
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(
					nameValuePairs, HTTP.UTF_8);
			// 把参数数据封装在http entity中
			HttpPost httpPost = new HttpPost(url);
			//先判断是否有cookie信息，如果有，直接使用，没有就要获取
			if(cookies!=null&&cookies.size()>0){
				httpPost.setHeader("Cookie", "ASP.NET_SessionId="+cookies.get(0).getValue());
			}
			// 生成一个httpPost对象
			httpPost.setEntity(requestHttpEntity);
			// 为http post请求设置http entity
			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse httpResponse = httpClient.execute(httpPost);
			//在执行完请求后，服务器会发给我们一个cookie信息
			if(cookies==null||cookies.size()<=0){
				cookies = ((AbstractHttpClient)httpClient).getCookieStore().getCookies();
			}		
			// 下面的代码和使用http get方式相同
			HttpEntity httpEntity = httpResponse.getEntity();
			inputStream = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				result = result + line;
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
