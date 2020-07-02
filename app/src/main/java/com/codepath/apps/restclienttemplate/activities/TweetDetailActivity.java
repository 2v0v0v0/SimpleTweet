package com.codepath.apps.restclienttemplate.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.databinding.ActivityTweetDetailBinding;
import com.codepath.apps.restclienttemplate.models.Time;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetDetailActivity extends AppCompatActivity {
    public static final String TAG = "TweetDetailActivity";
    public static final int RADIUS = 30;
    public static final int MARGIN = 10;
    Tweet tweet = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTweetDetailBinding binding = ActivityTweetDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tweet =  Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        Log.i(TAG, String.format("Tweet: %s, %s",tweet.body, tweet.mediaUrl));

        if(tweet!=null) {
            Log.i(TAG, "if 1 " +tweet.mediaUrl);
            if (tweet.mediaUrl != null && tweet.mediaType != null) {
                Log.i(TAG, "if 2 " +tweet.mediaUrl);
                if (tweet.mediaType.equals("photo")) {

                    Log.i(TAG, "if 3 " +tweet.mediaUrl);
                    binding.ivMedia.setVisibility(View.VISIBLE);
                    Glide.with(getApplicationContext())
                            .load(tweet.mediaUrl).fitCenter()
                            .centerCrop().transform(new RoundedCornersTransformation(RADIUS, MARGIN))
                            .into(binding.ivMedia);
                }
            }
        }

        Glide.with(getApplicationContext()).load(tweet.user.profileImageUrl).into(binding.ivProfileImage);
        binding.tvBody.setText(tweet.body);
        binding.tvName.setText(tweet.user.name);
        binding.tvScreenName.setText("@" + tweet.user.screenName);

        Time time = new Time ();
        binding.tvTime.setText(time.getRelativeTimeAgo(tweet.createdAt));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tweet_detail, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home){
            //Compose icon has been selected
            //Navigate to the compose activity
            Intent intent = new Intent(this, TimelineActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}