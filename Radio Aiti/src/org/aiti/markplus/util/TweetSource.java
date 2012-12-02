package org.aiti.markplus.util;

public class TweetSource {
	public String author;
	public String content;
	public String image_url;
	
	public TweetSource(String username, String message, String url) {
	    this.author = username;
	    this.content = message;
	    this.image_url = url;
	  }

	public TweetSource() {
		// TODO Auto-generated constructor stub
	}
	
}
