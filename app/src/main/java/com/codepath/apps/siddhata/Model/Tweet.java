package com.codepath.apps.siddhata.Model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import static android.text.format.DateUtils.HOUR_IN_MILLIS;
import static android.text.format.DateUtils.WEEK_IN_MILLIS;
import static android.text.format.DateUtils.getRelativeDateTimeString;

/**
 * Created by siddhatapatil on 10/8/17.
 */
public class Tweet extends com.codepath.apps.siddhata.Model.BaseModel {
    private User user;

    public User getUser() {
        return user;
    }

    public String getBody() {
        return getString("text");
    }

    public long getId() {
        return getLong("id");
    }

    public boolean isFavorited() {
        return getBoolean("favorited");
    }

    public boolean isRetweeted() {
        return getBoolean("retweeted");
    }

    public String getRealtiveTime(Context c)
    {
        try {
            return String.valueOf(getRelativeDateTimeString(c, getTwitterDate(getString("created_at")), HOUR_IN_MILLIS, WEEK_IN_MILLIS, 0));
        } catch (ParseException e) {
            Log.d("debug", e.toString());
            return "0";
        }
    }

    public static com.codepath.apps.siddhata.Model.Tweet fromJson(JSONObject jsonObject) {
        com.codepath.apps.siddhata.Model.Tweet tweet = new com.codepath.apps.siddhata.Model.Tweet();
        try {
            tweet.jsonObject = jsonObject;
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return tweet;
    }

    public static ArrayList<com.codepath.apps.siddhata.Model.Tweet> fromJson(JSONArray jsonArray) {
        ArrayList<com.codepath.apps.siddhata.Model.Tweet> tweets = new ArrayList<com.codepath.apps.siddhata.Model.Tweet>(jsonArray.length());

        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject tweetJson = null;
            try {
                tweetJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            com.codepath.apps.siddhata.Model.Tweet tweet = com.codepath.apps.siddhata.Model.Tweet.fromJson(tweetJson);
            if (tweet != null) {
                tweets.add(tweet);
            }
        }

        return tweets;
    }
}
