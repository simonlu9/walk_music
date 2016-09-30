import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.music.dao.TrackDao;

public class CloudMusicApi {
			
	
	public static void main(String[] args) throws Exception {
		//System.out.println(89);
		TrackDao  dao = new TrackDao();
		List ids = dao.findTracks();
		addToPlayList(ids,"473799522");
		
		//getUserListenRecord("116540575");
		//getTrackComment("37196629");
		//addTrackComment("37196629","hello bitch");
	}
	
	
	/*
	 * http://music.163.com/weapi/msg/private/send?csrf_token=ee8635a853198d019cb725fe126a19fc
	 * "{"type":"text","msg":"xcvxcvxcv","userIds":"[\"116540575\"]","time":"-1","csrf_token":"ee8635a853198d019cb725fe126a19fc"}"
	 * */
	public static void sendMessage(String uid,String content) throws JSONException{
		JSONObject param =  new JSONObject();
		param.put("type","text");
		param.put("msg", content);
		
		//param.put(, value)
	}
	
	
	
	/*
	 * 获取用戶听歌排行记录
	 * "{"uid":"116540575","type":"0","limit":"1000","offset":"0","total":"true","csrf_token":"b299ba62c57ad97ad80beb34c2f5d4d7"}"
	 * */
	public static void getUserListenRecord(String uid) throws Exception{
		JSONObject param =  new JSONObject();
		param.put("uid", uid);
		param.put("type","0");
		param.put("limit", "1000");
		param.put("offset","0");
		param.put("total","true");
		param.put("csrf_token", "72de26cc42527a22b007e3434e0419f6");
		
		String res = AesEncrypt.encrypt(param.toString(),"0CoJUm6Qyw8W8jud");
		
		res =  AesEncrypt.encrypt(res,"re9rnZIBPG8vJHT0");
		System.out.println(res);
		String rsaString = "c97c2c0f45920fca3c802605c25ded86d57762da56c5c8ec820b490f1891d94ce195f0ea6a24a0065e1d52671aff128a3ac853d1b146cd37ff61566fb55434c93f5a1b95d92f3f9f53129ae88dcbb52fd9830e024a7407a73be5ceaf905e32a74528b3e4f9b7fdb4bc3434ed575c81038839074718e295bbc1048c5d26a42f91";
		List values = new ArrayList();
		values.add(new BasicNameValuePair("params", res));
		values.add( new BasicNameValuePair("encSecKey", rsaString));
		 CloseableHttpClient httpClient =  HttpClients.createDefault();
		    HttpPost post = new HttpPost("http://music.163.com/weapi/v1/play/record?csrf_token=feba32b505ffdc14d3edde7a4eb7bc67");    
		post.addHeader("Cookie", "__gads=ID=d37ad833043438b9:T=1448585735:S=ALNI_Mast7F2aZvKJwDdCiU6ZzuFvl5jQQ; _ntes_nnid=6a48927e313db08684a2f69ef81d4af9,1448585708858; vjuids=-24680d5ac.151466fc561.0.d465985b; _ntes_nuid=6a48927e313db08684a2f69ef81d4af9; usertrack=c+5+hVZXv9Ktaj+0CPi5Ag==; NTES_CMT_USER_INFO=4686417%7Csimonlu123%7Chttp%3A%2F%2Fmimg.126.net%2Fp%2Fbutter%2F1008031648%2Fimg%2Fface_big.gif%7Cfalse%7Cc2ltb25sdTEyM0AxNjMuY29t; nteslogger_exit_time=1469596968365; NTES_PASSPORT=JpUa7wOkdRZ9KQaSjQecoUzN45ydEAvPE23YneWWgIfTbX4tbOxW2SnOyZdxE.JAQDubb5jZyyL0GeE93HzrsjqeUvo5Em1t3wRu0uTj8q1Pe; vjlast=1448585709.1471828080.11; vinfo_n_f_l_n3=3105ef2b5d64f73c.1.23.1448585708927.1471915901587.1471924116793; P_INFO=simonlu123@163.com|1472271606|0|other|00&99|gud&1471667150&easyread#gud&440600#10#0#0|&0|163&easyread&blog&kaola_check|simonlu123@163.com; Province=020; City=0757; _ga=GA1.2.517963274.1448585709; NETEASE_WDA_UID=1562682#|#1371429332931; NTESmusic_yyrSI=6399CF85A92EC3E114E42B8182CD054A.classa-music-yyr3.server.163.org-8010; JSESSIONID-WYYY=5a0a57987bf86ca7aff6e6778d1e50c56913101897ddf5eb128aab4c0978f23315002a77d2bdede070d27f75b51a8ae0af58071806f8fe90c2ba1d9495678dc3d215c6716f6c8087e3f8291dc7c2663f0bfcfc81403901b955baa8ac1ebc97ee5e0da72f3a5c4f3572d833982036283ff3361faa980f36bd318baa60f8534d5197c0baa3%3A1475050114427; _iuqxldmzr_=25; MUSIC_U=0e4857fbebd723d87125a22ac53cfe6720df35b3e4b78fe11342b823cd4d7b52a3af3268617150f232332f16da1dcf6ce64fb26f88948370a70b41177f9edcea; __csrf=b299ba62c57ad97ad80beb34c2f5d4d7; __remember_me=true; __utma=94650624.517963274.1448585709.1475046261.1475048315.52; __utmb=94650624.18.10.1475048315; __utmc=94650624; __utmz=94650624.1475048315.52.11.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
		post.addHeader("Host", "music.163.com");  
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
		post.addHeader("Referer","http://music.163.com/user/home?id=116540575");
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(values,"UTF-8");
		    
         post.setEntity(uefEntity);
        
         System.out.println("POST 请求...." + post.getURI());
         //执行请求
      
         
         CloseableHttpResponse httpResponse = httpClient.execute(post);
        
         try{
             HttpEntity entity = httpResponse.getEntity();
             if (null != entity){
            
                 System.out.println("-------------------------------------------------------");
                 System.out.println(EntityUtils.toString(entity));
                 System.out.println("-------------------------------------------------------");
             }
         } finally{
             httpResponse.close();
         }
		
	}
	
	/*
	 * http://music.163.com/weapi/v1/resource/comments/R_SO_4_37196629/?csrf_token=d3e549d71f3f597ac06a789a2ea70284
	 * "{"rid":"R_SO_4_37196629","offset":"20","total":"false","limit":"20","csrf_token":"d3e549d71f3f597ac06a789a2ea70284"}"
	 * 獲取歌曲評論
	 * */
	public static void getTrackComment(String songId) throws Exception{
		JSONObject param =  new JSONObject();
		param.put("rid", "R_SO_4_"+songId);
		param.put("offset", "20");
		param.put("total","false");
		param.put("limit","20");
		param.put("csrf_token","72de26cc42527a22b007e3434e0419f6");
         String res = AesEncrypt.encrypt(param.toString(),"0CoJUm6Qyw8W8jud");
		
		res =  AesEncrypt.encrypt(res,"re9rnZIBPG8vJHT0");
		System.out.println(res);
		String rsaString = "c97c2c0f45920fca3c802605c25ded86d57762da56c5c8ec820b490f1891d94ce195f0ea6a24a0065e1d52671aff128a3ac853d1b146cd37ff61566fb55434c93f5a1b95d92f3f9f53129ae88dcbb52fd9830e024a7407a73be5ceaf905e32a74528b3e4f9b7fdb4bc3434ed575c81038839074718e295bbc1048c5d26a42f91";
		List values = new ArrayList();
		values.add(new BasicNameValuePair("params", res));
		values.add( new BasicNameValuePair("encSecKey", rsaString));
		 CloseableHttpClient httpClient =  HttpClients.createDefault();
		    HttpPost post = new HttpPost("http://music.163.com/weapi/v1/resource/comments/R_SO_4_"+songId+"/?csrf_token=72de26cc42527a22b007e3434e0419f6");    
			post.addHeader("Cookie", "__gads=ID=d37ad833043438b9:T=1448585735:S=ALNI_Mast7F2aZvKJwDdCiU6ZzuFvl5jQQ; _ntes_nnid=6a48927e313db08684a2f69ef81d4af9,1448585708858; vjuids=-24680d5ac.151466fc561.0.d465985b; _ntes_nuid=6a48927e313db08684a2f69ef81d4af9; usertrack=c+5+hVZXv9Ktaj+0CPi5Ag==; NTES_CMT_USER_INFO=4686417%7Csimonlu123%7Chttp%3A%2F%2Fmimg.126.net%2Fp%2Fbutter%2F1008031648%2Fimg%2Fface_big.gif%7Cfalse%7Cc2ltb25sdTEyM0AxNjMuY29t; nteslogger_exit_time=1469596968365; NTES_PASSPORT=JpUa7wOkdRZ9KQaSjQecoUzN45ydEAvPE23YneWWgIfTbX4tbOxW2SnOyZdxE.JAQDubb5jZyyL0GeE93HzrsjqeUvo5Em1t3wRu0uTj8q1Pe; vjlast=1448585709.1471828080.11; vinfo_n_f_l_n3=3105ef2b5d64f73c.1.23.1448585708927.1471915901587.1471924116793; P_INFO=simonlu123@163.com|1472271606|0|other|00&99|gud&1471667150&easyread#gud&440600#10#0#0|&0|163&easyread&blog&kaola_check|simonlu123@163.com; Province=020; City=0757; _ga=GA1.2.517963274.1448585709; NETEASE_WDA_UID=1562682#|#1371429332931; NTESmusic_yyrSI=6399CF85A92EC3E114E42B8182CD054A.classa-music-yyr3.server.163.org-8010; JSESSIONID-WYYY=5a0a57987bf86ca7aff6e6778d1e50c56913101897ddf5eb128aab4c0978f23315002a77d2bdede070d27f75b51a8ae0af58071806f8fe90c2ba1d9495678dc3d215c6716f6c8087e3f8291dc7c2663f0bfcfc81403901b955baa8ac1ebc97ee5e0da72f3a5c4f3572d833982036283ff3361faa980f36bd318baa60f8534d5197c0baa3%3A1475050114427; _iuqxldmzr_=25; MUSIC_U=0e4857fbebd723d87125a22ac53cfe6720df35b3e4b78fe11342b823cd4d7b52a3af3268617150f232332f16da1dcf6ce64fb26f88948370a70b41177f9edcea; __csrf=b299ba62c57ad97ad80beb34c2f5d4d7; __remember_me=true; __utma=94650624.517963274.1448585709.1475046261.1475048315.52; __utmb=94650624.18.10.1475048315; __utmc=94650624; __utmz=94650624.1475048315.52.11.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
			post.addHeader("Host", "music.163.com");  
			post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
			post.addHeader("Referer","http://music.163.com/user/home?id=116540575");
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(values,"UTF-8");
			    
	         post.setEntity(uefEntity);
	        
	         System.out.println("POST 请求...." + post.getURI());
	         //执行请求
	      
	         
	         CloseableHttpResponse httpResponse = httpClient.execute(post);
	        
	         try{
	             HttpEntity entity = httpResponse.getEntity();
	             if (null != entity){
	            
	                 System.out.println("-------------------------------------------------------");
	                 System.out.println(EntityUtils.toString(entity));
	                 System.out.println("-------------------------------------------------------");
	             }
	         } finally{
	             httpResponse.close();
	         }
			
		
		
	}
	/*
	 * 點讚評論
	 * http://music.163.com/weapi/v1/comment/like?csrf_token=bafa47ad32f08da2a4cdb9f3eaa8b7f7
	 * "{"commentId":"20057562","threadId":"R_SO_4_77099","like":"true","csrf_token":"bafa47ad32f08da2a4cdb9f3eaa8b7f7"}"
	 * */
	public static void likeComment(String trackId,String commentId) throws JSONException{
		JSONObject param =  new JSONObject();
		param.put("commentId", commentId);
		param.put("threadId","R_SO_4_"+trackId);
		param.put("like","true");
		param.put("csrf_token","");
		
	}
	
	
	/*
	 * 添加歌曲評論
	 * http://music.163.com/weapi/resource/comments/add?csrf_token=d3e549d71f3f597ac06a789a2ea70284
	 * "{"threadId":"R_SO_4_37196629","content":"高音非常难上，666","csrf_token":"d3e549d71f3f597ac06a789a2ea70284"}"
	 * */
	public static void addTrackComment(String trackId,String content) throws Exception{
		JSONObject param =  new JSONObject();
		param.put("threadId", "R_SO_4_"+trackId);
		param.put("content",content);
		param.put("csrf_token","72de26cc42527a22b007e3434e0419f6");
	    String res = AesEncrypt.encrypt(param.toString(),"0CoJUm6Qyw8W8jud");
		
			res =  AesEncrypt.encrypt(res,"re9rnZIBPG8vJHT0");
			System.out.println(res);
			String rsaString = "c97c2c0f45920fca3c802605c25ded86d57762da56c5c8ec820b490f1891d94ce195f0ea6a24a0065e1d52671aff128a3ac853d1b146cd37ff61566fb55434c93f5a1b95d92f3f9f53129ae88dcbb52fd9830e024a7407a73be5ceaf905e32a74528b3e4f9b7fdb4bc3434ed575c81038839074718e295bbc1048c5d26a42f91";
			List values = new ArrayList();
			values.add(new BasicNameValuePair("params", res));
			values.add( new BasicNameValuePair("encSecKey", rsaString));
			 CloseableHttpClient httpClient =  HttpClients.createDefault();
			    HttpPost post = new HttpPost("http://music.163.com/weapi/resource/comments/add?csrf_token=72de26cc42527a22b007e3434e0419f6");    
				post.addHeader("Cookie", "__gads=ID=d37ad833043438b9:T=1448585735:S=ALNI_Mast7F2aZvKJwDdCiU6ZzuFvl5jQQ; _ntes_nnid=6a48927e313db08684a2f69ef81d4af9,1448585708858; vjuids=-24680d5ac.151466fc561.0.d465985b; _ntes_nuid=6a48927e313db08684a2f69ef81d4af9; usertrack=c+5+hVZXv9Ktaj+0CPi5Ag==; NTES_CMT_USER_INFO=4686417%7Csimonlu123%7Chttp%3A%2F%2Fmimg.126.net%2Fp%2Fbutter%2F1008031648%2Fimg%2Fface_big.gif%7Cfalse%7Cc2ltb25sdTEyM0AxNjMuY29t; nteslogger_exit_time=1469596968365; NTES_PASSPORT=JpUa7wOkdRZ9KQaSjQecoUzN45ydEAvPE23YneWWgIfTbX4tbOxW2SnOyZdxE.JAQDubb5jZyyL0GeE93HzrsjqeUvo5Em1t3wRu0uTj8q1Pe; vjlast=1448585709.1471828080.11; vinfo_n_f_l_n3=3105ef2b5d64f73c.1.23.1448585708927.1471915901587.1471924116793; P_INFO=simonlu123@163.com|1472271606|0|other|00&99|gud&1471667150&easyread#gud&440600#10#0#0|&0|163&easyread&blog&kaola_check|simonlu123@163.com; Province=020; City=0757; _ga=GA1.2.517963274.1448585709; NETEASE_WDA_UID=1562682#|#1371429332931; NTESmusic_yyrSI=6399CF85A92EC3E114E42B8182CD054A.classa-music-yyr3.server.163.org-8010; JSESSIONID-WYYY=5a0a57987bf86ca7aff6e6778d1e50c56913101897ddf5eb128aab4c0978f23315002a77d2bdede070d27f75b51a8ae0af58071806f8fe90c2ba1d9495678dc3d215c6716f6c8087e3f8291dc7c2663f0bfcfc81403901b955baa8ac1ebc97ee5e0da72f3a5c4f3572d833982036283ff3361faa980f36bd318baa60f8534d5197c0baa3%3A1475050114427; _iuqxldmzr_=25; MUSIC_U=0e4857fbebd723d87125a22ac53cfe6720df35b3e4b78fe11342b823cd4d7b52a3af3268617150f232332f16da1dcf6ce64fb26f88948370a70b41177f9edcea; __csrf=b299ba62c57ad97ad80beb34c2f5d4d7; __remember_me=true; __utma=94650624.517963274.1448585709.1475046261.1475048315.52; __utmb=94650624.18.10.1475048315; __utmc=94650624; __utmz=94650624.1475048315.52.11.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
				post.addHeader("Host", "music.163.com");  
				post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
				post.addHeader("Referer","http://music.163.com/user/home?id=116540575");
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(values,"UTF-8");
				    
		         post.setEntity(uefEntity);
		        
		         System.out.println("POST 请求...." + post.getURI());
		         //执行请求
		      
		         
		         CloseableHttpResponse httpResponse = httpClient.execute(post);
		        
		         try{
		             HttpEntity entity = httpResponse.getEntity();
		             if (null != entity){
		            
		                 System.out.println("-------------------------------------------------------");
		                 System.out.println(EntityUtils.toString(entity));
		                 System.out.println("-------------------------------------------------------");
		             }
		         } finally{
		             httpResponse.close();
		         }
				
		
	}
	
	
	
	/*
	 * 添加歌曲到歌单
	 * */
	public static void addToPlayList(List songIds,String playListId) throws Exception {
		Map nameValuePair = new HashMap();
		List values = new ArrayList();
		JSONObject param =  new JSONObject();
		//param.put("tracks", songId);
		param.put("pid", playListId);
	   JSONArray trackArr = new JSONArray(); //29800446 200527
	 //  trackArr.put(songId);
	   Iterator<String> iter = songIds.iterator();  
	   while(iter.hasNext())  
       {  
		   trackArr.put(iter.next());  
           //System.out.println(iter.next());  
       }  
	  // trackArr.put("200527");
	  // trackArr.put("29800446");
	   param.put("trackIds", trackArr);
	   param.put("op","add");
	   param.put("csrf_token","72de26cc42527a22b007e3434e0419f6" );
		//System.out.println(param);
		
		//String params = "{"tracks":"30500134","pid":"473799522","trackIds":"[\"30500134\"]","op":"add","csrf_token":"72de26cc42527a22b007e3434e0419f6\"}";
		String res = AesEncrypt.encrypt(param.toString(),"0CoJUm6Qyw8W8jud");
		res =  AesEncrypt.encrypt(res,"re9rnZIBPG8vJHT0");
	//	String rsaString = RsaEncrypt.encrypt("MuUE7ZMtiHPayrrb", "010001", "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7");
		String rsaString = "c97c2c0f45920fca3c802605c25ded86d57762da56c5c8ec820b490f1891d94ce195f0ea6a24a0065e1d52671aff128a3ac853d1b146cd37ff61566fb55434c93f5a1b95d92f3f9f53129ae88dcbb52fd9830e024a7407a73be5ceaf905e32a74528b3e4f9b7fdb4bc3434ed575c81038839074718e295bbc1048c5d26a42f91";
		values.add(new BasicNameValuePair("params", res));
	
		values.add( new BasicNameValuePair("encSecKey", rsaString));
		//System.out.println(values);
		//nameValuePair.put("nameValuePair", values);
		
		 //  RequestBuilder requestBuilder = RequestBuilder.post();
		   // requestBuilder.addParameters(  values);
		    
		    CloseableHttpClient httpClient =  HttpClients.createDefault();
		    HttpPost post = new HttpPost("http://music.163.com/weapi/playlist/manipulate/tracks?csrf_token=72de26cc42527a22b007e3434e0419f6");    
		post.addHeader("Cookie", "__gads=ID=d37ad833043438b9:T=1448585735:S=ALNI_Mast7F2aZvKJwDdCiU6ZzuFvl5jQQ; _ntes_nnid=6a48927e313db08684a2f69ef81d4af9,1448585708858; vjuids=-24680d5ac.151466fc561.0.d465985b; _ntes_nuid=6a48927e313db08684a2f69ef81d4af9; usertrack=c+5+hVZXv9Ktaj+0CPi5Ag==; NTES_CMT_USER_INFO=4686417%7Csimonlu123%7Chttp%3A%2F%2Fmimg.126.net%2Fp%2Fbutter%2F1008031648%2Fimg%2Fface_big.gif%7Cfalse%7Cc2ltb25sdTEyM0AxNjMuY29t; nteslogger_exit_time=1469596968365; NTES_PASSPORT=JpUa7wOkdRZ9KQaSjQecoUzN45ydEAvPE23YneWWgIfTbX4tbOxW2SnOyZdxE.JAQDubb5jZyyL0GeE93HzrsjqeUvo5Em1t3wRu0uTj8q1Pe; vjlast=1448585709.1471828080.11; vinfo_n_f_l_n3=3105ef2b5d64f73c.1.23.1448585708927.1471915901587.1471924116793; P_INFO=simonlu123@163.com|1472271606|0|other|00&99|gud&1471667150&easyread#gud&440600#10#0#0|&0|163&easyread&blog&kaola_check|simonlu123@163.com; Province=020; City=0757; _ga=GA1.2.517963274.1448585709; NETEASE_WDA_UID=1562682#|#1371429332931; NTESmusic_yyrSI=6DDAAFABC7BC70A76958993AE556B3D9.classa-music-yyr2.server.163.org-8010; MUSIC_U=0e4857fbebd723d87125a22ac53cfe67192dba7eb4fe2cae18160156f076e8a11e5a3b661db0863595e0df6573dca876a7188293c54b251731b299d667364ed3; __csrf=72de26cc42527a22b007e3434e0419f6; __remember_me=true; __utma=94650624.517963274.1448585709.1471328323.1475024805.49; __utmc=94650624; __utmz=94650624.1471328323.48.9.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
		post.addHeader("Host", "music.163.com");  
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
		
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(values,"UTF-8");
		    
            post.setEntity(uefEntity);
           
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            System.out.println(344);
            
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            System.out.println(677);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------------");
                }
            } finally{
                httpResponse.close();
            }
		    
		
		
		//req.setExtras(nameValuePair);
	}
}
