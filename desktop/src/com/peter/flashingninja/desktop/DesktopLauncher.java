package com.peter.flashingninja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.NinjaGame;
import helpers.DesktopGoogleServices;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Flashing Ninja ^_^";
        config.height = 360;
        config.width = 640;
		new LwjglApplication(new NinjaGame(new DesktopGoogleServices()), config);
	}
}
