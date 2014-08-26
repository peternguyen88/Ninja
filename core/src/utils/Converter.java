package utils;

/**
 * Created by Peter on 8/25/2014.
 */
public class Converter {
    public static Point getLocation(int horizontal, int vertical){
        Point p = new Point();
        p.x = 30 + horizontal * 80;
        p.y = 20 + vertical * 80;
        return  p;
    }

    public static Point revertLocation(int screenX, int screenY){
        return getLocation((screenX-30)/80,(screenY-20)/80);
    }
}
