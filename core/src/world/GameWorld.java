package world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import constant.NinjaColor;
import gameobjects.AbstractNinja;
import gameobjects.RealNinja;
import helpers.AssetsLoader;
import helpers.LocationGenerator;
import utils.Point;

/**
 * Created by Peter on 8/25/2014.
 */
public class GameWorld {
    public AbstractNinja greenNinja, redNinja, yellowNinja, blueNinja;
    public List<AbstractNinja> ninjas;

    public LocationGenerator locationGenerator;

    public float ninjaAliveTime = 0.25f;
    public float flashingTime = 2f;
    private float aliveTime, runtime;

    private GameState currentState;
    public AbstractNinja currentNinja;
    private Random random = new Random();

    enum GameState {
        RUNNING, CHOOSING, CHOSE
    }

    public GameWorld() {
        locationGenerator = new LocationGenerator();

        ninjas = new ArrayList<AbstractNinja>();

        greenNinja = new RealNinja(AssetsLoader.greenNinja, this, NinjaColor.GREEN);
        redNinja = new RealNinja(AssetsLoader.redNinja, this, NinjaColor.RED);
        yellowNinja = new RealNinja(AssetsLoader.yellowNinja, this, NinjaColor.YELLOW);
        blueNinja = new RealNinja(AssetsLoader.blueNinja, this, NinjaColor.BLUE);

        ninjas.add(greenNinja);
        ninjas.add(redNinja);
        ninjas.add(yellowNinja);
        ninjas.add(blueNinja);

        currentState = GameState.RUNNING;
    }

    public void update(float delta) {
        runtime += delta;

        if(runtime > flashingTime && isStateRunning()){
            switchToChoosingState();
        }

        switch (currentState) {
            case RUNNING:
                runningUpdate(delta);
                break;
            case CHOOSING:

                break;
            default:
                break;
        }
    }

    private void switchToChoosingState() {
        currentState = GameState.CHOOSING;

        for(AbstractNinja ninja : ninjas){
            ninja.setDisplay(false);
        }

        currentNinja = ninjas.get(random.nextInt(ninjas.size()));
    }

    private void runningUpdate(float delta){
        aliveTime += delta;
        if (aliveTime > ninjaAliveTime) {
            locationGenerator.reset();
            aliveTime = 0;

            for(AbstractNinja ninja : ninjas){
                ninja.nextPoint();
            }
        }
    }

    public void chooseNinja(Point p){
        currentState = GameState.CHOSE;
        currentNinja.setDisplay(true);

        if(currentNinja.getLocation().equals(p)){
            System.out.println("Correct");
        }
        else {
            for (AbstractNinja ninja : ninjas) {
                if (ninja.getLocation().equals(p)) {
                    ninja.setDisplay(true);
                }
            }
            System.out.println("Incorrect");
        }
    }

    public boolean isStateChoosing(){
        return currentState == GameState.CHOOSING;
    }

    public boolean isStateRunning(){
        return currentState == GameState.RUNNING;
    }

    public boolean isStateNinjaChosen() {
        return currentState == GameState.CHOSE;
    }
}
