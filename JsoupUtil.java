package jsoup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	
	private JsoupUtil(){
		
	}
	
	private static final JsoupUtil instance = new JsoupUtil();
	
	public static JsoupUtil getInstance(){
		return instance;
	}
	
	public void writeFile(String name){
		File file = new File("C:\\Users\\fujian\\Desktop\\douban.txt");
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);
			//TODO
			writer.write(name + "\r\n");
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public void getDoubanProgram(){
		try {
			String[]array = new String[41];
			int ai=0;
			for(int i=0;i<95; i++){
				String url = Constants.URL + Constants.star + String.valueOf(Constants.NUM*i) + Constants.type;
				System.out.println(url);
				Connection connection = Jsoup.connect(url);
				Document document = connection.get();
				Elements lis = document.select("li.subject-item");
				Iterator<Element> liIterator =  lis.iterator();
				int review=0;
				while(liIterator.hasNext()){
					Element element = liIterator.next();
					Elements pl = element.select("span.pl");
					if(pl.text().contains("ÉÙÓÚ")) {
						continue;
					} else
						review = Integer.parseInt(pl.text().substring(pl.text().indexOf("(")+1, pl.text().indexOf("ÈË")));
					
					if(review>1000){
						Elements rate = element.select("span.rating_nums");
						Elements a = element.select("a[title]");
						String name = a.text();
						Elements pub = element.select("div.pub");
						String local = name + "  " + "   " + review + "   " + pub.text()+ "%" + rate.text();
						array[ai] = local;
						ai++;
					}
					
					
				}
				
			}
			
			order(array);
			
			for(String ii : array){
				JsoupUtil.getInstance().writeFile(ii);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void order(String[] arr){
		
		String mid;
		for(int i =0; i<arr.length-1; i++){
			double first = Double.parseDouble(arr[i].substring(arr[i].indexOf("%")+1));
			for(int j=i+1; j<arr.length;j++){
				double second = Double.parseDouble(arr[j].substring(arr[j].indexOf("%")+1));
				if(first < second){
					mid = arr[i];
					arr[i] = arr[j];
					arr[j] = mid;
					first = Double.parseDouble(arr[i].substring(arr[i].indexOf("%")+1));
				}
			}
		}
	}
	
	
}
