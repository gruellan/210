
import java.io.*;
import java.nio.file.Paths;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

import java.math.*;

public class Main {

    public static RenderWindow app;
    public static View appview = new View();

    public static void main(String[] args) {
        final int windowWidth = 1000;
        final int windowHeight = 780;

        String title = "SPY GUY";

        app = new RenderWindow(new VideoMode(windowWidth, windowHeight), title);
        app.setFramerateLimit(20);
        appview = (View) app.getDefaultView();
        app.setView(appview);

        // Declare textures and sprites
        Player player;
        Enemy arrEnemy[] = new Enemy[1];

        Texture backgroundImageTexture = new Texture();
        Sprite backgroundImage;

        // load up things, etc.
        try {
            player = new Player("resources/user.png");
            arrEnemy[0] = new Enemy("resources/enemy.png", 700.f, 600.f);

            backgroundImageTexture.loadFromFile(Paths.get("resources/background.png"));
            backgroundImage = new Sprite(backgroundImageTexture);
        } catch (IOException e) {
            e.printStackTrace();
            app.close();
            return;
        }

        // Main Loop
        while (app.isOpen()) {
            // Event processing
            for (Event event : app.pollEvents()) {
                if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
                    player.jump();
                }
                switch (event.type) {
                    case CLOSED:
                        app.close();
                        break;

                    case KEY_PRESSED:
                        KeyEvent keyEvent = event.asKeyEvent();

                        switch (keyEvent.key) {
                            case A:
                                if (keyEvent.shift) {
                                    player.changePos(-50, 0);
                                } else {
                                    player.changePos(-5, 0);
                                }
                                break;

                            case D:
                                if (keyEvent.shift) {
                                    player.changePos(50, 0);
                                } else {
                                    player.changePos(5, 0);
                                }
                                break;

                            case SPACE:
                                if (keyEvent.shift) {
                                    player.jump();
                                } else {
                                    player.jump();
                                }
                                break;
                        }
                }
            }

            if (player.isTouching(arrEnemy[0].enemySprite))
                player.die();

            player.setSpriteColor(Color.WHITE);

            // Drawing and things
            app.clear(Color.WHITE);

            // Draw things
            app.draw(backgroundImage);
            app.draw(arrEnemy[0]);
            app.draw(player);
            app.display();
        }
    }
}
