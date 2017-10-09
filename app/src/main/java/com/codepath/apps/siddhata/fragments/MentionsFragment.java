package com.codepath.apps.siddhata.fragments;

import org.json.JSONArray;

import android.os.Bundle;

import com.codepath.apps.siddhata.Application.TwitterApplication;
import com.codepath.apps.siddhata.Model.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsFragment extends TweetsFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterApplication.getRestClient().getMentions(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
}
