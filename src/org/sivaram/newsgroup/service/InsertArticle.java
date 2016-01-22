package org.sivaram.newsgroup.service;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.sivaram.newsgroup.models.Article;

public class InsertArticle {

	
	public static boolean insert(Article a)
	{
		Connection con = null;
		int result = 0;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb", "root", "axess");
			PreparedStatement ps = con.prepareStatement("Insert into article(aid,userid,url,countlikes,countcom,uldatetime) values(null,?,?,0,0,?) ");
			ps.setString(1, a.getUserid());
			ps.setString(2, a.getUrl());
			Date dt = new Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ct = sdf.format(dt);
			ps.setString(3, ct);
			result = ps.executeUpdate();
			
			URL url = new URL(a.getUrl());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(result==1)
			return true;
		return false;
		
		//readContent();
	}
	
	public static void readContent()
	{
		String url = "http://www.sciencemag.org/news/2015/11/missing-link-between-dinosaur-nests-and-bird-nests";
        /*try {
            DOMParser parser = new DOMParser();
            parser.parse(url);
             
            Document document = parser.getDocument();
            DOMReader reader = new DOMReader();
            org.dom4j.Document doc = reader.read(document);
             
            Node totalResultNode = doc.selectSingleNode("//SPAN[@id='lblShowingResultsTop']/B[3]");
             
            @SuppressWarnings("unchecked")
            List<Node> itemList =  doc.selectNodes("//DIV[@class = 'searchrow']");
 
            System.out.println("Showing " + itemList.size() + " out of " + totalResultNode.getText());
            for(Node itemNode : itemList){
                Node itemTitle = itemNode.selectSingleNode("DIV[@class = 'searchrow-description']/A");
                System.out.println(itemTitle.getText());
            }
        } 
        
        catch (SAXException e) {
            System.out.println(e.getMessage());
        } 
        
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
       */
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(url).timeout(10*1000).get();
			String title = doc.title();
			System.out.println(title);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
