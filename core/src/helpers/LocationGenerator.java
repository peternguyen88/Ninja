package helpers;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Converter;
import utils.Point;

/**
 * Created by Peter on 8/25/2014.
 */
public class LocationGenerator {
    List<Coordinate> coordinatesBucket = new ArrayList<Coordinate>();
    Random random = new Random();

    public Point nextPoint(){
        Coordinate coordinate = new Coordinate(random.nextInt(4), random.nextInt(4));
        while(coordinatesBucket.contains(coordinate)){
            coordinate = new Coordinate(random.nextInt(4), random.nextInt(4));
        }
        coordinatesBucket.add(coordinate);
        return Converter.getLocation(coordinate.x, coordinate.y);
    }

    public void reset(){
        this.coordinatesBucket.clear();
    }

    class Coordinate{
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if(o instanceof Coordinate){
                Coordinate o2 = (Coordinate) o;
                return Objects.equal(this.x, o2.x) && Objects.equal(this.y, o2.y);
            }
            return super.equals(o);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("X",x).add("Y",y).toString();
        }
    }
}
