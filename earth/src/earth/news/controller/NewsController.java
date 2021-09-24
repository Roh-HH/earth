package earth.news.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news/*")
public class NewsController {

	
	public static HashMap<String, String>map;
	
	@RequestMapping("news.et")
	public String newsCrawl(Model model) throws IOException {
		String url = 
		"https://search.naver.com/search.naver?query=%EC%93%B0%EB%A0%88%EA%B8%B0&where=news&ie=utf8&sm=nws_hty";
		System.out.println("뉴스 크롤링 ===============");
		System.out.println("url : " + url);
		
		Document doc = Jsoup.connect(url).get();	
		
		Elements element = doc.select("div.group_news > ul > li > div");
		Elements newsElement = doc.select("div.group_news > ul > li > div > div > a");
		System.out.println("===========" +newsElement);
		System.out.println("===========");
				
		ArrayList<String> news2 = new ArrayList<>();
		ArrayList<String> news1 = new ArrayList<>();

		String URL = "";
		String TITLE = "";
		

		for (Element el : element.select("div.news_area")) {
			System.out.println(el.text());
			System.out.println("==================================");
			URL = el.select("a").attr("href");
			TITLE = el.select("a").attr("title");
			
			System.out.println("=====기사제목	======"+ TITLE);
			System.out.println("==================================");
			news1.add(TITLE);
			news2.add(URL);
			}
		
		model.addAttribute("news1", news1);
		model.addAttribute("news2", news2);
		model.addAttribute("news3", newsElement);

		return "news/news";

	}

}




