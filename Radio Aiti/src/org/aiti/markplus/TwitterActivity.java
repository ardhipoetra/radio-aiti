package org.aiti.markplus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.aiti.markplus.R;
import org.aiti.markplus.util.TweetSource;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TwitterActivity extends ListActivity {
	private ArrayList<TweetSource> tweets = new ArrayList<TweetSource>(); 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TweetListAdaptor adaptor = new TweetListAdaptor(this,R.layout.twitter_act, loadTweets());  
        //setListAdapter(adaptor);
        new GetTweets().execute("http://search.twitter.com/search.json?q=@the_marketeers&rpp=100&page=1");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.twitter_act, menu);
        return true;
    }
    
	private class TweetListAdaptor extends ArrayAdapter<TweetSource> {  
	        private ArrayList<TweetSource> tweets;  
	        public TweetListAdaptor(Context context,  
	                                    int textViewResourceId,  
	                                    ArrayList<TweetSource> items) {  
	                 super(context, textViewResourceId, items);  
	                 this.tweets = items;  
	        }  
	        @Override  
	        public View getView(int position, View convertView, ViewGroup parent) {  
	                View v = convertView;  
	                if (v == null) {  
	                        LayoutInflater vi = (LayoutInflater) getSystemService                          
	                        		(Context.LAYOUT_INFLATER_SERVICE);  
	                        v = vi.inflate(R.layout.twitter_act, null);  
	                }  
	                TweetSource o = tweets.get(position);  
	                TextView tt = (TextView) v.findViewById(R.id.toptext);  
	                TextView bt = (TextView) v.findViewById(R.id.bottomtext);  
	                tt.setText(o.content);  
	                bt.setText(o.author);  
	                return v;  
	        }  
	} 
	
	private class GetTweets extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... twitterURL) {
			StringBuilder tweetFeedBuilder = new StringBuilder();
			for (String searchURL : twitterURL) {
				HttpClient tweetClient = new DefaultHttpClient();
				try {
					HttpGet tweetGet = new HttpGet(searchURL);
					HttpResponse tweetResponse = tweetClient.execute(tweetGet);
					StatusLine searchStatus = tweetResponse.getStatusLine();
					if (searchStatus.getStatusCode() == 200) {
						HttpEntity tweetEntity = tweetResponse.getEntity();
						InputStream tweetContent = tweetEntity.getContent();
						InputStreamReader tweetInput = new InputStreamReader(tweetContent);
						BufferedReader tweetReader = new BufferedReader(tweetInput);
						String lineIn;
						while ((lineIn = tweetReader.readLine()) != null) {
						    tweetFeedBuilder.append(lineIn);
						}
					}
					else
					    System.out.println("Whoops - something went wrong!");
					
				}
				catch(Exception e) {
				    Log.e("Whoops - something went wrong!","elele", e);
				    e.printStackTrace();
				}			
				
			}
			
			// TODO Auto-generated method stub
			return tweetFeedBuilder.toString();
		}
		
		protected void onPostExecute(String result) {
			StringBuilder tweetResultBuilder = new StringBuilder();
			try {
				JSONObject resultObject = new JSONObject(result);
				JSONArray tweetArray = resultObject.getJSONArray("results");
				for (int t=0; t<tweetArray.length(); t++) {
					JSONObject tweetObject = tweetArray.getJSONObject(t);
					TweetSource tweet = new TweetSource();  
                    tweet.content = tweetObject.getString("text");  
                    tweet.author = "@" + tweetObject.getString("from_user");  
                    tweets.add(tweet);  
					//tweetResultBuilder.append(tweetObject.getString("from_user"));
					//tweetResultBuilder.append(tweetObject.get("text"));
				}
			}
			catch(Exception e) {
			    Log.e("Whoops - something went wrong!","elele", e);
			    e.printStackTrace();
			}
			 
            setListAdapter(new TweetListAdaptor(  
                            TwitterActivity.this, R.layout.twitter_act, tweets));
			
		}
		
	}
	
	
	/*
	private ArrayList<TweetSource> loadTweets2(){
		 ArrayList<TweetSource> tweets = new ArrayList<TweetSource>(); 
		 Twitter unauthenticatedTwitter = new TwitterFactory().getInstance();
		 
		 try {
			 ArrayList<Status> statuses = unauthenticatedTwitter.getPublicTimeline();
			 
		 }	catch (TwitterException te) {
             System.out.println("Failed to get timeline: "
                     + te.getMessage());
             System.exit(-1);
         }
		
		 return tweets;
	}*/
}
