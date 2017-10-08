package com.codepath.apps.siddhata.fragments;

import com.codepath.apps.siddhata.R;
import com.codepath.apps.siddhata.TwitterClient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ComposeFragment extends Fragment {

	TwitterClient client;
	
	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
		return inf.inflate(R.layout.post_twitter_fragment, parent, false);
	}
	
	
	

		

}
