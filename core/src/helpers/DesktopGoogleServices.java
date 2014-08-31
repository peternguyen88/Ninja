package helpers;

import com.badlogic.gdx.Gdx;

import constant.C;

/**
 * Created by Peter on 8/30/2014.
 */
public class DesktopGoogleServices implements IGoogleServices {
    @Override
    public void signIn() {
        System.out.println("DesktopGoogleServies: signIn()");
    }

    @Override
    public void signOut() {
        System.out.println("DesktopGoogleServies: signOut()");
    }

    @Override
    public void rateGame() {
        Gdx.net.openURI(C.Info.URI);
        System.out.println("DesktopGoogleServices: rateGame()");
    }

    @Override
    public void submitScore(long score) {
        System.out.println("DesktopGoogleServies: submitScore(" + score + ")");
    }

    @Override
    public void showScores() {
        System.out.println("DesktopGoogleServies: showScores()");
    }

    @Override
    public boolean isSignedIn() {
        System.out.println("DesktopGoogleServies: isSignedIn()");
        return false;
    }

    @Override
    public void showBannerAd(boolean show) {
        System.out.println("Show Banner Ad");
    }

    @Override
    public void showInterstitialAd(boolean show) {
        System.out.println("Show Interstitial Ad");
    }
}
