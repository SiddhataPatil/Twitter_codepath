package com.codepath.apps.siddhata.fragments;

/**
 * Created by siddhatapatil on 10/8/17.
 */

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.codepath.apps.siddhata.Application.TwitterApplication;
import com.codepath.apps.siddhata.Model.Tweet;
import com.codepath.apps.siddhata.Network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


public class UserTimelineFragment extends TweetsFragment {
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline(-1);
    }

    public static UserTimelineFragment newInstance(String screen_name) {
        UserTimelineFragment userFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screen_name);
        userFragment.setArguments(args);
        return userFragment;
    }

    protected void populateTimeline(long maxId) {

        String screenName = getArguments().getString("screen_name");
        client.getUserTimeline(screenName, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                addAll(Tweet.fromJson(json));
            }


            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }

}