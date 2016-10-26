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
 * �������Ҫ�����װһЩ�����������������
 * 
 * @author Administrator
 * 
 */
public class NetUtil {
	
	public static List<Cookie> cookies = null ;
	
	/**
	 * ���������Ҫ�Ƿ�װget����
	 * 
	 * @param url
	 *            get���������
	 * @return
	 */
	public static String httpGet(String url) {
		HttpResponse httpResponse = null;
		// ����һ���������
		HttpGet httpGet = new HttpGet(url);
		// ����һ��Http�ͻ��˶���
		HttpClient httpClient = new DefaultHttpClient();
		InputStream inputStream = null;
		//���ж��Ƿ���cookie��Ϣ������У�ֱ��ʹ�ã�û�о�Ҫ��ȡ
		if(cookies!=null&&cookies.size()>0){
			httpGet.setHeader("Cookie", "ASP.NET_SessionId="+cookies.get(0).getValue());
		}

		try {
			// ʹ��Http�ͻ��˷����������
			httpResponse = httpClient.execute(httpGet);
			//��ִ��������󣬷������ᷢ������һ��cookie��Ϣ
			if(cookies==null||cookies.size()<=0){
				cookies = ((AbstractHttpClient)httpClient).getCookieStore().getCookies();
			}		
			// ��http��Ӧ�����еõ�http entity
			HttpEntity httpEntity = httpResponse.getEntity();
			// ��http entity�����еõ����ݣ����������������ʽ
			inputStream = httpEntity.getContent();
			
			// ��ȡ���Ĳ���
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				result = result + line;
			}
			// ��http����Ӧ���ݷ��س���
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// �ر�������
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
	 *            post���������
	 * @param nameValuePairs
	 *            ��������б�
	 * @return
	 */
	public static String httpPost(String url, List<NameValuePair> nameValuePairs) {
		InputStream inputStream = null;
		try {
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(
					nameValuePairs, HTTP.UTF_8);
			// �Ѳ������ݷ�װ��http entity��
			HttpPost httpPost = new HttpPost(url);
			//���ж��Ƿ���cookie��Ϣ������У�ֱ��ʹ�ã�û�о�Ҫ��ȡ
			if(cookies!=null&&cookies.size()>0){
				httpPost.setHeader("Cookie", "ASP.NET_SessionId="+cookies.get(0).getValue());
			}
			// ����һ��httpPost����
			httpPost.setEntity(requestHttpEntity);
			// Ϊhttp post��������http entity
			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse httpResponse = httpClient.execute(httpPost);
			//��ִ��������󣬷������ᷢ������һ��cookie��Ϣ
			if(cookies==null||cookies.size()<=0){
				cookies = ((AbstractHttpClient)httpClient).getCookieStore().getCookies();
			}		
			// ����Ĵ����ʹ��http get��ʽ��ͬ
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
