import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PrivilegedActionException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Track;
import javax.xml.stream.events.EndElement;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.music.bean.Album;
import org.music.bean.Area;
import org.music.bean.Artist;
import org.music.bean.PlayList;
import org.music.bean.User;
import org.music.dao.AlbumDao;
import org.music.dao.ArtistDao;
import org.music.dao.PlayListDao;
import org.music.dao.TrackDao;
import org.music.dao.UserDao;

public class Analy {
	static final long YEAR_MICO_SEC = (3600 * 24 * 365 * 1000);
	//static ArrayDeque<File> queue = new ArrayDeque<File>();
	static UserDao userDao = new UserDao();
	static TrackDao trackDao = new TrackDao();
	static ArtistDao artistDao = new ArtistDao();
	static PlayListDao playlistDao = new PlayListDao();
	static AlbumDao albumDao = new AlbumDao();
	static Area areaObj = new Area();

	public static void main(String[] args) throws IOException, JSONException, SQLException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Class<?> threadClazz = Class.forName("Analy");  
		Thread threads[]=new Thread[6];
		    threads[0] = new HandleThread("E:\\download\\music.163.com\\user-home", "F:\\output\\user-home", threadClazz.getMethod("parseUserHtml",File.class));
		    threads[1] = new HandleThread("E:\\download\\music.163.com\\song-home", "F:\\output\\song-home", threadClazz.getMethod("parseSongHtml",File.class));
		     threads[2] = new HandleThread("E:\\download\\music.163.com\\artist-home", "F:\\output\\artist-home", threadClazz.getMethod("parseArtistHtml",File.class));
		    threads[3] = new HandleThread("E:\\download\\music.163.com\\playlist-home", "F:\\output\\playlist-home", threadClazz.getMethod("parsePlaylistHtml",File.class));
		    threads[4] = new HandleThread("E:\\download\\music.163.com\\album-home", "F:\\output\\album-home", threadClazz.getMethod("parseAlbumHtml",File.class));
		 		
