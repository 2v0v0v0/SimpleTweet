package com.codepath.apps.restclienttemplate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.databinding.ActivityProfileBinding;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.TwitterNumbers;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "ProfileActivity";
    public static final int RADIUS = 10;
    public static final int MARGIN = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        User user = tweet.user;
        Glide.with(getApplicationContext()).load(user.profileImageUrl)
                .transform(new RoundedCornersTransformation(RADIUS, MARGIN)).into(binding.profilePic);
        TwitterNumbers number = new TwitterNumbers();
        binding.tvName.setText(user.name);
        binding.tvScreenName.setText("@"+user.screenName);
        binding.followerNum.setText(number.format(user.followers));
        binding.followingNum.setText(number.format(user.followings));
        binding.descriptionText.setText(user.description);
    }



}