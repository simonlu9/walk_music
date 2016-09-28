import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

public class CloudMusicPipeLine  extends FilePersistentBase implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {   
		// TODO Auto-generated method stub
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
        PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".html")),"UTF-8"));
			   printWriter.println(     	resultItems.get("song-html"));
			   printWriter.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