		   //  threads[4].start();
		    for (int i = 0; i <5; i++)
	            threads[i].start();
		     //thread.start();
		
	}
	
	public static void test(File file) {
		System.out.println(file);
	}

	public static void parsePlaylistHtml(File file) throws IOException, JSONException {
		Document doc = Jsoup.parse(file, "UTF-8");
		Element info = doc.getElementById("m-playlist");
		PlayList playList = new PlayList();
		if(info.select("div.tit").size()==0){
			return;
		}
		playList.setName(info.select("div.tit").get(0).text());
		playList.setPublishDate(info.select("span.time").get(0).text().split("&")[0]);
		playList.setPic(info.select(".cover img").get(0).attr("src"));
		Element operate = info.getElementById("content-operation");
		playList.setFavNum(getNumbers(operate.select("a").get(2).text()));
		playList.setShareNum(getNumbers(operate.select("a").get(3).text()));
		playList.setCommentNum(getNumbers(operate.select("a").get(5).text()));
		playList.setId(getNumbers(doc.getElementById("content-operation").attr("data-rid")));
		playList.setUid(getNumbers(doc.select(".user .name a").get(0).attr("href").split("\\?")[1]));
		if (info.getElementById("album-desc-more") != null) {
			playList.setDesc(info.getElementById("album-desc-more").text());
		}
		playList.setTrackNum(getNumbers(info.getElementById("playlist-track-count").text()));
		playList.setPlayNum(getNumbers(info.getElementById("play-count").text()));
		
		System.out.println(playList);
		playlistDao.addPlayList(playList);
		
		Set<Long> trackIds = new HashSet<Long>();
		if (doc.getElementById("song-list-pre-cache") != null) {
			String content = doc.getElementById("song-list-pre-cache").select("textarea").get(0).text();
			JSONArray jsonArr = new JSONArray(content);

			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject songInfo = jsonArr.getJSONObject(i);
				org.music.bean.Track track = new org.music.bean.Track();
				// track.setDuration(duration);
				track.setDuration(songInfo.getInt("duration"));
				track.setScore(songInfo.getInt("score"));
				track.setId(songInfo.getLong("id"));
				track.setName(songInfo.getString("name"));
				track.setAlbumId(songInfo.getJSONObject("album").getLong("id"));
				track.setArtistId(songInfo.getJSONArray("artists").getJSONObject(0).getLong("id"));
				trackDao.replaceTrack(track);
				trackIds.add(track.getId());

				//System.out.println(track);

				// System.out.println(
				// jsonArr.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name"));

			}
			playlistDao.addPlayListTrack(playList, trackIds);
		
		}
		
		
	}

	public static void parseUserHtml(File file) throws IOException {
		Document doc = Jsoup.parse(file, "UTF-8");
		if (doc.select("div.g-bd").size() == 0) {
			return;
		}
		Element userInfo = doc.select("div.g-bd").get(0);
		User user = new User();
		if(userInfo.select("span.tit").size()==0){
			return;
		}
		user.setUsername(userInfo.select("span.tit").get(0).text()); // 用户明
		user.setLevel(Integer.parseInt(userInfo.select("span.lev").get(0).text()));// 级别

		user.setGender(userInfo.select(".u-icn-01").size() > 0 ? 1 : (userInfo.select(".u-icn-02").size() > 0 ? 2 : 0));// 性别
		Element tabBox = doc.getElementById("tab-box");
		user.setFeed_num(Integer.parseInt(tabBox.getElementById("event_count").text()));
		user.setFollow_num(Integer.parseInt(tabBox.getElementById("follow_count").text()));
		user.setFans_num(Integer.parseInt(tabBox.getElementById("fan_count").text()));

		if (userInfo.select(".f-brk").size() > 0) {
			Element sign = userInfo.select(".f-brk").get(0);
			String[] signArr = sign.text().split("：");
			if (signArr.length < 2) {
				user.setSign(signArr[0]);
			} else {
				user.setSign(signArr[1]);
			}
			// user.setSign(sign.text().split("：")[1]);// 个性签名
		} else {
			user.setSign("");
		}

		Element age = doc.getElementById("age");
		if (age != null) {
			long ageMico = Long.parseLong(age.attr("data-age"));
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(ageMico);
			user.setAge(2016 - cal.get(Calendar.YEAR));

			Elements area = age.parent().select("span");
			if (area.size() > 0) {
				user.setArea(area.select("span").get(0).text().split("：")[1]); // 区域
				String[] areaInfo = user.getArea().split("-");
				if (areaInfo.length < 2) {
					user.setProvince(0);
					user.setCity(0);
				} else {
					areaInfo[0] = areaInfo[0].trim();
					areaInfo[1] = areaInfo[1].trim();
					if (areaObj.direct.get(areaInfo[1]) == null) {
						if (areaInfo[1].equals("万州区")) {
							user.setProvince(areaObj.direct.get("直辖市"));
							user.setCity(areaObj.direct.get("重庆市"));
						} else if (areaInfo[1].equals("和平区")) {
							user.setProvince(areaObj.direct.get("直辖市"));
							user.setCity(areaObj.direct.get("天津市"));
						} else if (areaInfo[1].equals("黄埔区")) {
							user.setProvince(areaObj.direct.get("直辖市"));
							user.setCity(areaObj.direct.get("上海市"));
						} else if (areaInfo[1].equals("东城区")) {
							user.setProvince(areaObj.direct.get("直辖市"));
							user.setCity(areaObj.direct.get("北京市"));
						}
					} else {
						user.setProvince(areaObj.direct.get(areaInfo[0]));
						user.setCity(areaObj.direct.get(areaInfo[1]));
					}
				}

			}

		}

		user.setRecord(getNumbers((doc.getElementById("rHeader").select("h4").get(0).text())));// 听过记录
		user.setUid(getNumbers(doc.getElementById("m-record").attr("data-uid")));
		user.setAvatar(doc.getElementById("ava").select("img").get(0).attr("src")); // 头像

		if (doc.select(".u-slg").size() > 0) {
			user.setWeibo_id(getNumbers(doc.select(".u-slg").get(0).attr("href")));
		}
		// 收藏的歌单
		// 创建的歌单
		System.out.println(user);

		userDao.addUsers(user);

		// 移动文件

	}

	// 截取数字
	public static Long getNumbers(String content) {
		// System.out.println(content);
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return Long.parseLong(matcher.group(0));
		}
		return (long) 0;
	}

	public static void parseSongHtml(File file) throws IOException {
		Document doc = Jsoup.parse(file, "UTF-8");
		Element songInfo = doc.select(".cnt").get(0);
		org.music.bean.Track track = new org.music.bean.Track();
		track.setName(songInfo.select(".f-ff2").get(0).text());
		Elements media = songInfo.select(".des a");
		if(media.size()==0){
			track.setArtistId(0);
			track.setAlbumId(0);
		}else if (media.size()==1) {
			track.setArtistId(getNumbers(media.get(0).attr("href")));
			track.setAlbumId(0);
		}else if(media.size()==2){
			track.setAlbumId(getNumbers(media.get(1).attr("href")));
			track.setArtistId(getNumbers(media.get(0).attr("href")));
		}
		track.setId(getNumbers(doc.getElementById("content-operation").attr("data-rid")));
	
		System.out.println(track);
		trackDao.addTrack(track);
		
		
		
		

	}

	public static void parseAlbumHtml(File file) throws IOException, JSONException {
		Document doc = Jsoup.parse(file, "UTF-8");
		Elements intros = doc.select("p.intr");
		Album album = new Album();
		album.setName(doc.select(".tit").get(0).text());
		
		if(intros.size()==0){
			return;
		}
		if(intros.size()>=1){
			album.setArtistId(getNumbers(intros.get(0).select("a").get(0).attr("href").split("\\?")[1]));
		}else{
			album.setArtistId(0);
		}
		
		if(intros.size()>=2){
			album.setPublishDate(intros.get(1).text().split("：")[1]);
		}
		
		if (intros.size() >= 3) {
			album.setCompany(intros.get(2).text().split("：")[1]);
		}
		if (doc.getElementById("album-desc-more") != null) {
			album.setDesc(doc.getElementById("album-desc-more").text());
		}
		album.setCommentNum(getNumbers(doc.getElementById("cnt_comment_count").text()));
		album.setShareNum(getNumbers(doc.select(".u-btni-share i").text()));
		Elements sub = doc.select(".sub");
		album.setTrackNum(getNumbers(sub.get(0).text()));
		album.setPic(doc.select(".cover img").get(0).attr("src"));
		album.setId(getNumbers(doc.getElementById("content-operation").attr("data-rid")));

		System.out.println(album);
		albumDao.addAlbum(album);
		Set<Long> trackIds = new HashSet<Long>();
		if (doc.getElementById("song-list-pre-cache") != null) {
			String content = doc.getElementById("song-list-pre-cache").select("textarea").get(0).text();
			JSONArray jsonArr = new JSONArray(content);

			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject songInfo = jsonArr.getJSONObject(i);
				org.music.bean.Track track = new org.music.bean.Track();
				// track.setDuration(duration);
				track.setDuration(songInfo.getInt("duration"));
				track.setScore(songInfo.getInt("score"));
				track.setId(songInfo.getLong("id"));
				track.setName(songInfo.getString("name"));
				track.setAlbumId(songInfo.getJSONObject("album").getLong("id"));
				track.setArtistId(songInfo.getJSONArray("artists").getJSONObject(0).getLong("id"));
				trackDao.replaceTrack(track);
				trackIds.add(track.getId());

				//System.out.println(track);

				// System.out.println(
				// jsonArr.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name"));

			}
			albumDao.addAlbumTrack(album, trackIds);
		
		}
		
		
	}

	public static void parseArtistHtml(File file) throws IOException, JSONException {
		Document doc = Jsoup.parse(file, "UTF-8");
		// System.out.println(file.getPath());
		Elements metas = doc.select("meta");
		Artist artist = new Artist();
		if(metas.size()>=3){
			artist.setName(metas.get(2).attr("content"));
		}
	
		if(metas.size()>=7){
			artist.setAvatar(metas.get(6).attr("content"));
		}
		if(metas.size()>=8){
		artist.setId(getNumbers(metas.get(7).attr("content")));
		}
		if(metas.size()>=9){
			artist.setDesc(metas.get(8).attr("content"));
		}
		
		System.out.println(artist);
		artistDao.addArtist(artist);
		
	
		if (doc.getElementById("song-list-pre-cache") != null) {
			String content = doc.getElementById("song-list-pre-cache").select("textarea").get(0).text();
			JSONArray jsonArr = new JSONArray(content);

			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject songInfo = jsonArr.getJSONObject(i);
				org.music.bean.Track track = new org.music.bean.Track();
				// track.setDuration(duration);
				track.setDuration(songInfo.getInt("duration"));
				track.setScore(songInfo.getInt("score"));
				track.setId(songInfo.getLong("id"));
				track.setName(songInfo.getString("name"));
				track.setAlbumId(songInfo.getJSONObject("album").getLong("id"));
				track.setArtistId(songInfo.getJSONArray("artists").getJSONObject(0).getLong("id"));
				trackDao.replaceTrack(track);
				

				//System.out.println(track);

				// System.out.println(
				// jsonArr.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name"));

			}
		

		} else {

		}

	}
}

