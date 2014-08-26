package constant;

import com.badlogic.gdx.graphics.Color;

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

    String name;
    Color color;
}
