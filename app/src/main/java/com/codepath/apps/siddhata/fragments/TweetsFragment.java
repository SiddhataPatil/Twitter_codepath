package com.codepath.apps.siddhata.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.codepath.apps.siddhata.Activities.ProfileActivity;
import com.codepath.apps.siddhata.R;
import com.codepath.apps.siddhata.Application.TwitterApplication;
import com.codepath.apps.siddhata.Adapter.TweetsAdapter;
import com.codepath.apps.siddhata.Network.EndlessScrollListener;
import com.codepath.apps.siddhata.Network.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;


public class TweetsFragment extends Fragment {
	TweetsAdapter adapter;
	FragmentActivity listener;
    long itemPerPage = 20;
    long maxId = 0;
	
	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
		return inf.inflate(R.layout.fragment_tweets_list, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        adapter = new TweetsAdapter(getActivity(), tweets);
        ListView lv = (ListView) getActivity().findViewById(R.id.lvTweets);

        lv.setOnScrollListener(new EndlessScrollListener() {
             @Override
             public void onLoadMore(int page, int totalItemsCount) {
                 customLoadMoreDataFromApi(maxId, itemPerPage);
             }
		});

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView im = (ImageView)view.findViewById(R.id.ivProfile);
                String User = (String)im.getTag();

                customLoadUserData(User);
            }

        });

		lv.setAdapter(adapter);
	}


	
	public void addAll(ArrayList<Tweet> tweets) {
        this.maxId = tweets.get(tweets.size() - 1).getId();
		adapter.addAll(tweets);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.listener = (FragmentActivity) activity;
	}

    public void customLoadMoreDataFromApi(long dummy, long itemPerPage){
        TwitterApplication.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
        	@Override
       	    public void onSuccess(JSONArray jsonTweets) {
       		    addAll(Tweet.fromJson(jsonTweets));
        	}
        }, this.maxId, 20);

    }

    public void customLoadUserData(String User){
        Intent i = new Intent(getActivity(), ProfileActivity.class);
        i.putExtra("User", User);
        Log.d("Debug", "*** " + User);
        startActivity(i);
    }
}
