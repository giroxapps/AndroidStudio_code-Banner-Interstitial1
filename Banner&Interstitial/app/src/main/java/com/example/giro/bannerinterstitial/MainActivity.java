package com.example.giro.bannerinterstitial;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;



public class MainActivity extends Activity {
          AdView adView;     private InterstitialAd interstitial;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        interstitial = new InterstitialAd(this);
        // interstitial.setAdUnitId("ca-app-pub-123456789/123456789");
        interstitial.setAdUnitId(getString(R.string.ad_unit_id));
        interstitial.loadAd(adRequest);

        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Call displayInterstitial() function


                displayInterstitial();
            }
        });


    }
        public void displayInterstitial() {
            // If Ads are loaded, show Interstitial else show nothing.
            if (interstitial.isLoaded()) {
                interstitial.show();
            }
        }

    }






