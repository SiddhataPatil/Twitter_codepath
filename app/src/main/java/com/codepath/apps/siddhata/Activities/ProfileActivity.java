package com.codepath.apps.siddhata.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.codepath.apps.siddhata.R;
import com.codepath.apps.siddhata.Application.TwitterApplication;
import com.codepath.apps.siddhata.Network.TwitterClient;
import com.codepath.apps.siddhata.fragments.TweetsFragment;
import com.codepath.apps.siddhata.Model.Tweet;
import com.codepath.apps.siddhata.Model.User;
import com.codepath.apps.siddhata.fragments.UserTimelineFragment;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProfileActivity extends FragmentActivity {
	TweetsFragment fragmentTweetsList;
	TwitterClient client;
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		client = TwitterApplication.getRestClient();

		Intent i = getIntent();
		String u = i.getStringExtra("User");
		if (u != null) {
			client.getUserProfile(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject json) {
					user = User.fromJson(json);
					Log.d("Debug", "$$$ " + user.getName());
					getActionBar().setTitle("@" + user.getScreenName());
					populateProfileHeader(user);
					populateUserProfileTweets(user.getScreenName());
				}
			}, u);

		} else {
			client.getMyInfo(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject json) {
					user = User.fromJson(json);
					getActionBar().setTitle("@" + user.getScreenName());
					populateProfileHeader(user);
					populateProfileTweets();
				}
			});
		}

	}
	private void populateProfileHeader(User u) {
		TextView tvName = (TextView) findViewById(R.id.tvFullName);
		TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		tvName.setText(user.getName());
		tvTagline.setText(user.getTagline());
		tvFollowers.setText(user.getFollowersCount() + " Followers");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
	}

	private void populateProfileTweets() {
		fragmentTweetsList = (TweetsFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_tweets_list);

		client.getUserProfileTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				fragmentTweetsList.addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}

	private void populateUserProfileTweets(String screenName) {
		fragmentTweetsList = (TweetsFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_tweets_list);

		client.getUserTimeline(screenName, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				fragmentTweetsList.addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}