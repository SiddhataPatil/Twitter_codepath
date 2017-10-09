package com.codepath.apps.siddhata.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.siddhata.Application.TwitterApplication;
import com.codepath.apps.siddhata.Model.Tweet;
import com.codepath.apps.siddhata.Network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;


public class HomeFragment extends TweetsFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterApplication.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
}