package search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import vo.Program;

public class FileHandlerimpl implements FileHandler {

	@Override
	public int program2File(List<Program> programList) {
		// TODO Auto-generated method stub
		try {
			
			//System.out.println("haha3");
			String file="";
		for(Program program: programList)
		{
			//System.out.println("hhhhhhhhhh");
			String files="学校名称:"+program.getUniversity()+"\t"+
					"所在国家和地区:"+program.getCountry()+"\t"+
					"项目名称:"+program.getProgramName()+"\t"+
					"开设学院:"+program.getSchool()+"\t"+
					"学位："+program.getDegree()+"\t"+
					"邮箱："+program.getEmail()+"\t"+
					"电话："+program.getPhoneNumber()+"\t"+
					"地址："+program.getLocation()+"\t"+
					"有奖ddl:"+program.getDeadlineWithAid()+"\t"+
					"无奖ddl："+program.getDeadlineWithoutAid()+"\t"+
					"项目主页地址"+program.getHomepage();	
			file+=files+"\n";
			//System.out.println(files);
		}
		try
        {
            File f = new File("D:\\UNIVERSITY OF WISCONSIN–MADISON.txt");
            if(! f.exists())
                f.createNewFile();
            BufferedWriter br = new BufferedWriter(new FileWriter(f));
            br.write(file);
            br.close();
        }
        catch (IOException e){
        	e.printStackTrace();
        	}

		} catch (Exception e) {
	        // TODO: handle exception
	    }
		
		return 0;
	}

}
