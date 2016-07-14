import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.music.dao.UserDao;

public class Analy {
	static final long YEAR_MICO_SEC = (3600 * 24 * 365 * 1000);
	static ArrayDeque<File> queue = new ArrayDeque<File>();
	static UserDao userDao = new UserDao();
	static Area areaObj = new Area();

	public static void main(String[] args) throws IOException, JSONException, SQLException {
		File f = new File("E:\\download\\music.163.com\\user-home");
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

							 parseUserHtml(fileArray[i]);
							//parseArtistHtml(fileArray[i]);
							//parseAlbumHtml(fileArray[i]);
							//parsePlaylistHtml(fileArray[i]);
							break;
						}

					}

				}

			} else {

			}

		}
	}
	
	public static void parsePlaylistHtml(File file) throws IOException{
		Document doc = Jsoup.parse(file, "UTF-8");
		Element info = doc.getElementById("m-playlist");
		PlayList playList = new PlayList();
		playList.setName(info.select("div.tit").get(0).text());
		playList.setPublishDate(info.select("span.time").get(0).text().split("&")[0]);
		playList.setPic(info.select(".cover img").get(0).attr("src"));
		Element operate = info.getElementById("content-operation");
		playList.setFavNum(getNumbers(operate.select("a").get(2).text()));
		playList.setShareNum(getNumbers(operate.select("a").get(3).text()));
		playList.setCommentNum(getNumbers(operate.select("a").get(5).text()));
		if(info.getElementById("album-desc-more")!=null){
			playList.setDesc(info.getElementById("album-desc-more").text());
		}
		playList.setTrackNum(getNumbers(info.getElementById("playlist-track-count").text()));
		playList.setPlayNum(getNumbers(info.getElementById("play-count").text()));
		System.out.println(playList);
	}
	

	public static void parseUserHtml(File file) throws IOException, SQLException {
		Document doc = Jsoup.parse(file, "UTF-8");
		Element userInfo = doc.select("div.g-bd").get(0);
		User user = new User();
		user.setUsername(userInfo.select("span.tit").get(0).text()); // 用户明
		user.setLevel(Integer.parseInt(userInfo.select("span.lev").get(0).text()));// 级别
		
		user.setGender(userInfo.select(".u-icn-01").size() > 0 ? 1 : (userInfo.select(".u-icn-02").size() > 0 ? 2 : 0));// 性别
		Element tabBox = doc.getElementById("tab-box");
		user.setFeed_num(Integer.parseInt(tabBox.getElementById("event_count").text()));
		user.setFollow_num(Integer.parseInt(tabBox.getElementById("follow_count").text()));
		user.setFans_num(Integer.parseInt(tabBox.getElementById("fan_count").text()));

		if (userInfo.select(".f-brk").size()>0) {
			Element sign = userInfo.select(".f-brk").get(0);
			user.setSign(sign.text().split("：")[1]);// 个性签名
		}else{
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
				System.out.println(areaInfo[1]);
				user.setProvince(areaObj.direct.get(areaInfo[0].trim()));
				user.setCity(areaObj.direct.get(areaInfo[1].trim()));
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
		track.setAlbumId(getNumbers(songInfo.select(".des a").get(1).attr("href")));
		track.setArtistId(getNumbers(songInfo.select(".des a").get(0).attr("href")));
		System.out.println(track);
		
		
	}
	
	public static void parseAlbumHtml(File file) throws IOException{
		Document doc = Jsoup.parse(file, "UTF-8");
		Elements intros = doc.select("p.intr");
		Album album = new Album();
		album.setName(doc.select(".tit").get(0).text());
		album.setArtistId(getNumbers(intros.get(0).select("a").get(0).attr("href")));;
		album.setPublishDate(intros.get(1).text().split("：")[1]);
		if(intros.size()>=3){
			album.setCompany(intros.get(2).text().split("：")[1]);
		}
		if(doc.getElementById("album-desc-more")!=null){
			album.setDesc(doc.getElementById("album-desc-more").text());
		}
		album.setCommentNum(getNumbers(doc.getElementById("cnt_comment_count").text()));
		album.setShareNum(getNumbers(doc.select(".u-btni-share i").text()));
	    Elements sub = doc.select(".sub");
	    album.setTrackNum(getNumbers(sub.get(0).text()));
	    album.setPic(doc.select(".cover img").get(0).attr("src"));
	    
		System.out.println(album);
	}
	

	public static void parseArtistHtml(File file) throws IOException, JSONException {
		Document doc = Jsoup.parse(file, "UTF-8");
		// System.out.println(file.getPath());
		Elements metas = doc.select("meta");
		Artist artist = new Artist();
		artist.setName(metas.get(2).attr("content"));
		artist.setAvatar(metas.get(6).attr("content"));
		artist.setId(getNumbers(metas.get(7).attr("content")));

		artist.setDesc(metas.get(8).attr("content"));
		System.out.println(artist);
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
				

				System.out.println(track);

				// System.out.println(
				// jsonArr.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name"));

			}

		} else {

		}

	}
}
