import java.nio.file.Path;
import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.codec.digest.DigestUtils;

public class Output {
			  
				private static final String PATH_SEPERATOR = "/";
				public static void main(String[] args) throws InterruptedException {
					String ss = DigestUtils.md5Hex("123456");
					System.out.println(ss);
					
//					Calendar cal = Calendar.getInstance();
//					int month = cal.get(Calendar.MONTH);
//					int date = cal.get(Calendar.DATE);
//					int hour = cal.get(Calendar.HOUR_OF_DAY);
//					int minute = cal.get(Calendar.MINUTE);
//					int second = cal.get(Calendar.SECOND);
//					String path =month+PATH_SEPERATOR+ date+PATH_SEPERATOR+hour+PATH_SEPERATOR+minute+PATH_SEPERATOR+second+PATH_SEPERATOR;
//					System.out.println(path);
					
				}
				static String getOutputFilePath(String root){
							
					Calendar cal = Calendar.getInstance();
					int month = cal.get(Calendar.MONTH);
					int date = cal.get(Calendar.DATE);
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int minute = cal.get(Calendar.MINUTE);
					int second = cal.get(Calendar.SECOND);
					String path =root+PATH_SEPERATOR+month+PATH_SEPERATOR+ date+PATH_SEPERATOR+hour+PATH_SEPERATOR+minute+PATH_SEPERATOR+second+PATH_SEPERATOR;
				//	System.out.println(path);
					return path;
					
				}
				
}
