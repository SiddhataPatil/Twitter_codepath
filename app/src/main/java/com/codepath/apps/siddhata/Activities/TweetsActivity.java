package com.codepath.apps.siddhata.Activities;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.siddhata.R;
import com.codepath.apps.siddhata.Application.TwitterApplication;
import com.codepath.apps.siddhata.fragments.HomeFragment;
import com.codepath.apps.siddhata.fragments.MentionsFragment;
import com.codepath.apps.siddhata.Network.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

public class TweetsActivity extends FragmentActivity implements TabListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweets);
		setupNavigationTabs();
		
		TwitterApplication.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				User user = User.fromJson(json);
				getActionBar().setTitle(user.getName().split("\\s+")[0] + "'s Timeline");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweets, menu);
		return true;
	}

	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar.newTab().setText("").setTabListener(this)
				.setTag("HomeFragment").setIcon(R.drawable.ic_home);

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar.newTab().setText("").setTabListener(this)
				.setTag("MentionsFragment").setIcon(R.drawable.ic_mentions);

		actionBar.addTab(tab2);
	}


	public void onProfileView(MenuItem mi) {
	      Intent i = new Intent(this, ProfileActivity.class);
	      startActivity(i);
	}
	
	public void onPostTweet(MenuItem mi) {
	      Intent i = new Intent(this, ComposeActivity.class);
	      startActivity(i);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Class<? extends Fragment> fragmentClass = HomeFragment.class;
		if (tab.getTag() == "MentionsFragment") {
			fragmentClass = MentionsFragment.class;
		}
		android.support.v4.app.FragmentTransaction fts = getSupportFragmentManager()
				.beginTransaction();
		Fragment fragment = Fragment.instantiate(this, fragmentClass.getName());
		fts.replace(R.id.frame_container, fragment);
		fts.commit();
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

}
