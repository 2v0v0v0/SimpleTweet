package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public long id;
    public User user;
    public Media media;


    // empty constructor needed by the Parceler library
    public Tweet(){}



    public static Tweet fromJson (JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        //extract the values from JSON
        try {
            tweet.body = jsonObject.getString("text");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.id = jsonObject.getLong("id");
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
            JSONObject objMedias = jsonObject.getJSONObject("entities");
            if(objMedias!=null) {
                JSONArray jsonMedias = objMedias.getJSONArray("media");
                if(jsonMedias != null ) {
                    tweet.media = Media.fromJSON(jsonMedias.getJSONObject(0));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Debug","Tweets " + tweet.toString());



        return tweet;
    }

    public static List<Tweet> fromJsonArray (JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i =0; i <jsonArray.length(); i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
