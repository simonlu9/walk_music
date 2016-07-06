
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

public class CloudMusicPageProcessor implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	public static final String HOME_DIR = "home"; // 个人主页
	public static final String FAV_DIR = "fav"; // 我喜欢的音乐列表
	public static final String COLL_DIR = "coll"; // 我收藏的音乐列表
	public static final String SONG_DIR = "song";// 歌曲详情页
	

	public static final String URL_LIST = "http://music.163.com/playlist?id=\\d+";
	public static final String URL_SONG = "http://music.163.com/song?id=\\d+";
	public static final String URL_USER = "http://music.163.com/user/home?id=\\d+";
	//public static final String URL_RANK = "http://music.163.com/user/home?id=\\d+";
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

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		if (page.getUrl().regex(URL_LIST).match()) {
			page.putField("html", page.getRawText());
			page.putField("dir", FAV_DIR);

			Document doc = page.getHtml().getDocument();
			Elements songLinks = doc.select("#song-list-pre-cache a");
			for (Element songlink : songLinks) {
				page.addTargetRequest("http://music.163.com" + songlink.attr("href"));
			}
		}else if (page.getUrl().regex(URL_SONG).match()) {
			page.putField("html", page.getRawText());
			page.putField("dir", SONG_DIR);
			Document doc = page.getHtml().getDocument();
	              org.jsoup.select.Elements items =  doc.select("ul.m-piclist a");
	              for(Element item : items){
	            	  page.addTargetRequest("http://music.163.com" +item.attr("href"));
	              }
			
		}else if(page.getUrl().regex(URL_USER).match()){
			
		}
		// String jsonString = doc.select("#song-list-pre-cache
		// textarea").text();
		// // System.out.println(jsonString);
		// JSONArray jsonArr=null;
		// try {
		// jsonArr = new JSONArray(jsonString);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// for(int i=0;i<jsonArr.length();i++)
		// {
		// // System.out.println();
		//
		// try {
		// System.out.println(
		// jsonArr.getJSONObject(0).getJSONObject("album").get("picUrl"));
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// break;
		// }

		// // 部分二：定义如何抽取页面信息，并保存下来
		// page.putField("author",
		// page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		// page.putField("name", page.getHtml().xpath("//h1[@class='entry-title
		// public']/strong/a/text()").toString());
		// if (page.getResultItems().get("name") == null) {
		// //skip this page
		// //page.setSkip(true);
		// }
		// page.putField("readme",
		// page.getHtml().xpath("//div[@id='readme']/tidyText()"));
		//
		// // 部分三：从页面发现后续的url地址来抓取
		// page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {

		Spider.create(new CloudMusicPageProcessor())
				// 从"https://github.com/code4craft"开始抓
				.addUrl("http://music.163.com/playlist?id=1896896").addPipeline(new FilePipeline("E:\\download") {
					@Override
					public void process(ResultItems resultItems, Task task) {
						// TODO Auto-generated method stub
						// super.process(resultItems, task);

						String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
						PrintWriter printWriter;
						try {
							printWriter = new PrintWriter(
									new OutputStreamWriter(
											new FileOutputStream(getFile(path
													+ DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".html")),
									"UTF-8"));
							printWriter.println(resultItems.get("song-html"));
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
				// 开启5个线程抓取
				.thread(1)
				// 启动爬虫
				.run();
	}
}
