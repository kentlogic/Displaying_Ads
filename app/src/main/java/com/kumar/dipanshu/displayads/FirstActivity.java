package com.kumar.dipanshu.displayads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class FirstActivity extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;

    private Button fullscreenAd,rewardedVideoAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        MobileAds.initialize(this,getString(R.string.admob_app_id));

        showBannerAd();

        loadInterstitialAd();

        loadRewardedAd();


        fullscreenAd = (Button)findViewById(R.id.interstitialAd);
        fullscreenAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();     //showing interstial ads
                } else {
                    Toast.makeText(FirstActivity.this, "The interstitial wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rewardedVideoAd=(Button)findViewById(R.id.rewardedVideoAd);
        rewardedVideoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();        //showing rewarded video ads
                }else {
                    Toast.makeText(FirstActivity.this, "The interstitial wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //method to load Rewarded video ads...
    private void loadRewardedAd() {
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(FirstActivity.this);
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().addTestDevice("705E72EDF87BE715E51C879560D8ECA8").build());
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                // Reward the user.
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }
        });
    }

    //method to load Interstitial Ads...
    private void loadInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("705E72EDF87BE715E51C879560D8ECA8").build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("705E72EDF87BE715E51C879560D8ECA8").build());
            }
        });
    }

    //method to show Banner Ads...
    private void showBannerAd() {
        mAdView = (AdView)findViewById(R.id.bannerAd);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("705E72EDF87BE715E51C879560D8ECA8").build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mRewardedVideoAd.resume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mRewardedVideoAd.pause(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRewardedVideoAd.destroy(this);
    }
}