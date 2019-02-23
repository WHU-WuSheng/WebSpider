package search;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vo.Program;

public class Parserimpl implements Parser{

	@Override
	public Program parseHtml(String html) {
		// TODO Auto-generated method stub
		Program program=new Program();
		try {
			Document doc = Jsoup.parse(new URL("https://tools.grad.wisc.edu/mas/"+html), 50000);
				
		//获取具体网页源码
			//设置id
			String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
			program.setId(uuid);
			
			
			//设置国家
			program.setCountry("USA");
			
			//设置学校
			program.setUniversity("UNIVERSITY OF WISCONSIN–MADISON");
			
			
			//获取项目名称
			Element programname=doc.select("h3.widget-title").first();
			if(programname!=null)
			{
				program.setProgramName(programname.text());
				//存入项目名称
			}
			else
			{
				program.setProgramName("null");
				//值为空
			}
			//System.out.println(programname.text());
			
			//学院名称为空
			String school="null";
			program.setSchool(school);
			//存入学院名称
			
			//主页url
			String url="https://tools.grad.wisc.edu/mas/ "+html;
			program.setHomepage(url);
			//存入主页url
			
			//开设学位
			if(doc.getElementById("tab3_table")!=null)
			{
			Element table=doc.getElementById("tab3_table");
			Elements degrees=table.select("tr");
			String degreess="";
			for(int i=3;i<degrees.size();i++)
			{
				if(degrees.get(i)!=null)
				{
					degreess +=degrees.get(i).text()+" ";
					//存入一个string
				}
				else
				{
					degreess="null";
					//值为空
				}	
			}
			//存入学位
			program.setDegree(degreess);
			}
			else {program.setDegree("null");}
			//System.out.println(degreess);//测试用
			
			//有奖学金的申请截止日期
			String ddl1="null";
			program.setDeadlineWithAid(ddl1);
			//存入
			
			
			//无奖学金的申请截止日期
			String ddl2="null";
			program.setDeadlineWithoutAid(ddl2);
			//存入
			
			
			//邮箱
			Elements tables=doc.select("div.wp_widget_plugin_box");
			if(tables.select("p >a[href]").size()>2)
			{
			Element email=tables.select("p >a[href]").get(2);
			if(email!=null)
			{
				program.setEmail(email.text());
				//存入邮箱
			}
			else
			{
				program.setEmail("null");
				//值为空
			}
			program.setEmail("null");
			}
			else {program.setEmail("null");}
			
			
			
			//System.out.println(email.text());
			//开设项目所在地址
		    Element content=tables.select("p").get(1);//首先获取整个段落内容
			String contents=content.text();
			String[] contentss=contents.split("Campus");//分隔符截取
			String location=contentss[0];//取前三个即为地址
			if(location!=" ")
			{
				program.setLocation(location);
				//存入地址
			}
			else
			{
				program.setLocation("null");
		       //值为空
			}
			//System.out.println(location);
			
			
			
			//联系电话
			String []phones=contents.split("Phone:|Fax|E-mail");
			if(phones.length>1) {
			String phone=phones[1];
			if(phone!=" ")
			{
				program.setPhoneNumber(phone);
			}
			else
			{
				program.setPhoneNumber("null");
			}
			}else
			{
			program.setPhoneNumber("null");
			}
			//System.out.println(program.getPhoneNumber());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return program;
	}
	/*
	    public static void main(String[] args) {
		Parserimpl parser = new Parserimpl();
		parser.parseHtml("details/view/G018");
		}
	//测试用主函数

	*/
	}


