package utils;

/**
 * Created by Peter on 8/25/2014.
 */
public class Converter {
    public static Point getLocation(int horizontal, int vertical){
        Point p = new Point();
        p.x = 15 + horizontal * 40;
        p.y = 10 + vertical * 40;
        return  p;
    }
}
