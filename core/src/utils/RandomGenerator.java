package utils;

import java.util.Random;

/**
 * Created by Peter on 8/31/2014.
 */
public class RandomGenerator {
    public static final Random random = new Random();

    public static boolean trueValueWithPossibility(int possibility){
        int r = random.nextInt(100)+1;
        return r <= possibility;
    }
}
