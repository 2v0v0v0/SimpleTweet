package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity
public class User {
    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public  String name;

    @ColumnInfo
    public String screenName;

    @ColumnInfo
    public  String profileImageUrl;

    @Ignore
    public int followers;

    @Ignore
    public int followings;

    @Ignore
    public String description;

    // empty constructor needed by the Parceler library
    public User(){}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.id = jsonObject.getLong("id");
        user.name = jsonObject.getString("name");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl= jsonObject.getString("profile_image_url_https");
        user.followers = jsonObject.getInt("followers_count");
        user.followings = jsonObject.getInt("friends_count");
        try {
            user.description = jsonObject.getString("description");
        } catch (NullPointerException e){
            user.description ="";
        }


        return user;
    }

    public static List<User> fromJsonTweetArray(List<Tweet> tweetsFromNetWork) {
        List<User> users = new ArrayList<>();
        for (int i =0; i < tweetsFromNetWork.size(); i++){
            users.add(tweetsFromNetWork.get(i).user);
        }
        return users;
    }
}
