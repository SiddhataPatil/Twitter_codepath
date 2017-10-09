package com.codepath.apps.siddhata.Activities;

import com.codepath.apps.siddhata.R;
import com.codepath.apps.siddhata.Application.TwitterApplication;
import com.codepath.apps.siddhata.Network.TwitterClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

public class ComposeActivity extends FragmentActivity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
	}
	
    public void postTweeter(View v) {
		
		EditText TweeterContent = (EditText) findViewById(R.id.twiterContent);
		String text = TweeterContent.getText().toString();
		TwitterClient client = TwitterApplication.getRestClient();
		
		if(text.length() > 0) {
			
		//TwitterClient client = new TwitterClient(getApplicationContext());	
		client.postTwitter(text, new AsyncHttpResponseHandler());
		Intent i = new Intent(getApplicationContext(), TweetsActivity.class);
		startActivity(i);
		
		}
		
	}

}
