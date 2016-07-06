import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

    // ����һ��ץȡ��վ��������ã��������롢ץȡ��������Դ�����
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process�Ƕ��������߼��ĺ��Ľӿڣ��������д��ȡ�߼�
    public void process(Page page) {
        // ���ֶ���������γ�ȡҳ����Ϣ������������
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            //page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // ����������ҳ�淢�ֺ�����url��ַ��ץȡ
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //��"https://github.com/code4craft"��ʼץ
                .addUrl("https://github.com/simonlu9")
                . addPipeline(new FilePipeline("E:\\download"))
                //����5���߳�ץȡ
                .thread(1)
                //��������
                .run();
    }
}
