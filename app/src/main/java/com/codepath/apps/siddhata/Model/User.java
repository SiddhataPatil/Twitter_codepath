package com.codepath.apps.siddhata.Model;


import org.json.JSONObject;

/**
 * Created by siddhatapatil on 10/8/17.
 */
public class User extends com.codepath.apps.siddhata.Model.BaseModel {
    public String getName() {
        return getString("name");
    }

    public long getId() {
        return getLong("id");
    }

    public String getScreenName() {
        return getString("screen_name");
    }

    public String getProfileImageUrl() {
        return getString("profile_image_url");
    }

    public String getProfileBackgroundImageUrl() {
        return getString("profile_background_image_url");
    }

    public int getNumTweets() {
        return getInt("statuses_count");
    }

    public int getFollowersCount() {
        return getInt("followers_count");
    }

    public int getFriendsCount() {
        return getInt("friends_count");
    }

    public String getTagline() {
        return getString("description");
    }

    public static com.codepath.apps.siddhata.Model.User fromJson(JSONObject json) {
        com.codepath.apps.siddhata.Model.User u = new com.codepath.apps.siddhata.Model.User();

        try {
            u.jsonObject = json;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }
}


