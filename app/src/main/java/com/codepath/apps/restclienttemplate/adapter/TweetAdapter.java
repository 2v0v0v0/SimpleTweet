package com.codepath.apps.restclienttemplate.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.databinding.ItemTweetBinding;
import com.codepath.apps.restclienttemplate.models.Time;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{
    public static final int RADIUS = 30;
    public static final int MARGIN = 10;

    Context context;
    List<Tweet> tweets;
    OnTweetListener mOnTweetListener;
    Time time = new Time();

    //Pass in the context and list of tweets
    public TweetAdapter(Context context, List<Tweet> tweets, OnTweetListener onTweetListener) {
        this.context = context;
        this.tweets = tweets;
        this.mOnTweetListener = onTweetListener;
    }

    //For each row inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view, mOnTweetListener);

    }

    //Bind values based on the position pf the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the data at position
        Tweet tweet = tweets.get(position);
        //Bind the tweet with the view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }


    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    //Define a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ItemTweetBinding binding;
        OnTweetListener onTweetListener;


        public ViewHolder(@NonNull View itemView, OnTweetListener onTweetListener) {
            super(itemView);
            binding = ItemTweetBinding.bind(itemView);
            this.onTweetListener = onTweetListener;

            itemView.setOnClickListener(this);
        }

        public void bind (Tweet tweet){
            binding.tvBody.setText(tweet.body);
            binding.tvScreenName.setText("@"+tweet.user.screenName);
            binding.tvName.setText(tweet.user.name);
            binding.tvTime.setText(time.getRelativeTimeAgo(tweet.createdAt));
            Glide.with(context).load(tweet.user.profileImageUrl).
                    transform(new RoundedCornersTransformation(100, MARGIN)).into(binding.ivProfileImage);

            if (tweet.mediaUrl!=null && tweet.mediaType.equals("photo")){
                binding.ivMedia.setVisibility(View.VISIBLE);
                Glide.with(context)
                       .load(tweet.mediaUrl)
                        .centerCrop().transform(new RoundedCornersTransformation(RADIUS, MARGIN))
                        .into(binding.ivMedia);
            }else{
                binding.ivMedia.setVisibility(View.GONE);
            }
            Log.i("TweetAdapter", tweet.toString());
        }

        @Override
        public void onClick(View view) {
            onTweetListener.onTweetClick(getAdapterPosition());
        }
    }

    public interface OnTweetListener{
        void onTweetClick (int position);
    }
}
