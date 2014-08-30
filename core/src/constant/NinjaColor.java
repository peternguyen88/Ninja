package constant;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

/**
 * Created by Peter on 8/25/2014.
 */
public enum NinjaColor {
    BLUE(Color.BLUE,"BLUE"), RED(Color.RED,"RED"), GREEN(Color.GREEN,"GREEN"), YELLOW(Color.YELLOW,"YELLOW");

    private NinjaColor(Color color, String name){
        this.color = color;
        this.name = name;
    }

    public String colorName(){
        return name;
    }

    public Color getColor(){
        return color;
    }
    String name;
    Color color;

    static Random random = new Random();
    public static Color randomColor(){
        switch (random.nextInt(4)){
            case 0:
                return Color.GREEN;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.RED;
            case 3:
                return Color.YELLOW;
            default: return Color.BLACK;
        }
    }
}
