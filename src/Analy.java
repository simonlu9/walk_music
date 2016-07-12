import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Track;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.music.bean.Artist;
import org.music.bean.User;

public class Analy {
	static final long YEAR_MICO_SEC = (3600*24*365*1000);
	static ArrayDeque<File> queue = new ArrayDeque<File>();

	public static void main(String[] args) throws IOException, JSONException {
		File f = new File("E:\\download\\music.163.com\\artist-home");
//		String[] files = f.list();
//		for(String file : files)
//		System.out.println(file);
		queue.add(f);
		File[] fileArray;
		//FileUtils.listFiles(arg0, arg1, arg2)
		while (!queue.isEmpty()) {
			File currentFile = queue.pop();
			if(currentFile.isDirectory()){
				 fileArray = currentFile.listFiles();
			
					for (int i = 0; i < fileArray.length; i++) {
						// 递归调用
						if(fileArray[i].isDirectory()){
							//System.out.println(fileArray[i].getName());
							queue.add(fileArray[i]);
						}else{
							if(fileArray[i].getName().endsWith(".html")){
								
								//parseUserHtml(fileArray[i]);
								parseArtistHtml(fileArray[i]);
								break;
							}
							
							
							
						}
						
					}
					
				
			}else{
				
			}
			
		}
	}
	
	public static void parseUserHtml(File file) throws IOException{
		Document doc = Jsoup.parse(file, "UTF-8");
		Element userInfo = doc.select("div.name").get(0);
		User user = new User();
		user.setUsername(userInfo.select("span.tit").get(0).text()); //用户明
		user.setLevel(Integer.parseInt(userInfo.select("span.lev").get(0).text()));//级别
		
	   user.setGender(userInfo.select(".u-icn-01").size()>0?1:(userInfo.select(".u-icn-02").size()>0?2:0));//性别
	   Element tabBox = doc.getElementById("tab-box");
	   user.setFeed_num(Integer.parseInt(tabBox.getElementById("event_count").text()));
	   user.setFollow_num(Integer.parseInt(tabBox.getElementById("follow_count").text()));
	   user.setFans_num(Integer.parseInt(tabBox.getElementById("fan_count").text()));
		
	   if(doc.hasClass("f-brk")){
		   Element sign =  doc.select(".f-brk").get(0);
		   user.setSign(sign.text());//个性签名
	   }
	   
	   Element age = doc.getElementById("age");
	   if(age!=null){
		   long ageMico = Long.parseLong(age.attr("data-age"));
		   Calendar cal = Calendar.getInstance();
		   cal.setTimeInMillis(ageMico);
		   user.setAge(2016- cal.get(Calendar.YEAR));
		
		   Elements area = age.parent().select("span");
		   if(area.size()>0){
			   user.setArea(area.select("span").get(0).text().split("：")[1]); //区域
		   }
	   }
	
	   
	   user.setRecord(getNumbers((doc.getElementById("rHeader").select("h4").get(0).text())));//听过记录
	   user.setUid(getNumbers(doc.getElementById("m-record").attr("data-uid")));
	   user.setAvatar(doc.getElementById("ava").select("img").get(0).attr("src")); //头像
	   
	   if(doc.select(".u-slg").size()>0){
		   user.setWeibo_id(getNumbers(doc.select(".u-slg").get(0).attr("href")));
	   }
	   //收藏的歌单 
	   //创建的歌单
	   
	   
	System.out.println(user);
		
	}
	
	 //截取数字  
	   public static Long getNumbers(String content) {  
		 //  System.out.println(content);
	       Pattern pattern = Pattern.compile("\\d+");  
	       Matcher matcher = pattern.matcher(content);  
	       while (matcher.find()) {  
	           return Long.parseLong(matcher.group(0));  
	       }  
	       return (long) 0;  
	   }  

	public void parseAritstHtml(File file) throws IOException {
		

	}
	
	public void parseSongHtml(File file) throws IOException{
		Document doc = Jsoup.parse(file, "UTF-8");
	}
	public static void parseArtistHtml(File file) throws IOException, JSONException{
		Document doc = Jsoup.parse(file, "UTF-8");
		//System.out.println(file.getPath());
		if(doc.getElementById("song-list-pre-cache")!=null){
			String content = doc.getElementById("song-list-pre-cache").select("textarea").get(0).text();
			  JSONArray jsonArr = new JSONArray(content);
			  Artist artist = new Artist();
			   for(int i=0;i<jsonArr.length();i++)
	    	      {   
				   	JSONObject  songInfo = jsonArr.getJSONObject(i);
				   	org.music.bean.Track track = new org.music.bean.Track();
				   //	track.setDuration(duration);
				
				   		
	        	  			System.out.println(	jsonArr.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name"));
	        	  	
	 	      }
	         
		}else{
			
		}
		
		
		
	}
}
