package helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import constant.NinjaColor;
import gameobjects.AbstractNinja;
import gameobjects.RealNinja;
import world.GameWorld;

/**
 * Created by Peter on 8/27/2014.
 */
public class GameLevelManager {
    private GameWorld gameWorld;

    List<AbstractNinja> availableNinjas;

    private int numberOfCorrectInLevel;
    private int level;


    public GameLevelManager(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        level1();
    }

    public void chooseCorrectly() {
        numberOfCorrectInLevel++;
        if (numberOfCorrectInLevel >= 5) {
            proceedToNextLevel();
        }
        System.out.println(numberOfCorrectInLevel);
    }

    private void proceedToNextLevel() {
        numberOfCorrectInLevel = 0;
        level++;
        switch (level) {
            case 2:
                level2();
                break;

            case 3:
                level3();
                break;

            case 4:
                level4();
                break;

            case 5:
                level5();
                break;

            case 6:
                level6();
                break;

            case 7:
                level7();
                break;
            case 8:
                level8();
                break;
            default:
                break;
        }
    }

    public void chooseWrongly() {

    }

    public void level1() {

        availableNinjas = new ArrayList<AbstractNinja>();

        availableNinjas.add(new RealNinja(AssetsLoader.greenNinja, gameWorld, NinjaColor.GREEN));
        availableNinjas.add(new RealNinja(AssetsLoader.redNinja, gameWorld, NinjaColor.RED));
        availableNinjas.add(new RealNinja(AssetsLoader.yellowNinja, gameWorld, NinjaColor.YELLOW));
        availableNinjas.add(new RealNinja(AssetsLoader.blueNinja, gameWorld, NinjaColor.BLUE));

        Collections.shuffle(availableNinjas);

        level = 1;
        gameWorld.sameTextColor = true;
        gameWorld.ninjas.add(availableNinjas.remove(0));
        gameWorld.ninjas.add(availableNinjas.remove(0));
        gameWorld.ninjaAliveTime = 0.5f;
    }

    private void level2() {
        gameWorld.ninjaAliveTime = 0.25f;
    }

    private void level3() {
        gameWorld.sameTextColor = false;
    }

    private void level4() {
        gameWorld.sameTextColor = true;
        gameWorld.ninjaAliveTime = 0.4f;
        gameWorld.ninjas.add(availableNinjas.remove(0));
    }

    private void level5() {
        gameWorld.sameTextColor = false;
        gameWorld.ninjaAliveTime = 0.3f;
    }

    private void level6() {
        gameWorld.ninjaAliveTime = 0.2f;
    }

    private void level7() {
        gameWorld.ninjaAliveTime = 0.35f;
        gameWorld.sameTextColor = true;
        gameWorld.ninjas.add(availableNinjas.remove(0));
    }

    private void level8(){
        gameWorld.ninjaAliveTime = 0.15f;
        gameWorld.sameTextColor = false;
    }
}
