import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.http.util.ByteArrayBuffer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

public class RedisTest implements PageProcessor {
	private Site site = Site.me().setDomain("music.163.com").setRetryTimes(3).setSleepTime(1000);
	private JedisPool pool;

//	public RedisTest(String host) {
//		// TODO Auto-generated constructor stub
//		this(new JedisPool(new JedisPoolConfig(), host));
//
//	}
//
//	public RedisTest(JedisPool pool) {
//		this.pool = pool;
//		Jedis jedis = this.pool.getResource();
//		Pipeline p = jedis.pipelined();
//
//		// setDuplicateRemover(this);
//	}

	
	public static void main(String[] args) throws IOException {
		// new RedisTest("localhost");
		// RedisScheduler redis = new RedisScheduler("localhost");
		// redis.push(new Request("http:://baidu.com"), task);
		byte[] dst =  new byte[128]; 
		//ByteBuffer[] a = new ByteArrayBuffer(128);
		File f = new File("E:/download/queue/music.163.com.urls.txt");
		 Spider spider = Spider.create(new RedisTest());
		 Scheduler scheduler = new RedisScheduler("localhost");

		FileChannel fc;
		byte c;
		try {
			fc = new RandomAccessFile(f, "r").getChannel();
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
			
			// scheduler.push(new Request("http://www.baidu.com"),spider );
			 int start = 144584737;
			 int i = 0;
			 for(;start<f.length();start++){
				 if((c=mbb.get())=='\r'&&(c=mbb.get())=='\n'){
					//System.out.println(new String(dst,0,i));
					 
					 scheduler.push(new Request(new String(dst,0,i)),spider );
					 for(;i>0;i--){
						 dst[i]=0;
					 };
					
				 }else{
					 dst[i++] = c;
				 }
			 }
			
			
//			for(int i = 0;i<50;i++){
//				dst[i] = mbb.get(144584737+i);
//			}
//		
//			System.out.println(new String(dst));


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	

	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
}
