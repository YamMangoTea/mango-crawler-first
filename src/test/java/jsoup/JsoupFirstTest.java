package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class JsoupFirstTest {
    @Test
    public void testUrl() throws Exception {
        Document dom = Jsoup.parse(new URL("http://mangotea.cn"), 1000);
        String title = dom.getElementsByTag("title").first().text();
        System.out.println("title = " + title);
    }

    @Test
    public void testString() throws Exception {
        String content = FileUtils.readFileToString(new File("C:\\Users\\Mangotea\\IdeaProjects\\mango-crawler-first\\src\\main\\resources\\test.html"), "utf8");
        Document dom = Jsoup.parse(content);
        String title = dom.getElementsByTag("title").first().text();
        System.out.println("title = " + title);
    }

    @Test
    public void testFile() throws Exception {
        Document dom = Jsoup.parse(new File("C:\\Users\\Mangotea\\IdeaProjects\\mango-crawler-first\\src\\main\\resources\\test.html"), "utf-8");

        String title = dom.getElementsByTag("title").first().text();
        System.out.println("title = " + title);

        String title1 = dom.title();
        System.out.println("title1 = " + title1);
    }

    @Test
    public void testDOM() throws Exception {
        Document dom = Jsoup.parse(new File("C:\\Users\\Mangotea\\IdeaProjects\\mango-crawler-first\\src\\main\\resources\\test.html"), "utf-8");

        //Element element = dom.getElementById("city_bj");
        //Element element = dom.getElementsByTag("span").first();

        //Element element = dom.getElementsByClass("class_a class_b").first();
        //Element element = dom.getElementsByClass("class_a").first();
        //Element element = dom.getElementsByClass("class_b").first();

        //不行，必须要按顺序
        //Element element = dom.getElementsByClass("class_b class_a").first();

        //Element element = dom.getElementsByAttribute("abc").first();
        //Element element = dom.getElementsByAttribute("target").last();

        Element element = dom.getElementsByAttributeValue("href", "http://sh.itcast.cn").first();

        System.out.println("获取到的元素内容是： " + element.text());
    }

    @Test
    public void testData() throws Exception {
        Document dom = Jsoup.parse(new File("C:\\Users\\Mangotea\\IdeaProjects\\mango-crawler-first\\src\\main\\resources\\test.html"), "utf8");

        Element element = dom.getElementById("test");
        String str = "";

        str = element.id();
        str = element.className();
        /*Set<String> classSet = element.classNames();
        for (String s : classSet) {
            System.out.println(s);
        }*/

        //获取属性的值
        str = element.attr("id");
        str = element.attr("class");

        //获取所有属性
        Attributes attributes = element.attributes();
        System.out.println("attributes = " + attributes);


        str = element.text();

        System.out.println("获取到的数据是： " + str);
    }

    @Test
    public void testSelector() throws Exception {
        Document dom = Jsoup.parse(new File("C:\\Users\\Mangotea\\IdeaProjects\\mango-crawler-first\\src\\main\\resources\\test.html"), "utf8");

        /* Elements elements = dom.select("span");
        for (Element element : elements) {
            System.out.println(element.text());
        }*/

        //Element element = dom.select("#city_bj").first();

        //Element element = dom.select(".class_a").first();

        Element element = dom.select("[abc]").first();

        Elements elements = dom.select("[class=s_name]");
        for (Element element1 : elements) {
            System.out.println(element1.text());
        }

        System.out.println("获取到的结果是: " + element.text());
    }

    @Test
    public void testSelector2() throws Exception {
        Document dom = Jsoup.parse(new File("C:\\Users\\Mangotea\\IdeaProjects\\mango-crawler-first\\src\\main\\resources\\test.html"), "utf8");

        Element element = dom.select("h3#city_bj").first();

        element = dom.select("li.class_a").first();

        element = dom.select("span[abc]").first();

        element = dom.select("span[abc].s_name").first();

        element = dom.select("html body div").first();

        Elements elements = dom.select(".city_con li");

        elements = dom.select(".city_con > ul > li");
        elements = dom.select(".city_con > ul > *");
        System.out.println("获取到的内容是: " + element);

        for (Element element1 : elements) {
            System.out.println("遍历的结果： " + element1.text());
        }
    }
}
