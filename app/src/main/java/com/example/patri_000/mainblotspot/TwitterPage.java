package com.example.patri_000.mainblotspot;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

public class TwitterPage extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "bKwZz65Kr9ZzYZqxB6erWiNYs";
    private static final String TWITTER_SECRET = "p0xTdWvwLpTz4cVtIpHLV3Hil8e7NEwZuJhxbyFZ9dvpwe9gmr";
    private static ListView myTwitterlistView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_twitter_page);

        // ------------ Receiving and displaying information from previous page ------------ //
        Bundle p = getIntent().getExtras();
        String yourPreviousPzl = p.getString("pkgName");
        //String selection = "I see you selected " + yourPreviousPzl + " EXCELLENT choice!";
        //Toast.makeText(TwitterPage.this, yourPreviousPzl, Toast.LENGTH_SHORT).show();
        // ------------ Receiving and displaying information from previous page ------------ //


        //----------- SEARCH TWITTER ---------------
        // String searchString ="#Obama"
        String searchString = "#"+yourPreviousPzl;
        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query(searchString)
                .build();
        final TweetTimelineListAdapter adapterTwitter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(searchTimeline)
                .build();


        // ---------- END ------------
        // ------------------- LIST VIEW ADDITIONS --------------------
        myTwitterlistView = (ListView) findViewById(R.id.listViewTwitter);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        myTwitterlistView.setAdapter(adapterTwitter);

        // ListView Item Click Listener
        myTwitterlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) myTwitterlistView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }
        });
        // ------------------- LIST VIEW ADDITIONS --------------------







        // -------- TWITTER BEGIN ---------------------//

        // TODO: Use a more specific parent
        final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
        // TODO: Base this Tweet ID on some data from elsewhere in your app
        long tweetId = 631879971628183552L;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                TweetView tweetView = new TweetView(TwitterPage.this, result.data);
                //parentView.addView(tweetView);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Load Tweet failure", exception);
            }
        });

        // -------- TWITTER END ---------------------//


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }




}
