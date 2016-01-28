package org.sivaram.newsgroup.service;

import java.io.IOException;
import java.net.URL;

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
        for (int retries = 0; retries < 1; retries++) {
            try {
            	URL url = new URL(u);
            	/*System.setProperty("java.net.useSystemProxies", "true");
            	List l = null;
            	try {
            	  l = ProxySelector.getDefault().select(new URI("http://www.yahoo.com"));
            	}
            	catch (URISyntaxException e) {
            	  e.printStackTrace();
            	}
            	java.net.Proxy proxy = null;
            	if (l != null) {
            	   for (Iterator iter = l.iterator(); iter.hasNext(); )
            	   {
            	      proxy = (java.net.Proxy) iter.next();
            	      System.out.println("proxy hostname : " + proxy.type());
            	      InetSocketAddress addr = (InetSocketAddress) proxy.address();
            	      if (addr == null) {
            	        System.out.println("No Proxy");
            	      }
            	      else {
            	        System.out.println("proxy hostname : " + addr.getHostName());
            	        System.out.println("proxy port : " + addr.getPort());
            	      }
            	   }
            	}; // or whatever your proxy is
            	
            	HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
          	  uc.connect();

            	    String line = null;
            	    StringBuffer tmp = new StringBuffer();
            	    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            	    while ((line = in.readLine()) != null) {
            	      tmp.append(line);
            	    }
				*/
            	doc = Jsoup.connect(u).get();
            	this.setTitle(doc.title());
            	Elements elements = doc.getElementsByTag("p");
            	this.setContent(elements.get(0).text()+ ". "+elements.get(1).text());
            	System.out.println("HTMLParser : "+elements.get(0).text() + elements.get(1).text());
            	parsed = true;
            } 
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("connection timed out...let's try again");
                parsed = false;
            }
        }
        
        return parsed;
	}

	public boolean parseFullPage(String u)
	{
		Document doc;
		boolean parsed = false;
        for (int retries = 0; retries < 1; retries++) {
            try {
            	URL url = new URL(u);
            	/*System.setProperty("java.net.useSystemProxies", "true");
            	List l = null;
            	try {
            	  l = ProxySelector.getDefault().select(new URI("http://www.yahoo.com"));
            	}
            	catch (URISyntaxException e) {
            	  e.printStackTrace();
            	}
            	java.net.Proxy proxy = null;
            	if (l != null) {
            	   for (Iterator iter = l.iterator(); iter.hasNext(); )
            	   {
            	      proxy = (java.net.Proxy) iter.next();
            	      System.out.println("proxy hostname : " + proxy.type());
            	      InetSocketAddress addr = (InetSocketAddress) proxy.address();
            	      if (addr == null) {
            	        System.out.println("No Proxy");
            	      }
            	      else {
            	        System.out.println("proxy hostname : " + addr.getHostName());
            	        System.out.println("proxy port : " + addr.getPort());
            	      }
            	   }
            	}; // or whatever your proxy is
            	
            	HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
          	  uc.connect();

            	    String line = null;
            	    StringBuffer tmp = new StringBuffer();
            	    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            	    while ((line = in.readLine()) != null) {
            	      tmp.append(line);
            	    }
				*/
            	doc = Jsoup.connect(u).get();
            	this.setTitle(doc.title());
            	Elements elements = doc.getElementsByTag("p");
            	for(int c=0;c<elements.size();c++)
            	{
            	   this.content = this.content + ". " + elements.get(c).text();
            	}
            	
            	System.out.println("HTMLParser : "+elements.get(0).text() + elements.get(1).text());
            	parsed = true;
            } 
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("connection timed out...let's try again");
                parsed = false;
            }
        }
        
        return parsed;
	}
}
