package com.sharepoint.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLParser {
	String title, content;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean parse(String u)
	{
		Document doc;
		boolean parsed = false;
		int maxtry = 5;
        for (int retries = 0; retries < maxtry; retries++) {
            try {
            	doc = Jsoup.connect(u).timeout(10*1000).get();
            	this.setTitle(doc.title());
            	Elements elements = doc.getElementsByTag("p");
            	int temp = 0;
            	String content = "";
            	
            	while(temp<elements.size())
            	{ 
            	  content = content + elements.get(temp).text();
            	  temp++;
            	}
            	this.setContent(content);
            	
            	System.out.println("HTMLParser : "+elements.get(0).text() + elements.get(1).text());
            	parsed = true;
            	break;
            } 
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("connection timed out...let's try again");
                parsed = false;
                continue;
            }
        }
        
        return parsed;
	}
}
