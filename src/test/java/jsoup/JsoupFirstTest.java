package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.net.URL;

public class JsoupFirstTest {
    @Test
    public void testUrl() throws Exception{
        Document dom = Jsoup.parse(new URL("http://mangotea.cn"), 1000);
        String title = dom.getElementsByTag("title").first().text();
        System.out.println("title = " + title);
    }
}
