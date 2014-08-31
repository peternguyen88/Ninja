package helpers;

/**
 * Created by Peter on 8/30/2014.
 */
public interface IGoogleServices {
    public void signIn();

    public void signOut();

    public void rateGame();

    public void submitScore(long score);

    public void showScores();

    public boolean isSignedIn();

    public void showBannerAd(boolean show);

    public void showInterstitialAd(boolean show);

}
