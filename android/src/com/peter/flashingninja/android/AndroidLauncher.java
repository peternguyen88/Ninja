package com.peter.flashingninja.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

import game.NinjaGame;
import helpers.IGoogleServices;

public class AndroidLauncher extends AndroidApplication implements IGoogleServices {

    private AdView bannerAd;
    private InterstitialAd interstitialAd;
    private RelativeLayout layout;

    private GameHelper gameHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the layout
        layout = new RelativeLayout(this);

        initGameHelper();
        initAd();

        // Add the AdMob view
        RelativeLayout.LayoutParams adParams = getLayoutParam();

        View gameView = initializeForView(new NinjaGame(this));
        layout.addView(gameView);

        layout.addView(bannerAd,adParams);

        this.setContentView(layout);
    }

    private void initAd(){
        bannerAd = new AdView(this);
        bannerAd.setAdUnitId(getResources().getString(R.string.banner_ad_id));
        bannerAd.setAdSize(AdSize.SMART_BANNER);

        final AdRequest request = new AdRequest.Builder().build();
        bannerAd.loadAd(request);

        bannerAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAd.setVisibility(View.GONE);
                bannerAd.setVisibility(View.VISIBLE);
            }
        });

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_id));
        interstitialAd.loadAd(request);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(request);
            }
        });
    }

    private void initGameHelper() {
        // Create the GameHelper.
        gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(false);
        gameHelper.setMaxAutoSignInAttempts(0);

        GameHelper.GameHelperListener gameHelperListener = initGameHelperListener();

        gameHelper.setup(gameHelperListener);
    }

    private RelativeLayout.LayoutParams getLayoutParam(){
        RelativeLayout.LayoutParams adParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        return adParams;
    }

    @Override
    public void showBannerAd(final boolean show) {
        try {
            runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    if(show){
                        bannerAd.setVisibility(View.VISIBLE);
                    }
                    else{
                        bannerAd.setVisibility(View.GONE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showInterstitialAd(boolean show) {
        try {
            runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    if(interstitialAd.isLoaded()){
                        interstitialAd.show();
                        System.out.println("Loaded");
                    }
                    else {
                        interstitialAd.loadAd(new AdRequest.Builder().build());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private GameHelper.GameHelperListener initGameHelperListener() {
        return new GameHelper.GameHelperListener() {
            @Override
            public void onSignInSucceeded() {
            }

            @Override
            public void onSignInFailed() {
            }
        };
    }

    @Override
    public void signIn() {
        try {
            runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        try {
            runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void rateGame() {
        String str = "https://play.google.com/store/apps/details?id=com.peter.flashingninja.android";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    private final static int REQUEST_CODE_UNUSED = 9002;

    @Override
    public void submitScore(long score) {
        if (isSignedIn() == true) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), getString(R.string.app_id), score);
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), getString(R.string.app_id)), REQUEST_CODE_UNUSED);
        }
    }

    @Override
    public void showScores() {
        if (isSignedIn() == true)
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), getString(R.string.app_id)), REQUEST_CODE_UNUSED);
    }

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }

    @Override
    protected void onStart() {
        super.onStart();
        gameHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameHelper.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bannerAd.destroy();
        System.exit(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }
}
