
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover;

public class CloudMusicPageProcessor implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	public static final String HOME_DIR = "home"; // 个人主页
	public static final String FAV_DIR = "fav"; // 我喜欢的音乐列表
	public static final String COLL_DIR = "coll"; // 我收藏的音乐列表
	public static final String SONG_DIR = "song";// 歌曲详情页

	public static final String URL_LIST = "http://music\\.163\\.com/playlist\\?id=(\\d+)";
	public static final String URL_SONG = "http://music\\.163\\.com/song\\?id=(\\d+)";
	public static final String URL_USER = "http://music\\.163\\.com/user/home\\?id=(\\d+)";
	public static final String URL_SINGER = "http://music\\.163\\.com/artist\\?id=(\\d+)";
	public static final String URL_ALBUM = "http://music\\.163\\.com/album\\?id=(\\d+)";
	public static final String URL_ARTIST_ALBUM = "http://music\\.163\\.com/artist/album\\?id=(\\d+)";
	public static final String URL_ARTIST_DESC = "http://music\\.163\\.com/artist/desc\\?id=(\\d+)";
	public static final String URL_LIST_TAG = "http://music\\.163\\.com/discover/playlist/\\?cat=([\\w\\W]+)&order=hot";
	public static final String URL_LIST_TAG_PAGE = "http://music\\.163\\.com/discover/playlist/\\?order=hot&cat=([\\w\\W]+)&limit=(\\d+)&offset=(\\d+)";

	// public static final String URL_RANK =
	// "http://music.163.com/user/home?id=\\d+";
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	public CloudMusicPageProcessor() {
		site.addHeader("accept", "*/*");
		site.addHeader("connection", "Keep-Alive");
		site.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
		String myCookie = "__gads=ID=d37ad833043438b9:T=1448585735:S=ALNI_Mast7F2aZvKJwDdCiU6ZzuFvl5jQQ; _ntes_nnid=6a48927e313db08684a2f69ef81d4af9,1448585708858; vjuids=-24680d5ac.151466fc561.0.d465985b; _ntes_nuid=6a48927e313db08684a2f69ef81d4af9; usertrack=c+5+hVZXv9Ktaj+0CPi5Ag==; visited=true; Province=020; City=0757; _ga=GA1.2.517963274.1448585709; NETEASE_WDA_UID=1562682#|#1371429332931; vjlast=1448585709.1467358791.11; vinfo_n_f_l_n3=3105ef2b5d64f73c.1.12.1448585708927.1465005808356.1467358815254; P_INFO=simonlu123@163.com|1467425974|1|kaola|00&99|not_found&1467212342&quan#gud&440600#10#0#0|&0||simonlu123@163.com; NTES_PASSPORT=6yESAh.etpVzCnWxQu63nDrqt17YRmgSzxh8EMrrRsliVQXFV_orx5E_mnWoIN6SG.kVVC4nmmJ93MIuhL1P742MdbBCI0qFhaYk9ki4p2qAM; NTESmusic_yyrSI=58272124C59A9D424F4D774DA99C934A.classa-music-yyr1.server.163.org-8010; MUSIC_U=0e4857fbebd723d87125a22ac53cfe679c621dae2bfa2349e473eac7ecbfc25b2dce1caf437f0be4337de2dd67b7f98d46206da54aa635caa70b41177f9edcea; __csrf=5e1e218e2ddbbfc630c5d80cd2279b11; __remember_me=true; __utma=94650624.517963274.1448585709.1467600559.1467616120.12; __utmb=94650624.10.10.1467616120; __utmc=94650624; __utmz=94650624.1467600559.11.6.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; JSESSIONID-WYYY=6876571617b36c68180de6f9dd4150e2f56f101dc091f5b05e3fab33517cf25b85e82a946cb1ed7566907f7606ba8a930bd9071de4b3fedca6e3e3a56f119cd43bb9907591f9e1a68694be81ad2b66b805fbfca59e6001d46415a8cb7dd39798c35ca73d508b4fcebdcc330fa330281b2b301f242a7336e0bd0231bc6532fcd68ccbb7fb%3A1467619768049; _iuqxldmzr_=25";
		site.addHeader("Cookie", myCookie);
		site.addHeader("host", "music.163.com");
		site.addHeader("referer", "http://music.163.com/");
	}

	/*
	 * 歌单主页
	 */
	public void processPlayListHome(Page page) {
	

		Document doc = page.getHtml().getDocument();
		page.putField("dir","playlist-home");
		page.putField("content",doc.html());
		
		Elements userLinks = doc.select("ul.m-piclist a");
		for (Element userLink : userLinks) {

			page.addTargetRequest(userLink.attr("href")); // 喜欢这个歌单的人
		}

		Elements songLinks = doc.select("#song-list-pre-cache a");
		for (Element songlink : songLinks) {
			page.addTargetRequest(songlink.attr("href")); // 歌单歌曲链接

		}
		// 热门歌单 m-rctlist
		Elements playListLinks = doc.select("ul.m-rctlist a");
		for (Element playList : playListLinks) {
			page.addTargetRequest(playList.attr("href")); // 歌单歌曲链接

		}

		Elements tagListLinks = doc.select("div.tags a");
		for (Element tagList : tagListLinks) {
			page.addTargetRequest(tagList.attr("href")); // 标签链接

		}
	}

	public void processUserHome(Page page) {
		Document doc = page.getHtml().getDocument();
		page.putField("dir","user-home");
		page.putField("content",doc.html());
	}

	public void processSingerHome(Page page) {
		Document doc = page.getHtml().getDocument();
		
		page.putField("dir","artist-home");
		page.putField("content",doc.html());
		Element mHome = doc.getElementById("artist-home");// artist-home
		// Element mTab = doc.getElementById("#m_tabs");
		if (mHome !=null) {
			page.addTargetRequest(mHome.attr("href")); // 艺人社区主页
		}
		Elements mLinks = doc.select("#m_tabs a");
		for (Element mLink : mLinks) {
			page.addTargetRequest(mLink.attr("href")); // 热门50单曲/所有专辑/相关MV/歌手介绍
		}
		// 相似艺人
		mLinks = doc.select(".hd a");
		for (Element mLink : mLinks) {
			page.addTargetRequest(mLink.attr("href")); // 相似艺人
		}

		// m_tabs
		System.out.println("singer");

	}
	
	public void processArtistAlbum(Page page){
	
		// m-song-module
		Document doc = page.getHtml().getDocument();
		
		page.putField("dir","artist-album");
		page.putField("content",doc.html());
		
		Elements albumList = doc.getElementById("m-song-module").select("a");
		for (Element album : albumList) {
			page.addTargetRequest(album.attr("href")); // 热门50单曲/所有专辑/相关MV/歌手介绍
		}
		Elements pageList = doc.select("div.u-page a");
		for (Element mpage : pageList) {
			page.addTargetRequest(mpage.attr("href")); // 分页连接
		}
	}
	
	public void processArtistDesc(Page page){
	Document doc = page.getHtml().getDocument();
		
		page.putField("dir","artist-desc");
		page.putField("content",doc.html());
	}
	

	public void processSongHome(Page page) {
		
		Document doc = page.getHtml().getDocument();
		
		page.putField("dir","song-home");
		page.putField("content",doc.html());
		// 演唱信息
		// System.out.println(doc.html());
		Elements songInfo = doc.select("div.cnt p a");
		//System.out.println(songInfo.size());
		for (Element link : songInfo) {
			// System.out.println("----------http://music.163.com" +
			// link.attr("href"));
			page.addTargetRequest(link.attr("href")); // 包括
														// 歌手链接和专辑链接
		}
		org.jsoup.select.Elements items = doc.select("ul.m-piclist a");
		for (Element item : items) {
			page.addTargetRequest(item.attr("href")); // 喜欢这首歌的人
		}
		org.jsoup.select.Elements playlists = doc.select("div.m-rctlist a"); // 包含这首歌的歌单
		for (Element playlist : playlists) {
			page.addTargetRequest(playlist.attr("href")); // 标签列表
		}
	}
	
	public void processTag(Page page){
		 //m-pl-container
		Document doc = page.getHtml().getDocument();
		page.putField("dir","tag-playlist");
		page.putField("content",doc.html());
		Elements albumList = doc.select("#m-pl-container a");
		for (Element album : albumList) {
			page.addTargetRequest(album.attr("href")); // 标签列表
		}
		//m-pl-pager
		Elements pageList = doc.select("#m-pl-pager a");
		for (Element mPage : pageList) {
			page.addTargetRequest(mPage.attr("href")); // 分页
		}
	}
	
	public void processTagePage(Page page){
		Document doc = page.getHtml().getDocument();
		page.putField("dir","tag-playlist");
		page.putField("content",doc.html());
	}
	//处理专辑主页
	public void processAlbumHome(Page page){
		Document doc = page.getHtml().getDocument();
		page.putField("dir","album-home");
		page.putField("content",doc.html());
	}
	

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		if (page.getUrl().regex(URL_LIST).match()) { // 歌单主页

			this.processPlayListHome(page);

		} else if (page.getUrl().regex(URL_SONG).match()) { // 单曲主页

			this.processSongHome(page);

		} else if (page.getUrl().regex(URL_USER).match()) { // 用户主页
			this.processUserHome(page);

		} else if (page.getUrl().regex(URL_ALBUM).match()) { // 专辑主页
			this.processAlbumHome(page);

		} else if (page.getUrl().regex(URL_SINGER).match()) {
			this.processSingerHome(page);

		} else if (page.getUrl().regex(URL_ARTIST_ALBUM).match()) { // 艺人专辑列表
			this.processArtistAlbum(page);

		} else if (page.getUrl().regex(URL_ARTIST_DESC).match()) {
			System.out.println("album");
		} else if (page.getUrl().regex(URL_LIST_TAG).match()) {
			this.processTag(page);
			//System.out.println("tag");
		} else if (page.getUrl().regex(URL_LIST_TAG_PAGE).match()) {
			//System.out.println("tag_page")
			this.processTag(page);
		}

		// System.out.println(page.getUrl().regex(URL_LIST).match());
		System.out.println(page.getUrl());
	//	page.setSkip(true);

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {

		Spider.create(new CloudMusicPageProcessor())
				// 从"http://music.163.com/playlist?id=1896896"开始抓  http://music.163.com/artist?id=3084 http://music.163.com/artist?id=5771
				.addUrl("http://music.163.com/playlist?id=1896896").addPipeline(new FilePipeline("E:\\download") {
					@Override
					public void process(ResultItems resultItems, Task task) {
						// TODO Auto-generated method stub
						// super.process(resultItems, task);
					
						String path = this.path  + task.getUUID() + PATH_SEPERATOR+resultItems.get("dir")+PATH_SEPERATOR;
						String fileName = DigestUtils.md5Hex(resultItems.getRequest().getUrl());
						String aPath = fileName.substring(0,1);
						String bPath = fileName.substring(1,2);
						//System.out.println(path); //.substring(1, 2)
						PrintWriter printWriter;
						try {
						
							printWriter = new PrintWriter(
									new OutputStreamWriter(
											new FileOutputStream(getFile(path+aPath+PATH_SEPERATOR+bPath+PATH_SEPERATOR
													+ fileName + ".html")),
									"UTF-8"));
							printWriter.println(resultItems.get("content"));
							printWriter.close();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				})
				.scheduler(new FileCacheQueueScheduler("E:/download/queue/")
				.setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)
					)
				)
				// 开启5个线程抓取
				.thread(5)
				// 启动爬虫
				.run();
	}
}
