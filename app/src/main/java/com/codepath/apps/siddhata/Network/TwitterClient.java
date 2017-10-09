package com.codepath.apps.siddhata.Network;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.SSL.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "Czuqscs2DpBSw2WG0czg8cLEw";
    public static final String REST_CONSUMER_SECRET =
            "MX9VfomZrTXSUjo7ami5eqY4FdAlaVqoWL5gqFlqi4um7HO2bW";
    public static final String REST_CALLBACK_URL = "oauth://simpletwitter"; // Change this (here and in manifest)
    public static final String TIMELINE_STATUS = "statuses/home_timeline.json";
    public static final String UPDATE_TWEETER = "statuses/update.json";
    public static final String MENTION_TIMELINE = "statuses/mentions_timeline.json";
    public static final String USER_TIMELINE = "statuses/user_timeline.json";
    public static final String VERIFY_CREDENTIAL = "account/verify_credentials.json";
    public static final String USER_PROFILE = "users/show.json";
    public static final int TWEETS_COUNT_ONE_TIME = 25;
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    
    public void getHomeTimeline(AsyncHttpResponseHandler handler, long offset, long itemPerPage) {
    	String url = getApiUrl(TIMELINE_STATUS);
        RequestParams params = new RequestParams();
        if (offset != 0 ){
            params.put("max_id" , String.valueOf(offset));
        }

        if (itemPerPage != 0 ){
            params.put("count" , String.valueOf(itemPerPage));
        }
    	client.get(url, params, handler);
    }

    public void getUserProfile(AsyncHttpResponseHandler handler, String UserName) {
         String url = getApiUrl(USER_PROFILE);
         RequestParams params = new RequestParams();
         params.put("screen_name", UserName);
         client.get(url, params, handler);
    }
    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
        String url = getApiUrl(TIMELINE_STATUS);
        client.get(url, null, handler);
    }
    
    public void getMentions(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl(MENTION_TIMELINE);
    	client.get(url, null, handler);
    }
    
    public void getUserProfileTimeline(AsyncHttpResponseHandler handler) {
        String url = getApiUrl(USER_TIMELINE);
        client.get(url, null, handler);
    }

    public void getUserTimeline(String screenName, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        RequestParams params = new RequestParams();
        params.put("count", String.valueOf(TWEETS_COUNT_ONE_TIME));
        params.put("screen_name", screenName);
        getClient().get(apiUrl, params, handler);
    }
    public void updateStatus(String status, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", status);
        getClient().post(apiUrl, params, handler);
    }
    public void getMyInfo(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl(VERIFY_CREDENTIAL);
        client.get(apiUrl, null, handler);
    }
    
    public void postTwitter(String message, AsyncHttpResponseHandler handler) {
    	RequestParams params = new RequestParams();
   // 	try { //URLEncoder.encode(message,"UTF-8")
		   params.put("status", message);
    	//} catch (UnsupportedEncodingException e) {
	//		e.printStackTrace();
	//	}
		post(UPDATE_TWEETER, handler, params);
    }
    
    public void post(String apiUrl, AsyncHttpResponseHandler handler,
			RequestParams params) {
		String fullApiUrl = getApiUrl(apiUrl);
		params.put("format", "json");
		client.post(fullApiUrl, params, handler);
	}
    
}