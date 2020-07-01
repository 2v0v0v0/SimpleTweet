package com.codepath.apps.restclienttemplate.models;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity=User.class, parentColumns="id", childColumns="userId"))
public class Tweet {
    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public String body;

    @Ignore
    public User user;

    @ColumnInfo
    public Long userId;

    @ColumnInfo
    public String mediaType;

    @ColumnInfo
    public String mediaUrl;



    // empty constructor needed by the Parceler library
    public Tweet(){}



    public static Tweet fromJson (JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        //extract the values from JSON
        try {
            tweet.body = jsonObject.getString("text");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.id = jsonObject.getLong("id");
            User user = User.fromJson(jsonObject.getJSONObject("user"));
            tweet.user = user;
            tweet.userId = user.id;
            JSONObject entities = jsonObject.getJSONObject("entities");
            if(entities!=null) {
                JSONArray media = entities.getJSONArray("media");
                if(media != null ) {
                    tweet.mediaType = media.getJSONObject(0).getString("type");
                    tweet.mediaUrl = media.getJSONObject(0).getString("media_url_https");
                }
            } else {
                tweet.mediaType=null;
                tweet.mediaUrl= null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Debug","Tweets " +tweet.id+","+ tweet.toString());


        return tweet;
    }

    public static List<Tweet> fromJsonArray (JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i =0; i <jsonArray.length(); i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    @Override
    public String toString() {
        if(mediaUrl != null){
            return "Tweet{" + body + ", " +mediaUrl +", "+ mediaType+"}";
        }
        return "Tweet{ }\n";
    }
}
