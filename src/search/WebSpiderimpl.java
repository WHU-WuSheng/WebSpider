package search;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebSpiderimpl implements WebSpider{

	@Override
	public Parser getParser() {
		// TODO Auto-generated method stub
		Parserimpl test=new Parserimpl(); 
		
		
		return test;
	}

	@Override
	public List<String> getHtmlFromWeb() {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();//创建list存放所有href
		//jsoup解析
		try {
			// 将html源码存入doc中
			Document doc = Jsoup.parse(new URL("https://tools.grad.wisc.edu/mas/ "), 50000);
			Elements elements1 = doc.select("ul.main-list > li ");
			for(Element x: elements1)
			{
				Elements x2=x.select("a");
				//测试输出的项目名称
				//System.out.println(x2.text());
				String x3=x2.attr("href");
				//测试输出的网页链接
				//System.out.println(x3);
				list.add(x3);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}
