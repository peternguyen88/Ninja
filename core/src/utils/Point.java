package utils;

import com.google.common.base.Objects;

/**
 * Created by Peter on 8/25/2014.
 */
public class Point {
    public int x;
    public int y;

    @Override
    public boolean equals(Object o) {
        if(o instanceof Point){
            Point o2 = (Point) o;
            return Objects.equal(this.x,o2.x) && Objects.equal(this.y, o2.y);
        }
        return super.equals(o);
    }
}
