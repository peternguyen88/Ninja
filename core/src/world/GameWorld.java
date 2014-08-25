package world;

import game.Ninja;
import gameobjects.AbstractNinja;
import gameobjects.GreenNinja;
import helpers.AssetsLoader;
import helpers.LocationGenerator;

/**
 * Created by Peter on 8/25/2014.
 */
public class GameWorld {
    public AbstractNinja greenNinja, redNinja, yellowNinja, blueNinja;
    public LocationGenerator locationGenerator;

    public float ninjaAliveTime = 0.1f;
    private float aliveTime;

    public GameWorld(){
        locationGenerator  = new LocationGenerator();
        greenNinja = new GreenNinja(AssetsLoader.greenNinja, this);
        redNinja = new GreenNinja(AssetsLoader.redNinja, this);
        yellowNinja = new GreenNinja(AssetsLoader.yellowNinja, this);
        blueNinja = new GreenNinja(AssetsLoader.blueNinja, this);
    }

    public void update(float delta){
        aliveTime += delta;
        if(aliveTime > ninjaAliveTime){
            locationGenerator.reset();
            aliveTime = 0;
        }

        greenNinja.update(delta);
        redNinja.update(delta);
        yellowNinja.update(delta);
        blueNinja.update(delta);
    }
}
