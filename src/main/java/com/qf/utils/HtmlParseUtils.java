package com.qf.utils;

import com.qf.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtils {
    public static void main(String[] args) throws Exception {
        new HtmlParseUtils().parseJD("红牛").forEach(System.out::println);
    }

    public List<Content> parseJD(String keywords) throws Exception {
        //获取请求      https://search.jd.com/Search?keyword=java
        //前提，需要联网 ，ajax不能获取到！
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        //解析网页（Json返回Document就是Document对象）
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有在js使用的方法都能用
        Element element = document.getElementById("J_goodsList");
        //System.out.println(element.html());
        //获取所有的li元素
        Elements elements = element.getElementsByTag("li");
        ArrayList<Content> goodsList = new ArrayList<>();
        //获取元素中内容，这里el就是每一个li标签
        for (Element el : elements) {
            //关于这种图片特别多的网站，所有的图片都是延迟加载的
            //source-data-lazy-img
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            String shop = el.getElementsByClass("curr-shop hd-shopname").eq(0).text();
            String preferential = el.getElementsByClass("p-icons").eq(0).text();
            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            content.setShop(shop);
            content.setPreferential(preferential);
            goodsList.add(content);
        }
        return goodsList;
    }
}
