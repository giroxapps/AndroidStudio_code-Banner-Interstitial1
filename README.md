# AndroidStudio_code-Banner-Interstitial1
code AndroidStudio to insert advertising banner and interstitial 
Aprire file build.grandle(Module:app) e sotto :
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
    //insert api google with this line
    compile 'com.google.android.gms:play-services:6.+'
}
Inserire la riga evidenziata in rosso.

2.
Aprire il file activity_main.xml(oppure iln file xml presente nella classe giava in cui si vuole inserire la publicità)
E inserire le righe evidenziate in rosso:
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:ads="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent" android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin" tools:context=".MainActivity">

    <com.google.android.gms.ads.AdView
        android:id="@+id/adView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        ads:adSize="BANNER"
        ads:adUnitId="@string/banner_ad_unit_id" />


    <TextView android:text="@string/hello_world" android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>

p.s: lascelta di relative layout è casuale.

3. aprire file   manifest.xml ed inserire le seguenti line subito dopo package eprima del tag application:
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.giro.bannerinterstitial" >

    <!-- Include required permissions for Google Mobile Ads to run-->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >
        <!--This meta-data tag is required to use Google Play Services.-->
        <meta-data android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name="com.google.android.gms.ads.AdActivity"
            android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
            android:theme="@android:style/Theme.Translucent" />
    </application>

</manifest>

3.  Codice banner & Interstitial
	All’interno del file  string.xml   presente in values   inserire le seguenti stringhe:
la prima riga per i banner la seconda per gli interstitial. Il codice viene fornito da googleAdmob una volta registrati.
    <string name="banner_ad_unit_id">ca-app-pub-xxxxxxxx/xxxxxx</string>
    <string name="ad_unit_id">ca-app-pub-594343110900/xxxxxxxxx</string>

5. Codice java

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


