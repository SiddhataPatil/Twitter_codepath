package com.codepath.apps.siddhata.Model;

/**
 * Created by siddhatapatil on 10/8/17.
 */

import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BaseModel {
    protected JSONObject jsonObject;

    public String getJSONString() {
        return jsonObject.toString();
    }

    protected String getString(String name) {
        try {
            return jsonObject.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getTwitterDate(String date) throws ParseException {
        final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setLenient(true);
        return sf.parse(date).getTime();
    }

    protected long getLong(String name) {
        try {
            return jsonObject.getLong(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected int getInt(String name) {
        try {
            return jsonObject.getInt(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected double getDouble(String name) {
        try {
            return jsonObject.getDouble(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected boolean getBoolean(String name) {
        try {
            return jsonObject.getBoolean(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}