class HandleThread extends Thread{
	private ArrayDeque<File> queue = new ArrayDeque<File>();
	private String inputDir = "";
	private String outputDir="";
	private Method method ;
	public HandleThread(String inputDir,String outputDir,Method method) {
		// TODO Auto-generated constructor stub
		this.outputDir = outputDir;
		this.inputDir = inputDir;
		this.method = method;
	}
	
	@Override
	public void run() {
		File f = new File(inputDir);

		// String[] files = f.list();
		// for(String file : files)
		// System.out.println(file);
		queue.add(f);
		File[] fileArray;
		// FileUtils.listFiles(arg0, arg1, arg2)
		while (!queue.isEmpty()) {
			File currentFile = queue.pop();
			if (currentFile.isDirectory()) {
				fileArray = currentFile.listFiles();

				for (int i = 0; i < fileArray.length; i++) {
					// 递归调用
					if (fileArray[i].isDirectory()) {
						// System.out.println(fileArray[i].getName());
						queue.add(fileArray[i]);
					} else {
						if (fileArray[i].getName().endsWith(".html")) {
							
							try {
								method.invoke(null,fileArray[i] );
							} catch (IllegalAccessException e) {
				
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							//parseUserHtml(fileArray[i]);
							// parseArtistHtml(fileArray[i]);
							// parseAlbumHtml(fileArray[i]);
							// parsePlaylistHtml(fileArray[i]);
							// break;
							File newFile = new File(Output.getOutputFilePath(outputDir),
									fileArray[i].getName());
							if (!newFile.getParentFile().exists()) {
								newFile.getParentFile().mkdirs();
							}
							fileArray[i].renameTo(newFile);
						}

					}

				}

			} else {

			}

		}
		
	}
	
	
	
}

