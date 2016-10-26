package com.jwgl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;
/**
 * 
 * 用于解析html字符串
 * @author Administrator
 *
 */
public class HtmlPrase {
	
	
	public static void htmlPrase(String html){
		//生成解析对象，用于将html字符串包装
		Document doc = Jsoup.parse(html);
		//筛选出咱们自己的标签，获取其中的内同
		Elements elements=doc.select("td[class=ContentCell]");
		for(Element element : elements){
			Elements tds=element.getElementsByTag("td");
			for(Element td : tds){
				Log.v("td",td.text());
			}
		}
		
		
	}
	public static void htmlPrase(String html,ArrayList<HashMap<String,Object>> arrays){
		//生成解析对象，用于将html字符串包装
		if(arrays!=null&&arrays.size()>0){
			arrays.clear();
		}
		Document doc = Jsoup.parse(html);
		//筛选出咱们自己的标签，获取其中的内同
		Elements elements=doc.select("td[class=ContentCell]");
		int j=0;
		for(Element element : elements){
			Elements tds=element.getElementsByTag("td");
			HashMap<String, Object> map = new HashMap<String, Object>();
			for(int i=0;i<6;i++){
				if(i==0){
					map.put("td"+i, "");
				}
				else{
				map.put("td"+i, tds.get(i).text());
				Log.v("j="+j, tds.get(i).text());
				}
			}
			arrays.add(map);
			
			j++;
		}
		
		
	}

}
