package de.intektor.landshut_app.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.intektor.landshut_app.LandshutAppClient;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 340;
        config.height = 640;
        new LwjglApplication(new LandshutAppClient(), config);
    }
}
